package com.pth.workflow.services.bl.strategy.steps;

import com.pth.workflow.entities.WorkflowActionEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class InitializeWorkflowInitialActionStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public InitializeWorkflowInitialActionStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {

    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    if (processingWorkflow.hasAction() == false) {
      final WorkflowActionEntity action = this.getWorkflowSaveStrategy().getInitialStepAction(processingWorkflow);

      processingWorkflow.addAction(action);
      processingWorkflow.setCurrentStepId(action.getCurrentStepId());
      processingWorkflow.setCurrentStep(action.getCurrentStep());
    }

  }

  @Override
  public boolean shouldProcess() {

    return true;
  }

}
