package com.pth.workflow.services.bl.strategy.steps;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.WorkflowTypeEntity;
import com.pth.workflow.entities.WorkflowTypeStepEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class ValidateWorkflowTypeStepStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateWorkflowTypeStepStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException{

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final WorkflowTypeEntity processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    if (processingWorkflow.getCurrentStep() == null) {

      this.setWorkflowCurrentStep(processingWorkflow, processingWorkflowType);
    }

    if (processingWorkflow.getCurrentStep() == null) {

      throw new WorkflowCustomizedException("Unknown processingWorkflow step identity:" + processingWorkflow.getIdentity(),
                                            EIFlowErrorType.UNKNOWN_WORKFLOW_TYPE_STEP);
    }

    this.getWorkflowSaveStrategy().validateCurrentStepExistsInWorkflowType(processingWorkflow, processingWorkflowType);

  }

  private void setWorkflowCurrentStep(final IWorkflowBaseEntity workflow, final WorkflowTypeEntity workflowType) {

    if (workflow.isInitializing() || workflow.isOffering()) {
      final WorkflowTypeStepEntity firstStep = this.getWorkflowSaveStrategy().findFirstStep(workflowType);
      if (firstStep != null) {
        workflow.setCurrentStep(firstStep);
        workflow.setCurrentStepId(firstStep.getId());
      }
    }

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
