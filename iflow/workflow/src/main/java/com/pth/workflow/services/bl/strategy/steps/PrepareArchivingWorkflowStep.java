package com.pth.workflow.services.bl.strategy.steps;


import com.pth.common.edo.enums.EWorkflowStatus;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class PrepareArchivingWorkflowStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public PrepareArchivingWorkflowStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    processingWorkflow.setStatus(EWorkflowStatus.ARCHIVED);

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
