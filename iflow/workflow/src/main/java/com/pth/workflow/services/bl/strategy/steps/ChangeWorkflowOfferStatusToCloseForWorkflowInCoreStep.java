package com.pth.workflow.services.bl.strategy.steps;


import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.workflow.entities.WorkflowActionEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

import java.util.Optional;
import java.util.UUID;

public class ChangeWorkflowOfferStatusToCloseForWorkflowInCoreStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public ChangeWorkflowOfferStatusToCloseForWorkflowInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final Optional<W> processingWorkflowOptional = this.getWorkflowSaveStrategy().getSavedSingleWorkflowOptional();
    if(processingWorkflowOptional.isPresent()){
      W processingWorkflow = processingWorkflowOptional.get();
      final WorkflowActionEntity prevAction = this.getWorkflowSaveStrategy().getPrevActiveAction();
      final UUID stepId = prevAction != null ? prevAction.getCurrentStepId() : processingWorkflow.getCurrentStepId();

      this.getWorkflowSaveStrategy()
          .updateWorkflowMessageStatus(processingWorkflow.getWorkflowId(),
                                       stepId,
                                       EWorkflowMessageStatus.CLOSED);
    }


  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
