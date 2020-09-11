package com.pth.gui.services.impl.workflow.testthree;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowListEdo;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.mapper.ITestThreeTaskWorkflowMapper;
import com.pth.gui.mapper.ITestThreeTaskWorkflowSaveRequestMapper;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowFile;
import com.pth.gui.models.workflow.testthree.TestThreeTaskWorkflow;
import com.pth.gui.models.workflow.testthree.TestThreeTaskWorkflowSaveRequest;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.IBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.ITestThreeTaskBasicWorkflowHandler;
import com.pth.gui.services.impl.workflow.base.WorkflowHandlerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pth.clients.clients.workflow.ITestThreeTaskWorkflowClient;

import javax.inject.Singleton;


@Singleton
public class TestThreeTaskBasicWorkflowHandler
        extends WorkflowHandlerHelper<TestThreeTaskWorkflow>
        implements ITestThreeTaskBasicWorkflowHandler {

  private static final Logger logger = LoggerFactory.getLogger(TestThreeTaskBasicWorkflowHandler.class);

  private final ITestThreeTaskWorkflowClient testThreeTaskWorkClient;
  private final ITestThreeTaskWorkflowMapper testThreeTaskWorkMapper;
  private final ITestThreeTaskWorkflowSaveRequestMapper testThreeTaskWorkflowSaveRequestMapper;
  private final IUploadFileManager uploadFileManager;

  public TestThreeTaskBasicWorkflowHandler(ITestThreeTaskWorkflowClient testThreeTaskWorkClient,
                                           ITestThreeTaskWorkflowMapper testThreeTaskWorkMapper,
                                           ITestThreeTaskWorkflowSaveRequestMapper testThreeTaskWorkflowSaveRequestMapper,
                                           IUploadFileManager uploadFileManager) {

    this.testThreeTaskWorkClient = testThreeTaskWorkClient;
    this.testThreeTaskWorkMapper = testThreeTaskWorkMapper;
    this.testThreeTaskWorkflowSaveRequestMapper = testThreeTaskWorkflowSaveRequestMapper;
    this.uploadFileManager = uploadFileManager;
  }

  @Override
  public Optional<TestThreeTaskWorkflow> readWorkflow(final UUID workflowId, SessionData sessionData){

    logger.debug("Read workflow {}", workflowId);

    Optional<TestThreeTaskWorkflowEdo> workflowEdoOptional =
            testThreeTaskWorkClient.read(sessionData.getRefreshToken() , workflowId);

    if(workflowEdoOptional.isPresent()){
      final TestThreeTaskWorkflow workflow = testThreeTaskWorkMapper.fromEdo(workflowEdoOptional.get());
      return Optional.of(this.prepareWorkflow(workflow, sessionData));
    }
    return Optional.empty();
  }

  @Override
  public List<TestThreeTaskWorkflow> createWorkflow(final TestThreeTaskWorkflowSaveRequest createRequest,
                                                 SessionData sessionData) throws IOException {

    logger.debug("Create workflow");

    createRequest.setCommand(EWorkflowProcessCommand.CREATE);

    createRequest.getWorkflow().setComments(createRequest.getComments());
    if (createRequest.getWorkflow().getHasActiveAction()) {
      createRequest.getWorkflow().getActiveAction().setComments(createRequest.getComments());
    }

    this.testThreeTaskWorkClient.validate(sessionData.getRefreshToken(),
                                           testThreeTaskWorkflowSaveRequestMapper.toEdo(createRequest));

    this.prepareUploadedFiles(createRequest, sessionData.getCurrentUserId());

    Optional<TestThreeTaskWorkflowListEdo> singleTaskWorkflowListEdoOptional =
            this.testThreeTaskWorkClient.create(sessionData.getRefreshToken(),
                                                 testThreeTaskWorkflowSaveRequestMapper.toEdo(createRequest));
    if(singleTaskWorkflowListEdoOptional.isPresent()){
      final List<TestThreeTaskWorkflow> resultList =
              testThreeTaskWorkMapper.fromEdoList(singleTaskWorkflowListEdoOptional.get().getWorkflows());

      return this.prepareWorkflowList(resultList, sessionData);
    }

    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<TestThreeTaskWorkflow> saveWorkflow(final TestThreeTaskWorkflowSaveRequest saveRequest,
                                                   SessionData sessionData) throws IOException {

    logger.debug("Save workflow");

    if (saveRequest.getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getActiveAction().setCurrentStepId(saveRequest.getWorkflow().getCurrentStepId());
    }

    saveRequest.setCommand(EWorkflowProcessCommand.SAVE);
    if (saveRequest.getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getActiveAction().setComments(saveRequest.getComments());
    }

    this.testThreeTaskWorkClient.validate(sessionData.getRefreshToken(),
                                           testThreeTaskWorkflowSaveRequestMapper.toEdo(saveRequest));

    this.prepareUploadedFiles(saveRequest, sessionData.getCurrentUserId());

    Optional<TestThreeTaskWorkflowEdo> singleTaskWorkflowEdoOptional =
            this.testThreeTaskWorkClient.save(sessionData.getRefreshToken(),
                                               testThreeTaskWorkflowSaveRequestMapper.toEdo(saveRequest));
    if(singleTaskWorkflowEdoOptional.isPresent()){
      final TestThreeTaskWorkflow resultWorkflow = testThreeTaskWorkMapper.fromEdo(singleTaskWorkflowEdoOptional.get());

      return Optional.of(this.prepareWorkflow(resultWorkflow, sessionData));
    }

    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<TestThreeTaskWorkflow> assignWorkflow(final UUID workflowId, SessionData sessionData){

    logger.debug("Assign workflow");

    final Optional<TestThreeTaskWorkflow> workflowOptional = this.readWorkflow(workflowId, sessionData);
    if(workflowOptional.isPresent()) {

      final TestThreeTaskWorkflowSaveRequest request =
              TestThreeTaskWorkflowSaveRequest.generateNewNoExpireDays(workflowOptional.get());
      request.setCommand(EWorkflowProcessCommand.ASSIGN);
      request.setAssignUser(sessionData.getCurrentUserId());

      this.testThreeTaskWorkClient.validate(sessionData.getRefreshToken(), testThreeTaskWorkflowSaveRequestMapper.toEdo(request));

      Optional<TestThreeTaskWorkflowEdo> singleTaskWorkflowEdoOptional =
              this.testThreeTaskWorkClient.save(sessionData.getRefreshToken(),
                                                 testThreeTaskWorkflowSaveRequestMapper.toEdo(request));
      if(singleTaskWorkflowEdoOptional.isPresent()){
        final TestThreeTaskWorkflow resultWorkflow = testThreeTaskWorkMapper.fromEdo(singleTaskWorkflowEdoOptional.get());

        return Optional.of(this.prepareWorkflow(resultWorkflow, sessionData));
      }

      throw new GuiCustomizedException("error by saving workflow");
    }
    return Optional.empty();

  }

  @Override
  public Optional<TestThreeTaskWorkflow> doneWorkflow(final TestThreeTaskWorkflowSaveRequest saveRequest, SessionData sessionData) throws
                                                                                                                             IOException {

    logger.debug("Make workflow done");

    saveRequest.setCommand(EWorkflowProcessCommand.DONE);
    if (saveRequest.getWorkflow().getHasActiveAction()) {
      saveRequest.getWorkflow().getActiveAction().setComments(saveRequest.getComments());
    }

    this.testThreeTaskWorkClient.validate(sessionData.getRefreshToken(), testThreeTaskWorkflowSaveRequestMapper.toEdo(saveRequest));

    this.prepareUploadedFiles(saveRequest, sessionData.getCurrentUserId());

    Optional<TestThreeTaskWorkflowEdo> singleTaskWorkflowEdoOptional =
            this.testThreeTaskWorkClient.save(sessionData.getRefreshToken(),
                                               testThreeTaskWorkflowSaveRequestMapper.toEdo(saveRequest));
    if(singleTaskWorkflowEdoOptional.isPresent()){
      final TestThreeTaskWorkflow resultWorkflow = testThreeTaskWorkMapper.fromEdo(singleTaskWorkflowEdoOptional.get());

      return Optional.of(this.prepareWorkflow(resultWorkflow, sessionData));
    }

    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<TestThreeTaskWorkflow> archiveWorkflow(final TestThreeTaskWorkflow workflow, SessionData sessionData){

    logger.debug("Make workflow archive");

    final TestThreeTaskWorkflowSaveRequest request = TestThreeTaskWorkflowSaveRequest.generateNewNoExpireDays(workflow);
    request.setCommand(EWorkflowProcessCommand.ARCHIVE);

    this.testThreeTaskWorkClient.validate(sessionData.getRefreshToken(), testThreeTaskWorkflowSaveRequestMapper.toEdo(request));

    Optional<TestThreeTaskWorkflowEdo> singleTaskWorkflowEdoOptional =
            this.testThreeTaskWorkClient.save(sessionData.getRefreshToken(),
                                               testThreeTaskWorkflowSaveRequestMapper.toEdo(request));
    if(singleTaskWorkflowEdoOptional.isPresent()){
      final TestThreeTaskWorkflow resultWorkflow = testThreeTaskWorkMapper.fromEdo(singleTaskWorkflowEdoOptional.get());

      return Optional.of(this.prepareWorkflow(resultWorkflow, sessionData));
    }

    throw new GuiCustomizedException("error by saving workflow");
  }

  @Override
  public Optional<WorkflowFile> readWorkflowFile(final UUID workflowId,
                                                 final UUID fileId,
                                                 SessionData sessionData){

    Optional<TestThreeTaskWorkflow> workflowOptional = this.readWorkflow(workflowId, sessionData);

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
