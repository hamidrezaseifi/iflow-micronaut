package com.pth.gui.services.impl.workflow.invoice;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pth.clients.workflow.IInvoiceWorkflowClient;
import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.gui.mapper.IInvoiceWorkflowMapper;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowFile;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflow;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflowSaveRequest;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.IWorkflowHandler;
import com.pth.gui.services.impl.workflow.base.WorkflowHandlerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class InvoiceWorkflowHandler extends WorkflowHandlerHelper<InvoiceWorkflow>
        implements IWorkflowHandler<InvoiceWorkflow, InvoiceWorkflowSaveRequest> {

  private static final Logger logger = LoggerFactory.getLogger(InvoiceWorkflowHandler.class);

  private final IInvoiceWorkflowClient invoiceWorkflowClient;
  private final IInvoiceWorkflowMapper invoiceWorkflowMapper;
  private final IUploadFileManager uploadFileManager;

  public InvoiceWorkflowHandler(IUploadFileManager uploadFileManager,
                                IInvoiceWorkflowClient invoiceWorkflowClient,
                                IInvoiceWorkflowMapper invoiceWorkflowMapper) {
    this.uploadFileManager = uploadFileManager;
    this.invoiceWorkflowClient = invoiceWorkflowClient;
    this.invoiceWorkflowMapper = invoiceWorkflowMapper;
  }

  @Override
  public Optional<InvoiceWorkflow> readWorkflow(final UUID workflowId, SessionData sessionData){

    logger.debug("Read workflow {}", workflowId);

    Optional<InvoiceWorkflowEdo> invoiceWorkflowEdoOptional =
            invoiceWorkflowClient.readInvoice(sessionData.getRefreshToken() , workflowId);

    if(invoiceWorkflowEdoOptional.isPresent()){
      final InvoiceWorkflow workflow = invoiceWorkflowMapper.fromEdo(invoiceWorkflowEdoOptional.get());
      return Optional.of(this.prepareWorkflow(workflow, sessionData));
    }
    return Optional.empty();
  }

  @Override
  public List<InvoiceWorkflow> createWorkflow(final InvoiceWorkflowSaveRequest createRequest,
                                              SessionData sessionData) throws IOException {

    logger.debug("Create workflow");

    createRequest.setCommand(EWorkflowProcessCommand.CREATE);

    createRequest.getWorkflow().setComments(createRequest.getComments());
    if (createRequest.getWorkflow().getHasActiveAction()) {
      createRequest.getWorkflow().getActiveAction().setComments(createRequest.getComments());
    }

    this.invoiceWorkflowClient.validate(sessionData.getRefreshToken(), createRequest);

    this.prepareUploadedFiles(createRequest, sessionData.getCurrentUserId());

    final List<InvoiceWorkflow> list = this.invoiceWorkflowClient.create(sessionData.getRefreshToken(), createRequest);

    return this.prepareWorkflowList(list, sessionData);

  }

  @Override
  public Optional<InvoiceWorkflow> saveWorkflow(final InvoiceWorkflowSaveRequest saveRequest, SessionData sessionData) throws
                                                                                                                       IOException {

    logger.debug("Save workflow");

    if (saveRequest.getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getActiveAction().setCurrentStepId(saveRequest.getWorkflow().getCurrentStepId());
    }

    saveRequest.setCommand(EWorkflowProcessCommand.SAVE);
    if (saveRequest.getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getActiveAction().setComments(saveRequest.getComments());
    }

    this.invoiceWorkflowClient.validate(sessionData.getRefreshToken(), saveRequest);

    this.prepareUploadedFiles(saveRequest, sessionData.getCurrentUserId());

    final InvoiceWorkflow result = this.invoiceWorkflowClient.save(sessionData.getRefreshToken(), saveRequest);
    return Optional.of(this.prepareWorkflow(result, sessionData));
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

      this.invoiceWorkflowClient.validate(sessionData.getRefreshToken(), request);

      final InvoiceWorkflow result = this.invoiceWorkflowClient.save(sessionData.getRefreshToken(), request);
      return Optional.of(this.prepareWorkflow(result, sessionData));
    }
    return Optional.empty();

  }

  @Override
  public Optional<InvoiceWorkflow> doneWorkflow(final InvoiceWorkflowSaveRequest saveRequest, SessionData sessionData) throws
                                                                                                                       IOException {

    logger.debug("Make workflow done");

    saveRequest.setCommand(EWorkflowProcessCommand.DONE);
    if (saveRequest.getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getActiveAction().setComments(saveRequest.getComments());
    }

    this.invoiceWorkflowClient.validate(sessionData.getRefreshToken(), saveRequest);

    this.prepareUploadedFiles(saveRequest, sessionData.getCurrentUserId());

    final InvoiceWorkflow result = this.invoiceWorkflowClient.save(sessionData.getRefreshToken(), saveRequest);
    return Optional.of(this.prepareWorkflow(result, sessionData));
  }

  @Override
  public Optional<InvoiceWorkflow> archiveWorkflow(final InvoiceWorkflow workflow, SessionData sessionData){

    logger.debug("Make workflow archive");

    final InvoiceWorkflowSaveRequest request = InvoiceWorkflowSaveRequest.generateNewNoExpireDays(workflow);
    request.setCommand(EWorkflowProcessCommand.ARCHIVE);

    this.invoiceWorkflowClient.validate(sessionData.getRefreshToken(), request);

    final InvoiceWorkflow result = this.invoiceWorkflowClient.save(sessionData.getRefreshToken(), request);
    return Optional.of(this.prepareWorkflow(result, sessionData));
  }

  @Override
  public Optional<WorkflowFile> readWorkflowFile(final UUID workflowId,
                                       final UUID fileId,
                                       SessionData sessionData){

    Optional<InvoiceWorkflow> workflowOptional = this.readWorkflow(workflowId, sessionData);

    if(workflowOptional.isPresent()){
      final WorkflowFile workflowFile = workflowOptional.get().getFileById(workflowId);

      return Optional.of(workflowFile);
    }

    Optional.empty();
  }

  @Override
  protected IUploadFileManager getUploadFileManager() {

    return this.uploadFileManager;
  }

}
