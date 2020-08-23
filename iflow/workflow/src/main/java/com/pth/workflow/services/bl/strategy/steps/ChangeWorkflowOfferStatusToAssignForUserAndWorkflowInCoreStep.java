package com.pth.workflow.services.bl.strategy.steps;


import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

import java.util.Optional;
import java.util.UUID;

public class ChangeWorkflowOfferStatusToAssignForUserAndWorkflowInCoreStep<W extends IWorkflowBaseEntity>
    extends AbstractWorkflowSaveStrategyStep<W> {

  public ChangeWorkflowOfferStatusToAssignForUserAndWorkflowInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final IWorkflowSaveRequest<W>
            processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();
    final Optional<W> processingWorkflowOptional = this.getWorkflowSaveStrategy().getSavedSingleWorkflowOptional();
    if(processingWorkflowOptional.isPresent()){
      final UUID userId = processingWorkflowSaveRequest.getAssigns().get(0).getItemId();

      this.getWorkflowSaveStrategy()
          .updateUserAndWorkflowMessageStatus(processingWorkflowOptional.get().getWorkflowId(),
                                              processingWorkflowOptional.get().getCurrentStepId(),
                                              userId,
                                              EWorkflowMessageStatus.ASSIGNED);
    }


  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
