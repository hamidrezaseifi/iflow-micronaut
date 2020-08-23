package com.pth.workflow.services.bl.strategy.steps;

import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

import java.util.Optional;
import java.util.UUID;

public class SendWorkflowOffersForUsersToProfileStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public SendWorkflowOffersForUsersToProfileStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {

    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final WorkflowTypeEntity workflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    final UUID companyId = this.getWorkflowSaveStrategy().getProcessingWorkflow().getCompanyId();

    if (workflowType.isAssignTypeOffering()) {
      final Optional<W> processingWorkflowOptional = this.getWorkflowSaveStrategy().getSavedSingleWorkflow();

      if(processingWorkflowOptional.isPresent()){
        this.getWorkflowSaveStrategy().resetWorkflowtCachData(companyId,
                                                              processingWorkflowOptional.get().getWorkflowId());
      }

    }
    else {
      for (final UUID userId : this.getWorkflowSaveStrategy().getSavedWorkflowList().keySet()) {
        final W workflow = this.getWorkflowSaveStrategy().getSavedWorkflowList().get(userId);
        this.getWorkflowSaveStrategy().resetWorkflowtCachData(companyId, workflow.getWorkflowId());
      }
    }
  }

  @Override
  public boolean shouldProcess() {

    final WorkflowTypeEntity workflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();

    if (workflowType.isAssignTypeOffering()) {
      return true;
    }
    else {
      return this.getWorkflowSaveStrategy().getAssignedUsers().isEmpty() == false;

    }
    // return true; // this.getWorkflowSaveStrategy().getAssignedUsers().isEmpty() == false; //
    // processingWorkflowType.isAssignTypeOffering();
  }

}
