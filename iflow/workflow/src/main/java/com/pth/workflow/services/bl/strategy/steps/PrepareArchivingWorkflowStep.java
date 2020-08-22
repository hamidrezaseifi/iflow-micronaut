package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.enums.EWorkflowStatus;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class PrepareArchivingWorkflowStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public PrepareArchivingWorkflowStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    processingWorkflow.setStatus(EWorkflowStatus.ARCHIVED);

  }

  @Override
  public boolean shouldProcess() {
    return true;
  }

}
