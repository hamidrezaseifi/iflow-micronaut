package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.enums.EAssignType;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflow;
import com.pth.iflow.workflow.models.base.IWorkflowSaveRequest;

public class ValidateSingleUserAssignInSaveRequestStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateSingleUserAssignInSaveRequestStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final IWorkflowSaveRequest<W> processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();

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
