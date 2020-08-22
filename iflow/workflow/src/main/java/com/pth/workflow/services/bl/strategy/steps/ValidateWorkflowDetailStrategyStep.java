package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class ValidateWorkflowDetailStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateWorkflowDetailStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

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
