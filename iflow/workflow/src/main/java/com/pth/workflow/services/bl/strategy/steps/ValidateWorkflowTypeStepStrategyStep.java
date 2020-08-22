package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.WorkflowTypeStep;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class ValidateWorkflowTypeStepStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateWorkflowTypeStepStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final WorkflowType processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    if (processingWorkflow.getCurrentStep() == null) {

      this.setWorkflowCurrentStep(processingWorkflow, processingWorkflowType);
    }

    if (processingWorkflow.getCurrentStep() == null) {

      throw new WorkflowCustomizedException("Unknown processingWorkflow step identity:" + processingWorkflow.getIdentity(),
                                            EIFlowErrorType.UNKNOWN_WORKFLOW_TYPE_STEP);
    }

    this.getWorkflowSaveStrategy().validateCurrentStepExistsInWorkflowType(processingWorkflow, processingWorkflowType);

  }

  private void setWorkflowCurrentStep(final IWorkflow workflow, final WorkflowType workflowType) {

    if (workflow.isInitializing() || workflow.isOffering()) {
      final WorkflowTypeStep firstStep = this.getWorkflowSaveStrategy().findFirstStep(workflowType);
      if (firstStep != null) {
        workflow.setCurrentStep(firstStep);
        workflow.setCurrentStepIdentity(firstStep.getIdentity());
      }
    }

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
