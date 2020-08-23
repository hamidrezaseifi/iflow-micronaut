package com.pth.workflow.services.bl.strategy.steps;


import com.pth.common.edo.enums.EWorkflowActionStatus;
import com.pth.workflow.entities.WorkflowActionEntity;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class PrepareDoneActiveActionStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public PrepareDoneActiveActionStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final WorkflowActionEntity activeAction = this.getWorkflowSaveStrategy().getActiveAction();

    activeAction.setStatus(EWorkflowActionStatus.DONE);

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
