package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowAction;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class InitializeWorkflowActiveActionStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public InitializeWorkflowActiveActionStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    final WorkflowAction action = this.getWorkflowSaveStrategy().initialNextStep(processingWorkflow);
    if (action != null) {
      processingWorkflow.addAction(action);
    }

  }

  @Override
  public boolean shouldProcess() {
    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final WorkflowType processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();
    final WorkflowAction prevAction = this.getWorkflowSaveStrategy().getPrevActiveAction();

    if (prevAction != null && this.getWorkflowSaveStrategy().isLastStep(processingWorkflowType, prevAction.getCurrentStep())) {
      return false;
    }
    return processingWorkflow.hasActiveAction() == false;
  }

}
