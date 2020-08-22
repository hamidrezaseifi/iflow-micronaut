package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.enums.EWorkflowStatus;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowAction;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class SelectWorkflowStatusStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public SelectWorkflowStatusStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final AbstractWorkflowSaveStrategy<W> strategy = this.getWorkflowSaveStrategy();
    final W processingWorkflow = strategy.getProcessingWorkflow();
    final WorkflowType processingWorkflowType = strategy.getProcessingWorkflowType();
    final WorkflowAction activeAction = strategy.getActiveAction();

    if (strategy.IsWorkflowCurrectStepChanged() == false) {
      if (strategy.isLastStep(processingWorkflowType, processingWorkflow.getCurrentStep())) {
        processingWorkflow.setStatus(EWorkflowStatus.DONE);
      }
    }
    else {
      if (activeAction.isAssigned() == false) {
        processingWorkflow.setStatus(EWorkflowStatus.NOT_ASSIGNED);
      }
    }

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
