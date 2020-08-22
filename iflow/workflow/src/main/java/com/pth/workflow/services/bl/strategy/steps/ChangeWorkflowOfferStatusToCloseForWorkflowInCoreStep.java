package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.enums.EWorkflowMessageStatus;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowAction;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class ChangeWorkflowOfferStatusToCloseForWorkflowInCoreStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public ChangeWorkflowOfferStatusToCloseForWorkflowInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getSavedSingleWorkflow();
    final WorkflowAction prevAction = this.getWorkflowSaveStrategy().getPrevActiveAction();
    final String stepId = prevAction != null ? prevAction.getCurrentStepIdentity() : processingWorkflow.getCurrentStepIdentity();

    this.getWorkflowSaveStrategy()
        .updateWorkflowMessageStatus(processingWorkflow.getIdentity(),
                                     stepId,
                                     EWorkflowMessageStatus.CLOSED);

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
