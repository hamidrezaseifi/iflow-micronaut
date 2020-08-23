package com.pth.workflow.services.bl.strategy.steps;


import com.pth.common.edo.enums.EAssignType;
import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class ValidateSingleUserAssignInSaveRequestStrategyStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateSingleUserAssignInSaveRequestStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final IWorkflowSaveRequest<W>
            processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();

    if (processingWorkflowSaveRequest.getAssigns().size() != 1
        || processingWorkflowSaveRequest.getAssigns().get(0).getItemType() != EAssignType.USER) {
      throw new WorkflowCustomizedException("Invalid assign list or item", EIFlowErrorType.INVALID_WORKFLOW_ASSIGN_LIST);

    }

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
