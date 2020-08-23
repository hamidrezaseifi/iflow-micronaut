package com.pth.workflow.services.bl.strategy.steps;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class ValidateWorkflowActiveActionStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateWorkflowActiveActionStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    if (processingWorkflow.hasActiveAction() == false) {
      throw new WorkflowCustomizedException("The workflow has no active action identity:" + processingWorkflow.getIdentity(),
                                            EIFlowErrorType.INVALID_WORKFLOW_STATUS);

    }

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
