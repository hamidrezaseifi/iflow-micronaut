package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.enums.EWorkflowActionStatus;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowAction;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class PrepareDoneActiveActionStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public PrepareDoneActiveActionStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final WorkflowAction activeAction = this.getWorkflowSaveStrategy().getActiveAction();

    activeAction.setStatus(EWorkflowActionStatus.DONE);

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
