package com.pth.gui.services.impl.workflow.workflow;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.gui.mapper.IWorkflowMapper;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowAction;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.models.workflow.WorkflowTypeStep;
import com.pth.gui.models.workflow.workflow.Workflow;
import com.pth.gui.services.impl.workflow.IWorkflowHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pth.clients.clients.workflow.IWorkflowClient;

import javax.inject.Singleton;

@Singleton
public class WorkflowHandler implements IWorkflowHandler {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowHandler.class);

  private final IWorkflowClient workflowClient;
  private final IWorkflowMapper workflowMapper;

  public WorkflowHandler(IWorkflowClient workflowClient,
                         IWorkflowMapper workflowMapper) {

    this.workflowClient = workflowClient;
    this.workflowMapper = workflowMapper;
  }

  @Override
  public Optional<Workflow> readWorkflow(final UUID workflowId, SessionData sessionData){

    logger.debug("Read workflow {}", workflowId);

    Optional<WorkflowEdo> workflowEdoOptional =
            workflowClient.read(sessionData.getRefreshToken() , workflowId);

    if(workflowEdoOptional.isPresent()){
      final Workflow workflow = workflowMapper.fromEdo(workflowEdoOptional.get());
      return Optional.of(this.prepareWorkflow(workflow, sessionData));
    }
    return Optional.empty();
  }


  private Workflow prepareWorkflow(final Workflow workflow, SessionData sessionData) {

    workflow.setWorkflowType(sessionData.findWorkflowType(workflow.getWorkflowTypeId()));
    workflow.setCreatedByUser(sessionData.findUser(workflow.getCreatedById()));
    workflow.setControllerUser(sessionData.findUser(workflow.getControllerId()));
    workflow.setCurrentUserId(sessionData.getUser().getCurrentUser().getId());
    workflow.setCurrentStep(this.findStepByIdInWorkflowType(workflow.getCurrentStepId(), workflow.getWorkflowType()));

    this.prepareWorkflowActions(workflow, sessionData);

    return workflow;
  }

  private Workflow prepareWorkflowActions(final Workflow workflow, SessionData sessionData){
    
    for (final WorkflowAction action : workflow.getActions()) {
      action.setAssignToUser(sessionData.findUser(action.getAssignToId()));
      action.setCurrentStep(this.findStepByIdInWorkflowType(action.getCurrentStepId(), workflow.getWorkflowType()));
    }

    return workflow;
  }

  private WorkflowTypeStep findStepByIdInWorkflowType(final UUID stepId, final WorkflowType workflowType) {

    final Map<UUID, WorkflowTypeStep> steps = this.getIdMapedSteps(workflowType);

    if (steps.containsKey(stepId)) {
      return steps.get(stepId);
    }
    else {
      return null;
    }
  }

  protected Map<UUID, WorkflowTypeStep> getIdMapedSteps(final WorkflowType workflowType) {

    final Map<UUID, WorkflowTypeStep> list = workflowType.getSteps().stream().collect(
            Collectors.toMap(s -> s.getId(), s -> s));

    return list;
  }
}
