package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class SendWorkflowOffersForUsersToProfileStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public SendWorkflowOffersForUsersToProfileStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {

    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final WorkflowType workflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    final String companyIdentity = this.getWorkflowSaveStrategy().getProcessingWorkflow().getCompanyIdentity();

    if (workflowType.isAssignTypeOffering()) {
      final W processingWorkflow = this.getWorkflowSaveStrategy().getSavedSingleWorkflow();

      this.getWorkflowSaveStrategy().resetWorkflowtCachData(companyIdentity, processingWorkflow.getIdentity());
    }
    else {
      for (final String userIdentity : this.getWorkflowSaveStrategy().getSavedWorkflowList().keySet()) {
        final W workflow = this.getWorkflowSaveStrategy().getSavedWorkflowList().get(userIdentity);
        this.getWorkflowSaveStrategy().resetWorkflowtCachData(companyIdentity, workflow.getIdentity());
      }
    }
  }

  @Override
  public boolean shouldProcess() {

    final WorkflowType workflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    if (workflowType.isAssignTypeOffering()) {
      return true;
    }
    else {
      return this.getWorkflowSaveStrategy().getAssignedUsers().isEmpty() == false;

    }
    // return true; // this.getWorkflowSaveStrategy().getAssignedUsers().isEmpty() == false; //
    // processingWorkflowType.isAssignTypeOffering();
  }

}
