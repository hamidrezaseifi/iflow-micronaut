package com.pth.gui.services.impl.workflow.invoice;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pth.clients.clients.workflow.IInvoiceWorkflowClient;
import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowListEdo;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.mapper.IInvoiceWorkflowMapper;
import com.pth.gui.mapper.IInvoiceWorkflowSaveRequestMapper;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowFile;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflow;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflowSaveRequest;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.IBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.IInvoiceBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.base.WorkflowHandlerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;


@Singleton
public class InvoiceBasicWorkflowHandler extends WorkflowHandlerHelper<InvoiceWorkflow>
        implements IInvoiceBasicWorkflowHandler {

  private static final Logger logger = LoggerFactory.getLogger(InvoiceBasicWorkflowHandler.class);

  private final IInvoiceWorkflowClient invoiceWorkflowClient;
  private final IInvoiceWorkflowMapper invoiceWorkflowMapper;
  private final IInvoiceWorkflowSaveRequestMapper invoiceWorkflowSaveRequestMapper;
  private final IUploadFileManager uploadFileManager;

  public InvoiceBasicWorkflowHandler(IUploadFileManager uploadFileManager,
                                     IInvoiceWorkflowClient invoiceWorkflowClient,
                                     IInvoiceWorkflowSaveRequestMapper invoiceWorkflowSaveRequestMapper,
                                     IInvoiceWorkflowMapper invoiceWorkflowMapper) {
    this.uploadFileManager = uploadFileManager;
    this.invoiceWorkflowClient = invoiceWorkflowClient;
    this.invoiceWorkflowSaveRequestMapper = invoiceWorkflowSaveRequestMapper;
    this.invoiceWorkflowMapper = invoiceWorkflowMapper;
  }

  @Override
  public Optional<InvoiceWorkflow> readWorkflow(final UUID workflowId, SessionData sessionData){

    logger.debug("Read workflow {}", workflowId);

    Optional<InvoiceWorkflowEdo> invoiceWorkflowEdoOptional =
            invoiceWorkflowClient.read(sessionData.getRefreshToken() , workflowId);

    if(invoiceWorkflowEdoOptional.isPresent()){
      final InvoiceWorkflow workflow = invoiceWorkflowMapper.fromEdo(invoiceWorkflowEdoOptional.get());
      return Optional.of(this.prepareWorkflow(workflow, sessionData));
    }
    return Optional.empty();
  }

  @Override
  public List<InvoiceWorkflow> createWorkflow(final InvoiceWorkflowSaveRequest createRequest, SessionData sessionData) {

    logger.debug("Create workflow");

    createRequest.setCommand(EWorkflowProcessCommand.CREATE);

    createRequest.getWorkflow().getWorkflow().setComments(createRequest.getComments());
    if (createRequest.getWorkflow().getWorkflow().getHasActiveAction()) {
      createRequest.getWorkflow().getWorkflow().getActiveAction().setComments(createRequest.getComments());
    }

    this.invoiceWorkflowClient.validate(sessionData.getRefreshToken(),
                                        invoiceWorkflowSaveRequestMapper.toEdo(createRequest));

    this.prepareUploadedFiles(createRequest, sessionData.getCurrentUserId());

    Optional<InvoiceWorkflowListEdo> invoiceWorkflowListEdoOptional =
            this.invoiceWorkflowClient.create(sessionData.getRefreshToken(),
                                              invoiceWorkflowSaveRequestMapper.toEdo(createRequest));

    if(invoiceWorkflowListEdoOptional.isPresent()) {
      final List<InvoiceWorkflow> list =
              invoiceWorkflowMapper.fromEdoList(invoiceWorkflowListEdoOptional.get().getWorkflows());

      return this.prepareWorkflowList(list, sessionData);
    }

    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<InvoiceWorkflow> saveWorkflow(final InvoiceWorkflowSaveRequest saveRequest, SessionData sessionData){

    logger.debug("Save workflow");

    if (saveRequest.getWorkflow().getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getWorkflow().getActiveAction().setCurrentStepId(
              saveRequest.getWorkflow().getWorkflow().getCurrentStepId());
    }

    saveRequest.setCommand(EWorkflowProcessCommand.SAVE);
    if (saveRequest.getWorkflow().getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getWorkflow().getActiveAction().setComments(saveRequest.getComments());
    }

    this.invoiceWorkflowClient.validate(sessionData.getRefreshToken(),
                                        invoiceWorkflowSaveRequestMapper.toEdo(saveRequest));

    this.prepareUploadedFiles(saveRequest, sessionData.getCurrentUserId());

    final Optional<InvoiceWorkflowEdo> resultOptional =
            this.invoiceWorkflowClient.save(sessionData.getRefreshToken(),
                                            invoiceWorkflowSaveRequestMapper.toEdo(saveRequest));

    if(resultOptional.isPresent()) {
      return Optional.of(this.prepareWorkflow(invoiceWorkflowMapper.fromEdo(resultOptional.get()) , sessionData));
    }
    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<InvoiceWorkflow> assignWorkflow(final UUID workflowId, SessionData sessionData){

    logger.debug("Assign workflow");

    final Optional<InvoiceWorkflow> workflowOptional = this.readWorkflow(workflowId, sessionData);
    if(workflowOptional.isPresent()) {

      final InvoiceWorkflowSaveRequest request =
              InvoiceWorkflowSaveRequest.generateNewNoExpireDays(workflowOptional.get());
      request.setCommand(EWorkflowProcessCommand.ASSIGN);
      request.setAssignUser(sessionData.getCurrentUserId());

      this.invoiceWorkflowClient.validate(sessionData.getRefreshToken(),
                                          invoiceWorkflowSaveRequestMapper.toEdo(request));
      final Optional<InvoiceWorkflowEdo> resultOptional =
              this.invoiceWorkflowClient.save(sessionData.getRefreshToken(),
                                              invoiceWorkflowSaveRequestMapper.toEdo(request));
      if(resultOptional.isPresent()) {
        return Optional.of(this.prepareWorkflow(invoiceWorkflowMapper.fromEdo(resultOptional.get()) , sessionData));
      }
      throw new GuiCustomizedException("error by saving workflow");
    }
    return Optional.empty();

  }

  @Override
  public Optional<InvoiceWorkflow> doneWorkflow(final InvoiceWorkflowSaveRequest saveRequest, SessionData sessionData){

    logger.debug("Make workflow done");

    saveRequest.setCommand(EWorkflowProcessCommand.DONE);
    if (saveRequest.getWorkflow().getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getWorkflow().getActiveAction().setComments(saveRequest.getComments());
    }

    this.invoiceWorkflowClient.validate(sessionData.getRefreshToken(),
                                        invoiceWorkflowSaveRequestMapper.toEdo(saveRequest));

    this.prepareUploadedFiles(saveRequest, sessionData.getCurrentUserId());

    final Optional<InvoiceWorkflowEdo> resultOptional =
            this.invoiceWorkflowClient.save(sessionData.getRefreshToken(),
                                            invoiceWorkflowSaveRequestMapper.toEdo(saveRequest));
    if(resultOptional.isPresent()) {
      return Optional.of(this.prepareWorkflow(invoiceWorkflowMapper.fromEdo(resultOptional.get()) , sessionData));
    }
    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<InvoiceWorkflow> archiveWorkflow(final InvoiceWorkflow workflow, SessionData sessionData){

    logger.debug("Make workflow archive");

    final InvoiceWorkflowSaveRequest request = InvoiceWorkflowSaveRequest.generateNewNoExpireDays(workflow);
    request.setCommand(EWorkflowProcessCommand.ARCHIVE);

    this.invoiceWorkflowClient.validate(sessionData.getRefreshToken(),
                                        invoiceWorkflowSaveRequestMapper.toEdo(request));

    final Optional<InvoiceWorkflowEdo> resultOptional =
            this.invoiceWorkflowClient.save(sessionData.getRefreshToken(),
                                            invoiceWorkflowSaveRequestMapper.toEdo(request));
    if(resultOptional.isPresent()) {
      return Optional.of(this.prepareWorkflow(invoiceWorkflowMapper.fromEdo(resultOptional.get()) , sessionData));
    }
    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<WorkflowFile> readWorkflowFile(final UUID workflowId,
                                       final UUID fileId,
                                       SessionData sessionData){

    Optional<InvoiceWorkflow> workflowOptional = this.readWorkflow(workflowId, sessionData);

    if(workflowOptional.isPresent()){
      final WorkflowFile workflowFile = workflowOptional.get().getWorkflow().getFileById(workflowId);

      return Optional.of(workflowFile);
    }

    return Optional.empty();
  }

  @Override
  protected IUploadFileManager getUploadFileManager() {

    return this.uploadFileManager;
  }

}
