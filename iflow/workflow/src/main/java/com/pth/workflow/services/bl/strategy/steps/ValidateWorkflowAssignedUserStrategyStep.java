package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class ValidateWorkflowAssignedUserStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateWorkflowAssignedUserStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

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
