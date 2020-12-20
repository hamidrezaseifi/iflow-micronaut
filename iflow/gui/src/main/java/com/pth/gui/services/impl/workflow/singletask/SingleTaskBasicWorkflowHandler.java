package com.pth.gui.services.impl.workflow.singletask;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowListEdo;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.mapper.ISingleTaskWorkflowMapper;
import com.pth.gui.mapper.ISingleTaskWorkflowSaveRequestMapper;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowFile;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflowSaveRequest;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.IBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.ISingleTaskBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.base.WorkflowHandlerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pth.clients.clients.workflow.ISingleTaskWorkflowClient;

import javax.inject.Singleton;


@Singleton
public class SingleTaskBasicWorkflowHandler
        extends WorkflowHandlerHelper<SingleTaskWorkflow>
        implements ISingleTaskBasicWorkflowHandler {

  private static final Logger logger = LoggerFactory.getLogger(SingleTaskBasicWorkflowHandler.class);

  private final ISingleTaskWorkflowClient singleTaskWorkflowClient;
  private final ISingleTaskWorkflowMapper singleTaskWorkflowMapper;
  private final ISingleTaskWorkflowSaveRequestMapper singleTaskWorkflowSaveRequestMapper;
  private final IUploadFileManager uploadFileManager;

  public SingleTaskBasicWorkflowHandler(ISingleTaskWorkflowClient singleTaskWorkflowClient,
                                        ISingleTaskWorkflowMapper singleTaskWorkflowMapper,
                                        ISingleTaskWorkflowSaveRequestMapper singleTaskWorkflowSaveRequestMapper,
                                        IUploadFileManager uploadFileManager) {

    this.singleTaskWorkflowClient = singleTaskWorkflowClient;
    this.singleTaskWorkflowMapper = singleTaskWorkflowMapper;
    this.singleTaskWorkflowSaveRequestMapper = singleTaskWorkflowSaveRequestMapper;
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
                                                 SessionData sessionData){

    logger.debug("Create workflow");

    createRequest.setCommand(EWorkflowProcessCommand.CREATE);

    createRequest.getWorkflow().getWorkflow().setComments(createRequest.getComments());

    if (createRequest.getWorkflow().getWorkflow().getHasActiveAction()) {
      createRequest.getWorkflow().getWorkflow().getActiveAction().setComments(createRequest.getComments());
    }

    this.singleTaskWorkflowClient.validate(sessionData.getRefreshToken(),
                                           singleTaskWorkflowSaveRequestMapper.toEdo(createRequest));

    this.prepareUploadedFiles(createRequest, sessionData.getCurrentUserId());

    Optional<SingleTaskWorkflowListEdo> singleTaskWorkflowListEdoOptional =
            this.singleTaskWorkflowClient.create(sessionData.getRefreshToken(),
                                                 singleTaskWorkflowSaveRequestMapper.toEdo(createRequest));
    if(singleTaskWorkflowListEdoOptional.isPresent()){
      final List<SingleTaskWorkflow> resultList =
              singleTaskWorkflowMapper.fromEdoList(singleTaskWorkflowListEdoOptional.get().getWorkflows());

      return this.prepareWorkflowList(resultList, sessionData);
    }

    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<SingleTaskWorkflow> saveWorkflow(final SingleTaskWorkflowSaveRequest saveRequest,
                                                   SessionData sessionData){

    logger.debug("Save workflow");

    if (saveRequest.getWorkflow().getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getWorkflow().getActiveAction().setCurrentStepId(saveRequest.getWorkflow().getWorkflow().getCurrentStepId());
    }

    saveRequest.setCommand(EWorkflowProcessCommand.SAVE);
    if (saveRequest.getWorkflow().getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getWorkflow().getActiveAction().setComments(saveRequest.getComments());
    }

    this.singleTaskWorkflowClient.validate(sessionData.getRefreshToken(),
                                           singleTaskWorkflowSaveRequestMapper.toEdo(saveRequest));

    this.prepareUploadedFiles(saveRequest, sessionData.getCurrentUserId());

    Optional<SingleTaskWorkflowEdo> singleTaskWorkflowEdoOptional =
            this.singleTaskWorkflowClient.save(sessionData.getRefreshToken(),
                                               singleTaskWorkflowSaveRequestMapper.toEdo(saveRequest));
    if(singleTaskWorkflowEdoOptional.isPresent()){
      final SingleTaskWorkflow resultWorkflow =
              singleTaskWorkflowMapper.fromEdo(singleTaskWorkflowEdoOptional.get());

      return Optional.of(this.prepareWorkflow(resultWorkflow, sessionData));
    }

    throw new GuiCustomizedException("error by saving workflow");
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

      this.singleTaskWorkflowClient.validate(sessionData.getRefreshToken(),
                                             singleTaskWorkflowSaveRequestMapper.toEdo(request));

      Optional<SingleTaskWorkflowEdo> singleTaskWorkflowEdoOptional =
              this.singleTaskWorkflowClient.save(sessionData.getRefreshToken(),
                                                 singleTaskWorkflowSaveRequestMapper.toEdo(request));
      if(singleTaskWorkflowEdoOptional.isPresent()){
        final SingleTaskWorkflow resultWorkflow =
                singleTaskWorkflowMapper.fromEdo(singleTaskWorkflowEdoOptional.get());

        return Optional.of(this.prepareWorkflow(resultWorkflow, sessionData));
      }

      throw new GuiCustomizedException("error by saving workflow");
    }
    return Optional.empty();

  }

  @Override
  public Optional<SingleTaskWorkflow> doneWorkflow(final SingleTaskWorkflowSaveRequest saveRequest,
                                                   SessionData sessionData){

    logger.debug("Make workflow done");

    saveRequest.setCommand(EWorkflowProcessCommand.DONE);
    if (saveRequest.getWorkflow().getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getWorkflow().getActiveAction().setComments(saveRequest.getComments());
    }

    this.singleTaskWorkflowClient.validate(sessionData.getRefreshToken(),
                                           singleTaskWorkflowSaveRequestMapper.toEdo(saveRequest));

    this.prepareUploadedFiles(saveRequest, sessionData.getCurrentUserId());

    Optional<SingleTaskWorkflowEdo> singleTaskWorkflowEdoOptional =
            this.singleTaskWorkflowClient.save(sessionData.getRefreshToken(),
                                               singleTaskWorkflowSaveRequestMapper.toEdo(saveRequest));
    if(singleTaskWorkflowEdoOptional.isPresent()){
      final SingleTaskWorkflow resultWorkflow =
              singleTaskWorkflowMapper.fromEdo(singleTaskWorkflowEdoOptional.get());

      return Optional.of(this.prepareWorkflow(resultWorkflow, sessionData));
    }

    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<SingleTaskWorkflow> archiveWorkflow(final SingleTaskWorkflow workflow, SessionData sessionData){

    logger.debug("Make workflow archive");

    final SingleTaskWorkflowSaveRequest request = SingleTaskWorkflowSaveRequest.generateNewNoExpireDays(workflow);
    request.setCommand(EWorkflowProcessCommand.ARCHIVE);

    this.singleTaskWorkflowClient.validate(sessionData.getRefreshToken(),
                                           singleTaskWorkflowSaveRequestMapper.toEdo(request));

    Optional<SingleTaskWorkflowEdo> singleTaskWorkflowEdoOptional =
            this.singleTaskWorkflowClient.save(sessionData.getRefreshToken(),
                                               singleTaskWorkflowSaveRequestMapper.toEdo(request));
    if(singleTaskWorkflowEdoOptional.isPresent()){
      final SingleTaskWorkflow resultWorkflow = singleTaskWorkflowMapper.fromEdo(singleTaskWorkflowEdoOptional.get());

      return Optional.of(this.prepareWorkflow(resultWorkflow, sessionData));
    }

    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<WorkflowFile> readWorkflowFile(final UUID workflowId,
                                                 final UUID fileId,
                                                 SessionData sessionData){

    Optional<SingleTaskWorkflow> workflowOptional = this.readWorkflow(workflowId, sessionData);

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
