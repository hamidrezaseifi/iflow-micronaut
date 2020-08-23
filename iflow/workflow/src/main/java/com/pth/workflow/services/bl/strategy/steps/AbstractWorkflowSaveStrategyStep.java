package com.pth.workflow.services.bl.strategy.steps;

import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.IWorkflowSaveStrategyStep;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public abstract class AbstractWorkflowSaveStrategyStep<W extends IWorkflowBaseEntity> implements
                                                                                      IWorkflowSaveStrategyStep {

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
