package com.pth.gui.services.impl.workflow.singletask;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.gui.mapper.IInvoiceWorkflowMapper;
import com.pth.gui.mapper.ISingleTaskWorkflowMapper;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowFile;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflowSaveRequest;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.IWorkflowHandler;
import com.pth.gui.services.impl.workflow.base.WorkflowHandlerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pth.clients.clients.workflow.ISingleTaskWorkflowClient;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;


@Singleton
public class SingleTaskWorkflowHandler
        extends WorkflowHandlerHelper<SingleTaskWorkflow>
        implements IWorkflowHandler<SingleTaskWorkflow, SingleTaskWorkflowSaveRequest> {

  private static final Logger logger = LoggerFactory.getLogger(SingleTaskWorkflowHandler.class);

  private final ISingleTaskWorkflowClient singleTaskWorkflowClient;
  private final ISingleTaskWorkflowMapper singleTaskWorkflowMapper;
  private final IUploadFileManager uploadFileManager;

  public SingleTaskWorkflowHandler(ISingleTaskWorkflowClient singleTaskWorkflowClient,
                                   ISingleTaskWorkflowMapper singleTaskWorkflowMapper,
                                   IUploadFileManager uploadFileManager) {

    this.singleTaskWorkflowClient = singleTaskWorkflowClient;
    this.singleTaskWorkflowMapper = singleTaskWorkflowMapper;
    this.uploadFileManager = uploadFileManager;
  }

  @Override
  public Optional<SingleTaskWorkflow> readWorkflow(final UUID workflowId, SessionData sessionData){

    logger.debug("Read workflow {}", workflowId);

    Optional<SingleTaskWorkflowEdo> workflowEdoOptional =
            singleTaskWorkflowClient.read(sessionData.getRefreshToken() , workflowId);

    if(workflowEdoOptional.isPresent()){
      final SingleTaskWorkflow workflow = singleTaskWorkflowMapper.fromEdo(workflowEdoOptional.get());
      return Optional.of(this.prepareWorkflow(workflow, sessionData));
    }
    return Optional.empty();
  }

  @Override
  public List<SingleTaskWorkflow> createWorkflow(final SingleTaskWorkflowSaveRequest createRequest,
                                              SessionData sessionData) throws IOException {

    logger.debug("Create workflow");

    createRequest.setCommand(EWorkflowProcessCommand.CREATE);

    createRequest.getWorkflow().setComments(createRequest.getComments());
    if (createRequest.getWorkflow().getHasActiveAction()) {
      createRequest.getWorkflow().getActiveAction().setComments(createRequest.getComments());
    }

    this.singleTaskWorkflowClient.validate(sessionData.getRefreshToken(), createRequest);

    this.prepareUploadedFiles(createRequest, sessionData.getCurrentUserId());

    final List<SingleTaskWorkflow> list = this.singleTaskWorkflowClient.create(sessionData.getRefreshToken(), createRequest);

    return this.prepareWorkflowList(list, sessionData);

  }

  @Override
  public Optional<SingleTaskWorkflow> saveWorkflow(final SingleTaskWorkflowSaveRequest saveRequest, SessionData sessionData) throws
          IOException {

    logger.debug("Save workflow");

    if (saveRequest.getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getActiveAction().setCurrentStepId(saveRequest.getWorkflow().getCurrentStepId());
    }

    saveRequest.setCommand(EWorkflowProcessCommand.SAVE);
    if (saveRequest.getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getActiveAction().setComments(saveRequest.getComments());
    }

    this.singleTaskWorkflowClient.validate(sessionData.getRefreshToken(), saveRequest);

    this.prepareUploadedFiles(saveRequest, sessionData.getCurrentUserId());

    final SingleTaskWorkflow result = this.singleTaskWorkflowClient.save(sessionData.getRefreshToken(), saveRequest);
    return Optional.of(this.prepareWorkflow(result, sessionData));
  }

  @Override
  public Optional<SingleTaskWorkflow> assignWorkflow(final UUID workflowId, SessionData sessionData){

    logger.debug("Assign workflow");

    final Optional<SingleTaskWorkflow> workflowOptional = this.readWorkflow(workflowId, sessionData);
    if(workflowOptional.isPresent()) {

      final SingleTaskWorkflowSaveRequest request =
              SingleTaskWorkflowSaveRequest.generateNewNoExpireDays(workflowOptional.get());
      request.setCommand(EWorkflowProcessCommand.ASSIGN);
      request.setAssignUser(sessionData.getCurrentUserId());

      this.singleTaskWorkflowClient.validate(sessionData.getRefreshToken(), request);

      final SingleTaskWorkflow result = this.singleTaskWorkflowClient.save(sessionData.getRefreshToken(), request);
      return Optional.of(this.prepareWorkflow(result, sessionData));
    }
    return Optional.empty();

  }

  @Override
  public Optional<SingleTaskWorkflow> doneWorkflow(final SingleTaskWorkflowSaveRequest saveRequest, SessionData sessionData) throws
          IOException {

    logger.debug("Make workflow done");

    saveRequest.setCommand(EWorkflowProcessCommand.DONE);
    if (saveRequest.getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getActiveAction().setComments(saveRequest.getComments());
    }

    this.singleTaskWorkflowClient.validate(sessionData.getRefreshToken(), saveRequest);

    this.prepareUploadedFiles(saveRequest, sessionData.getCurrentUserId());

    final SingleTaskWorkflow result = this.singleTaskWorkflowClient.save(sessionData.getRefreshToken(), saveRequest);
    return Optional.of(this.prepareWorkflow(result, sessionData));
  }

  @Override
  public Optional<SingleTaskWorkflow> archiveWorkflow(final SingleTaskWorkflow workflow, SessionData sessionData){

    logger.debug("Make workflow archive");

    final SingleTaskWorkflowSaveRequest request = SingleTaskWorkflowSaveRequest.generateNewNoExpireDays(workflow);
    request.setCommand(EWorkflowProcessCommand.ARCHIVE);

    this.singleTaskWorkflowClient.validate(sessionData.getRefreshToken(), request);

    final SingleTaskWorkflow result = this.singleTaskWorkflowClient.save(sessionData.getRefreshToken(), request);
    return Optional.of(this.prepareWorkflow(result, sessionData));
  }

  @Override
  public Optional<WorkflowFile> readWorkflowFile(final UUID workflowId,
                                                 final UUID fileId,
                                                 SessionData sessionData){

    Optional<SingleTaskWorkflow> workflowOptional = this.readWorkflow(workflowId, sessionData);

    if(workflowOptional.isPresent()){
      final WorkflowFile workflowFile = workflowOptional.get().getFileById(workflowId);

      return Optional.of(workflowFile);
    }

    return Optional.empty();
  }

  @Override
  protected IUploadFileManager getUploadFileManager() {

    return this.uploadFileManager;
  }

}
