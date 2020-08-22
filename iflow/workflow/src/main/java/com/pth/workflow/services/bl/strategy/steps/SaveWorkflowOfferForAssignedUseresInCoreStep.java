package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;

import com.pth.iflow.common.enums.EWorkflowMessageStatus;
import com.pth.iflow.common.enums.EWorkflowMessageType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class SaveWorkflowOfferForAssignedUseresInCoreStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public SaveWorkflowOfferForAssignedUseresInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {

    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final WorkflowType workflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    if (workflowType.isAssignTypeOffering()) {
      final W processingWorkflow = this.getWorkflowSaveStrategy().getSavedSingleWorkflow();

      for (final String userIdentity : this.getWorkflowSaveStrategy().getAssignedUsers()) {
        this
            .getWorkflowSaveStrategy()
            .createWorkflowMessage(processingWorkflow, userIdentity, EWorkflowMessageType.OFFERING_WORKFLOW,
                EWorkflowMessageStatus.OFFERING);
      }
    }
    else {
      for (final String userIdentity : this.getWorkflowSaveStrategy().getSavedWorkflowList().keySet()) {
        final W workflow = this.getWorkflowSaveStrategy().getSavedWorkflowList().get(userIdentity);
        this
            .getWorkflowSaveStrategy()
            .createWorkflowMessage(workflow, userIdentity, EWorkflowMessageType.ASSIGNED_WORKFLOW, EWorkflowMessageStatus.ASSIGNED);
      }
    }

  }

  @Override
  public boolean shouldProcess() {

    final WorkflowType processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();
    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    if (processingWorkflow.isDone() || processingWorkflow.isArchived()) {
      return false;
    }

    return this.getWorkflowSaveStrategy().getAssignedUsers().isEmpty() == false; // processingWorkflowType.isAssignTypeOffering();
  }

}
