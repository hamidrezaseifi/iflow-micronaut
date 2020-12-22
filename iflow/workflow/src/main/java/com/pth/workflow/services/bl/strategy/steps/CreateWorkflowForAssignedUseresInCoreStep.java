package com.pth.workflow.services.bl.strategy.steps;


import com.pth.common.edo.enums.EWorkflowActionStatus;
import com.pth.common.edo.enums.EWorkflowStatus;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

import java.util.Optional;
import java.util.UUID;

public class CreateWorkflowForAssignedUseresInCoreStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public CreateWorkflowForAssignedUseresInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {

    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    // final List<W> result = new ArrayList<>();

    for (final UUID userId : this.getWorkflowSaveStrategy().getAssignedUsers()) {
      processingWorkflow.setActiveActionAssignTo(userId);
      processingWorkflow.setActiveActionStatus(EWorkflowActionStatus.OPEN);
      processingWorkflow.setStatus(EWorkflowStatus.ASSIGNED);

      final Optional<W> savedWorkflowOptional = this.getWorkflowSaveStrategy().createWorkflow(processingWorkflow);
      if(savedWorkflowOptional.isPresent()){
        this.getWorkflowSaveStrategy().addSavedWorkflowToList(userId, savedWorkflowOptional.get());
      }

    }

    // this.getWorkflowSaveStrategy().setSavedWorkflowList(result);

  }

  @Override
  public boolean shouldProcess() {

    return true;
  }

}
