package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class ValidateWorkflowDetailStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateWorkflowDetailStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    if (processingWorkflow.hasController() == false) {

      throw new WorkflowCustomizedException("Invalid workflow controller!", EIFlowErrorType.INVALID_WORKFLOW_DETAIL);
    }

    if (processingWorkflow.hasCreatedBy() == false) {

      throw new WorkflowCustomizedException("Invalid workflow CreatedBy!", EIFlowErrorType.INVALID_WORKFLOW_DETAIL);
    }

    if (processingWorkflow.hasWorkflowType() == false) {

      throw new WorkflowCustomizedException("Invalid workflow WorkflowType!", EIFlowErrorType.INVALID_WORKFLOW_DETAIL);
    }

    if (processingWorkflow.isInitializing() == false && processingWorkflow.getCurrentStep() == null) {

      throw new WorkflowCustomizedException("Unknown processingWorkflow typestep", EIFlowErrorType.UNKNOWN_WORKFLOW_TYPE_STEP);
    }

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
