package com.pth.workflow.services.bl.strategy.steps;

import com.pth.iflow.workflow.bl.strategy.IWorkflowSaveStrategyStep;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.models.base.IWorkflow;

public abstract class AbstractWorkflowSaveStrategyStep<W extends IWorkflow> implements IWorkflowSaveStrategyStep {

  private final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy;

  public AbstractWorkflowSaveStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    this.workflowSaveStrategy = workflowSaveStrategy;
  }

  public boolean hasNextStep(final IWorkflowSaveStrategyStep currentStep) {
    return workflowSaveStrategy.hasNextStep(currentStep);
  }

  public AbstractWorkflowSaveStrategy<W> getWorkflowSaveStrategy() {
    return workflowSaveStrategy;
  }

}
