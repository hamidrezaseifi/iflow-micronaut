package com.pth.workflow.services.bl.strategy.steps;

import com.pth.common.edo.enums.EWorkflowStatus;
import com.pth.workflow.entities.workflow.WorkflowActionEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class SelectWorkflowStatusStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public SelectWorkflowStatusStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final AbstractWorkflowSaveStrategy<W> strategy = this.getWorkflowSaveStrategy();
    final W processingWorkflow = strategy.getProcessingWorkflow();
    final WorkflowTypeEntity processingWorkflowType = strategy.getProcessingWorkflowType();
    final WorkflowActionEntity activeAction = strategy.getActiveAction();

    if (strategy.IsWorkflowCurrentStepChanged() == false) {
      if (strategy.isLastStep(processingWorkflowType, processingWorkflow.getCurrentStep())) {
        processingWorkflow.setStatus(EWorkflowStatus.DONE);
      }
    }
    else {
      if (activeAction.isAssigned() == false) {
        processingWorkflow.setStatus(EWorkflowStatus.NOT_ASSIGNED);
      }
    }

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
