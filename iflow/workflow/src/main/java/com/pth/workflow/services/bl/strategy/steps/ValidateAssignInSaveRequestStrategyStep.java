package com.pth.workflow.services.bl.strategy.steps;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.entities.WorkflowTypeEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class ValidateAssignInSaveRequestStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateAssignInSaveRequestStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException{

    final IWorkflowSaveRequest<W>
            processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();

    if (processingWorkflowSaveRequest.getAssigns().isEmpty()) {
      throw new WorkflowCustomizedException("No assign by workflow create", EIFlowErrorType.NO_WORKFLOW_ASSIGN_CREATE_STRATEGY);
    }

  }

  @Override
  public boolean shouldProcess() {
    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final WorkflowTypeEntity processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();
    final IWorkflowSaveRequest<W> processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();

    if (processingWorkflowSaveRequest.isDoneCommand()) {
      if (this.getWorkflowSaveStrategy().isLastStep(processingWorkflowType, processingWorkflow.getCurrentStep())) {
        return false;
      }
    }
    return true;
  }

}
