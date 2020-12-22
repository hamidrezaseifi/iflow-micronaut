package com.pth.workflow.services.bl.strategy.steps;

import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

import java.util.Optional;

public class CreateWorkflowInCoreStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  private final boolean addToSavedWorkflowList;

  public CreateWorkflowInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy, final boolean addToSavedWorkflowList) {

    super(workflowSaveStrategy);
    this.addToSavedWorkflowList = addToSavedWorkflowList;
  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    final Optional<W> savedWorkflowOptional = this.getWorkflowSaveStrategy().createWorkflow(processingWorkflow);

    if(savedWorkflowOptional.isPresent()){
      W savedWorkflow = savedWorkflowOptional.get();
      this.getWorkflowSaveStrategy().setSavedSingleWorkflowOptional(savedWorkflow);

      if (this.addToSavedWorkflowList) {
        this.getWorkflowSaveStrategy().addSavedWorkflowToList(null, savedWorkflow);
      }
    }

  }

  @Override
  public boolean shouldProcess() {

    return true;
  }

}
