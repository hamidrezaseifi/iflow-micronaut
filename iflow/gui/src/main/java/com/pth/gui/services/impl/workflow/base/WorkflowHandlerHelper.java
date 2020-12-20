package com.pth.gui.services.impl.workflow.base;

import com.pth.gui.models.gui.FileSavingData;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.*;
import com.pth.gui.models.workflow.base.WorkflowBased;
import com.pth.gui.models.workflow.workflow.Workflow;
import com.pth.gui.services.IUploadFileManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class WorkflowHandlerHelper<W extends WorkflowBased> {

  // private static final boolean MANAGE_UPLOADFILES_IS_DISABLED = false;

  protected void prepareUploadedFiles(final IWorkflowSaveRequest<W> createRequest, UUID userId) {

    final List<FileSavingData> archiveList = this.getUploadFileManager().moveFromTempToArchive(createRequest.getUploadedFiles());

    createRequest.getWorkflow().getWorkflow().clearFiles();

    for (final FileSavingData savedArchiveFile : archiveList) {

      createRequest
          .getWorkflow()
          .getWorkflow()
          .addNewFile(savedArchiveFile.getFilePath(),
                      userId,
                      savedArchiveFile.getTitle(),
                      savedArchiveFile.getFileExtention(),
                      "");
    }

  }

  protected List<W> prepareWorkflowList(final List<W> pureWorkflowList, SessionData sessionData){

    final List<W> workflowList = new ArrayList<>();

    if (pureWorkflowList != null) {
      for (final W workflow : pureWorkflowList) {
        workflowList.add(this.prepareWorkflow(workflow, sessionData));
      }
    }

    return workflowList;
  }

  protected Map<UUID, WorkflowTypeStep> getIdMapedSteps(final WorkflowType workflowType) {

    final Map<UUID, WorkflowTypeStep> list = workflowType.getSteps().stream().collect(Collectors.toMap(s -> s.getId(), s -> s));

    return list;
  }

  protected WorkflowTypeStep findStepByIdInWorkflowType(final UUID stepId, final WorkflowType workflowType) {

    final Map<UUID, WorkflowTypeStep> steps = this.getIdMapedSteps(workflowType);

    if (steps.containsKey(stepId)) {
      return steps.get(stepId);
    }
    else {
      return null;
    }
  }

  protected W prepareWorkflow(final W workflow, SessionData sessionData) {

    Workflow workflowBase = workflow.getWorkflow();

    workflowBase.setWorkflowType(sessionData.findWorkflowType(workflowBase.getWorkflowTypeId()));
    workflowBase.setCreatedByUser(sessionData.findUser(workflowBase.getCreatedById()));
    workflowBase.setControllerUser(sessionData.findUser(workflowBase.getControllerId()));
    workflowBase.setCurrentUserId(sessionData.getUser().getCurrentUser().getId());
    workflowBase.setCurrentStep(this.findStepByIdInWorkflowType(workflowBase.getCurrentStepId(), workflowBase.getWorkflowType()));

    this.prepareWorkflowActions(workflow, sessionData);

    return workflow;
  }

  protected W prepareWorkflowActions(final W workflow, SessionData sessionData){
    Workflow workflowBase = workflow.getWorkflow();

    for (final WorkflowAction action : workflowBase.getActions()) {
      action.setAssignToUser(sessionData.findUser(action.getAssignToId()));
      action.setCurrentStep(this.findStepByIdInWorkflowType(action.getCurrentStepId(), workflowBase.getWorkflowType()));
    }

    return workflow;
  }

  protected abstract IUploadFileManager getUploadFileManager();

}
