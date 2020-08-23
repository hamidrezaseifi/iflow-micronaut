package com.pth.workflow.services.bl.strategy.steps;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class ValidateWorkflowAssignedUserStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateWorkflowAssignedUserStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    if (processingWorkflow.getActiveAction().isAssigned() == false) {
      throw new WorkflowCustomizedException("The workflow has not been assigned identity:" + processingWorkflow.getIdentity(),
                                            EIFlowErrorType.UNKNOWN_WORKFLOW_ASSIGN);

    }

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
