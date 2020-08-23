package com.pth.workflow.services.bl.strategy.steps;


import com.pth.common.edo.enums.EWorkflowActionStatus;
import com.pth.workflow.entities.WorkflowTypeEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

import java.util.UUID;

public class AssignWorkflowActiveActionStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public AssignWorkflowActiveActionStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final IWorkflowSaveRequest<W>
            processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();

    final UUID userId = processingWorkflowSaveRequest.getAssigns().get(0).getItemId();

    processingWorkflow.setActiveActionAssignTo(userId);
    processingWorkflow.setActiveActionStatus(EWorkflowActionStatus.OPEN);

  }

  @Override
  public boolean shouldProcess() {

    final WorkflowTypeEntity processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();
    final IWorkflowSaveRequest<W> processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();
    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    if (processingWorkflowSaveRequest.isDoneCommand() && processingWorkflowType.isAssignTypeManual()) {
      return this.getWorkflowSaveStrategy().IsWorkflowCurrentStepChanged() && processingWorkflow.hasActiveAction();
    }

    return processingWorkflowType.isAssignTypeManual() || processingWorkflowSaveRequest.isAssignCommand();
  }

}
