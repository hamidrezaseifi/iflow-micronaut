package com.pth.workflow.services.bl.strategy.steps;

import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

import java.util.Optional;

public class UpdateWorkflowInCoreStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  private final boolean addToSavedWorkflowList;

  public UpdateWorkflowInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy, final boolean addToSavedWorkflowList) {

    super(workflowSaveStrategy);
    this.addToSavedWorkflowList = addToSavedWorkflowList;
  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    final Optional<W> existingWorkflowOptional =
            this.getWorkflowSaveStrategy().getWorkflowRepository().getByWorkflowId(processingWorkflow.getWorkflowId());

    if(existingWorkflowOptional.isPresent()){
      W existingWorkflow = existingWorkflowOptional.get();
      existingWorkflow.fill(processingWorkflow);

      final Optional<W> savedWorkflowOptional = this.getWorkflowSaveStrategy().updateWorkflow(existingWorkflow);

      if(savedWorkflowOptional.isPresent()){
        W savedWorkflow = savedWorkflowOptional.get();
        this.getWorkflowSaveStrategy().setSavedSingleWorkflowOptional(savedWorkflow);

        if (this.addToSavedWorkflowList) {
          this.getWorkflowSaveStrategy().addSavedWorkflowToList(null, savedWorkflow);
        }
      }

    }
    else{
      throw new WorkflowCustomizedException("Workflow not found: " + processingWorkflow.getWorkflowId().toString(),
                                            EIFlowErrorType.WORKFLOW_NOTFOUND);
    }



  }

  @Override
  public boolean shouldProcess() {

    return true;
  }

}
