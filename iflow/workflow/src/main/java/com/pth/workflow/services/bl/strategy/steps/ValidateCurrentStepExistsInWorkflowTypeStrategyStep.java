package com.pth.workflow.services.bl.strategy.steps;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeStepEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

import java.net.MalformedURLException;

public class ValidateCurrentStepExistsInWorkflowTypeStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateCurrentStepExistsInWorkflowTypeStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final WorkflowTypeEntity processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    final boolean isValid = this.validateCurrentStepExistsInWorkflowType(processingWorkflow.getCurrentStep(), processingWorkflowType);
    if (isValid == false) {

      throw new WorkflowCustomizedException("Invalid workflow step id:" + processingWorkflow.getIdentity(),
                                            EIFlowErrorType.INVALID_WORKFLOW_STEP);

    }

  }

  private boolean validateCurrentStepExistsInWorkflowType(final WorkflowTypeStepEntity step, final WorkflowTypeEntity workflowType) {

    for (final WorkflowTypeStepEntity typeStep : workflowType.getSteps()) {
      if (typeStep.getId() == step.getId()) {
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
