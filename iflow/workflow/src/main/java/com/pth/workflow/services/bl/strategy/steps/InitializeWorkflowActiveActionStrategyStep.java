package com.pth.workflow.services.bl.strategy.steps;

import com.pth.workflow.entities.WorkflowActionEntity;
import com.pth.workflow.entities.WorkflowTypeEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class InitializeWorkflowActiveActionStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public InitializeWorkflowActiveActionStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    final WorkflowActionEntity action = this.getWorkflowSaveStrategy().initialNextStep(processingWorkflow);
    if (action != null) {
      processingWorkflow.addAction(action);
    }

  }

  @Override
  public boolean shouldProcess() {
    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final WorkflowTypeEntity processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();
    final WorkflowActionEntity prevAction = this.getWorkflowSaveStrategy().getPrevActiveAction();

    if (prevAction != null && this.getWorkflowSaveStrategy().isLastStep(processingWorkflowType, prevAction.getCurrentStep())) {
      return false;
    }
    boolean hasActiveAction = processingWorkflow.hasActiveAction();
    return hasActiveAction == false;
  }

}
