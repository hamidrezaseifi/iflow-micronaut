package com.pth.workflow.services.bl.strategy.steps;

import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

import java.util.Optional;
import java.util.UUID;

public class SaveWorkflowOfferForAssignedUseresInCoreStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public SaveWorkflowOfferForAssignedUseresInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {

    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final WorkflowTypeEntity workflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    if (workflowType.isAssignTypeOffering()) {
      final Optional<W> processingWorkflowOptional = this.getWorkflowSaveStrategy().getSavedSingleWorkflow();

      if(processingWorkflowOptional.isPresent()){
        W processingWorkflow = processingWorkflowOptional.get();
        for (final UUID userId : this.getWorkflowSaveStrategy().getAssignedUsers()) {
          this.getWorkflowSaveStrategy().createWorkflowMessage(processingWorkflow,
                                                               userId,
                                                               EWorkflowMessageType.OFFERING_WORKFLOW,
                                                               EWorkflowMessageStatus.OFFERING);
        }
      }

    }
    else {
      for (final UUID userId : this.getWorkflowSaveStrategy().getSavedWorkflowList().keySet()) {
        final W workflow = this.getWorkflowSaveStrategy().getSavedWorkflowList().get(userId);
        this.getWorkflowSaveStrategy().createWorkflowMessage(workflow,
                                                             userId,
                                                             EWorkflowMessageType.ASSIGNED_WORKFLOW,
                                                             EWorkflowMessageStatus.ASSIGNED);
      }
    }

  }

  @Override
  public boolean shouldProcess() {

    final WorkflowTypeEntity processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();
    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    if (processingWorkflow.isDone() || processingWorkflow.isArchived()) {
      return false;
    }

    return this.getWorkflowSaveStrategy().getAssignedUsers().isEmpty() == false;
  }

}
