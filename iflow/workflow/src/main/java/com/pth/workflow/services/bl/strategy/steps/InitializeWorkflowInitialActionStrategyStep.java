package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowAction;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class InitializeWorkflowInitialActionStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public InitializeWorkflowInitialActionStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {

    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    if (processingWorkflow.hasAction() == false) {
      final WorkflowAction action = this.getWorkflowSaveStrategy().getInitialStepAction(processingWorkflow);

      processingWorkflow.addAction(action);
      processingWorkflow.setCurrentStepIdentity(action.getCurrentStepIdentity());
      processingWorkflow.setCurrentStep(action.getCurrentStep());
    }

  }

  @Override
  public boolean shouldProcess() {

    return true;
  }

}
