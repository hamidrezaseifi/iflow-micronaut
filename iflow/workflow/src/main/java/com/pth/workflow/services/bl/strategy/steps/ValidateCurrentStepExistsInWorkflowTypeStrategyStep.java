package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.WorkflowTypeStep;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class ValidateCurrentStepExistsInWorkflowTypeStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateCurrentStepExistsInWorkflowTypeStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final WorkflowType processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    final boolean isValid = this.validateCurrentStepExistsInWorkflowType(processingWorkflow.getCurrentStep(), processingWorkflowType);
    if (isValid == false) {

      throw new WorkflowCustomizedException("Invalid workflow step id:" + processingWorkflow.getIdentity(),
                                            EIFlowErrorType.INVALID_WORKFLOW_STEP);

    }

  }

  private boolean validateCurrentStepExistsInWorkflowType(final WorkflowTypeStep step, final WorkflowType workflowType) {

    for (final WorkflowTypeStep typeStep : workflowType.getSteps()) {
      if (typeStep.hasSameIdentity(step.getIdentity())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
