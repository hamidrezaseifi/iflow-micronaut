package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.exceptions.EIFlowErrorType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.base.IWorkflow;
import com.pth.iflow.workflow.models.base.IWorkflowSaveRequest;

public class ValidateAssignInSaveRequestStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public ValidateAssignInSaveRequestStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final IWorkflowSaveRequest<W> processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();

    if (processingWorkflowSaveRequest.getAssigns().isEmpty()) {
      throw new WorkflowCustomizedException("No assign by workflow create", EIFlowErrorType.NO_WORKFLOW_ASSIGN_CREATE_STRATEGY);
    }

  }

  @Override
  public boolean shouldProcess() {
    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final WorkflowType processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();
    final IWorkflowSaveRequest<W> processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();

    if (processingWorkflowSaveRequest.isDoneCommand()) {
      if (this.getWorkflowSaveStrategy().isLastStep(processingWorkflowType, processingWorkflow.getCurrentStep())) {
        return false;
      }
    }
    return true;
  }

}
