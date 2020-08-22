package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.WorkflowTypeStep;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class ValidateWorkflowNextStepStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateWorkflowNextStepStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final WorkflowType processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    final WorkflowTypeStep nextStep = this.getWorkflowSaveStrategy().findNextStep(processingWorkflowType, processingWorkflow);
    if (nextStep == null) {
      throw new WorkflowCustomizedException("Invalid workflow step identity:" + processingWorkflow.getIdentity(),
                                            EIFlowErrorType.INVALID_WORKFLOW_STEP);
    }

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
