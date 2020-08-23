package com.pth.workflow.services.bl.strategy.steps;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeStepEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class SelectWorkflowNextStepStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public SelectWorkflowNextStepStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException{

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final WorkflowTypeEntity processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    final WorkflowTypeStepEntity nextStep =
            this.getWorkflowSaveStrategy().findNextStep(processingWorkflowType, processingWorkflow);
    if (nextStep == null) {
      throw new WorkflowCustomizedException(String.format("Invalid workflow step id:%s", processingWorkflow.getIdentity()),
                                            EIFlowErrorType.INVALID_WORKFLOW_STEP);
    }

    processingWorkflow.setCurrentStep(nextStep);
    processingWorkflow.setCurrentStepId(nextStep.getId());

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
