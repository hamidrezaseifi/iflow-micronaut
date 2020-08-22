package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;

import com.pth.iflow.common.enums.EWorkflowActionStatus;
import com.pth.iflow.common.enums.EWorkflowStatus;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.base.IWorkflow;

public class SaveWorkflowForAssignedUseresInCoreStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public SaveWorkflowForAssignedUseresInCoreStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {

    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    // final List<W> result = new ArrayList<>();

    for (final String userIdentity : this.getWorkflowSaveStrategy().getAssignedUsers()) {
      processingWorkflow.setActiveActionAssignTo(userIdentity);
      processingWorkflow.setActiveActionStatus(EWorkflowActionStatus.OPEN);
      processingWorkflow.setStatus(EWorkflowStatus.ASSIGNED);

      final W savedWorkflow = this.getWorkflowSaveStrategy().saveWorkflow(processingWorkflow);
      this.getWorkflowSaveStrategy().addSavedWorkflowToList(userIdentity, savedWorkflow);
    }

    // this.getWorkflowSaveStrategy().setSavedWorkflowList(result);

  }

  @Override
  public boolean shouldProcess() {

    return true;
  }

}
