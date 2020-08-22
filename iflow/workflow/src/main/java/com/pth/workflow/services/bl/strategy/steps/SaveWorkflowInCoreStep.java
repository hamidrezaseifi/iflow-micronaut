package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;

import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class SaveWorkflowInCoreStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  private final boolean addToSavedWorkflowList;

  public SaveWorkflowInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy, final boolean addToSavedWorkflowList) {

    super(workflowSaveStrategy);
    this.addToSavedWorkflowList = addToSavedWorkflowList;
  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    final W savedWorkflow = this.getWorkflowSaveStrategy().saveWorkflow(processingWorkflow);

    // this.getWorkflowSaveStrategy().setProcessingWorkflow(savedWorkflow);

    this.getWorkflowSaveStrategy().setSavedSingleWorkflow(savedWorkflow);

    if (this.addToSavedWorkflowList) {
      this.getWorkflowSaveStrategy().addSavedWorkflowToList("not-set", savedWorkflow);
    }
  }

  @Override
  public boolean shouldProcess() {

    return true;
  }

}
