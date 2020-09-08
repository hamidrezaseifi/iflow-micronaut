package com.pth.gui.services.impl.workflow.workflow;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.mapper.ISingleTaskWorkflowMapper;
import com.pth.gui.mapper.IWorkflowMapper;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowFile;
import com.pth.gui.models.workflow.workflow.Workflow;
import com.pth.gui.models.workflow.workflow.WorkflowSaveRequest;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.IWorkflowHandler;
import com.pth.gui.services.impl.workflow.base.WorkflowHandlerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pth.clients.clients.workflow.IWorkflowClient;

import javax.inject.Singleton;

@Singleton
public class WorkflowHandler extends WorkflowHandlerHelper<Workflow> implements IWorkflowHandler<Workflow, WorkflowSaveRequest> {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowHandler.class);

  private final IWorkflowClient workflowAccess;
  private final IWorkflowMapper workflowMapper;
  private final IUploadFileManager uploadFileManager;

  public WorkflowHandler(IWorkflowClient workflowAccess,
                         IWorkflowMapper workflowMapper,
                                   IUploadFileManager uploadFileManager) {

    this.workflowAccess = workflowAccess;
    this.workflowMapper = workflowMapper;
    this.uploadFileManager = uploadFileManager;
  }

  @Override
  public Optional<Workflow> readWorkflow(final UUID workflowId, SessionData sessionData){

    logger.debug("Read workflow {}", workflowId);

    Optional<WorkflowEdo> workflowEdoOptional =
            workflowAccess.read(sessionData.getRefreshToken() , workflowId);

    if(workflowEdoOptional.isPresent()){
      final Workflow workflow = workflowMapper.fromEdo(workflowEdoOptional.get());
      return Optional.of(this.prepareWorkflow(workflow, sessionData));
    }
    return Optional.empty();
  }

  @Override
  public List<Workflow> createWorkflow(final WorkflowSaveRequest createRequest,
                                                 SessionData sessionData) throws IOException {
    throw new GuiCustomizedException("not implemented");
  }

  @Override
  public Optional<Workflow> saveWorkflow(final WorkflowSaveRequest saveRequest, SessionData sessionData) throws
          IOException {

    logger.debug("Save workflow");

    throw new GuiCustomizedException("not implemented");
  }

  @Override
  public Optional<Workflow> assignWorkflow(final UUID workflowId, SessionData sessionData){

    logger.debug("Assign workflow");

    throw new GuiCustomizedException("not implemented");

  }

  @Override
  public Optional<Workflow> doneWorkflow(final WorkflowSaveRequest saveRequest, SessionData sessionData) throws
          IOException {

    logger.debug("Make workflow done");

    throw new GuiCustomizedException("not implemented");
  }

  @Override
  public Optional<Workflow> archiveWorkflow(final Workflow workflow, SessionData sessionData){

    logger.debug("Make workflow archive");

    throw new GuiCustomizedException("not implemented");
  }

  @Override
  public Optional<WorkflowFile> readWorkflowFile(final UUID workflowId,
                                                 final UUID fileId,
                                                 SessionData sessionData){

    throw new GuiCustomizedException("not implemented");
  }

  @Override
  protected IUploadFileManager getUploadFileManager() {

    return this.uploadFileManager;
  }

}
