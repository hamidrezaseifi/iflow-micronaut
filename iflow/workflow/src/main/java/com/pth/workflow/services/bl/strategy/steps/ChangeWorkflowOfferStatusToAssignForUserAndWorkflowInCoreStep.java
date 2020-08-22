package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.enums.EWorkflowMessageStatus;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflow;
import com.pth.iflow.workflow.models.base.IWorkflowSaveRequest;

public class ChangeWorkflowOfferStatusToAssignForUserAndWorkflowInCoreStep<W extends IWorkflow>
    extends AbstractWorkflowSaveStrategyStep<W> {

  public ChangeWorkflowOfferStatusToAssignForUserAndWorkflowInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final IWorkflowSaveRequest<W> processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();
    final W processingWorkflow = this.getWorkflowSaveStrategy().getSavedSingleWorkflow();
    final String userId = processingWorkflowSaveRequest.getAssigns().get(0).getItemIdentity();

    this.getWorkflowSaveStrategy()
        .updateUserAndWorkflowMessageStatus(processingWorkflow.getIdentity(),
                                            processingWorkflow.getCurrentStepIdentity(),
                                            userId,
                                            EWorkflowMessageStatus.ASSIGNED);

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
