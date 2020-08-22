package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import com.pth.iflow.common.enums.EWorkflowActionStatus;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.base.IWorkflow;
import com.pth.iflow.workflow.models.base.IWorkflowSaveRequest;

public class AssignWorkflowActiveActionStrategyStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public AssignWorkflowActiveActionStrategyStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {
    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();
    final IWorkflowSaveRequest<W> processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();

    final String userId = processingWorkflowSaveRequest.getAssigns().get(0).getItemIdentity();

    processingWorkflow.setActiveActionAssignTo(userId);
    processingWorkflow.setActiveActionStatus(EWorkflowActionStatus.OPEN);

  }

  @Override
  public boolean shouldProcess() {

    final WorkflowType processingWorkflowType = this.getWorkflowSaveStrategy().getProcessingWorkflowType();
    final IWorkflowSaveRequest<W> processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();
    final W processingWorkflow = this.getWorkflowSaveStrategy().getProcessingWorkflow();

    if (processingWorkflowSaveRequest.isDoneCommand() && processingWorkflowType.isAssignTypeManual()) {
      return this.getWorkflowSaveStrategy().IsWorkflowCurrectStepChanged() && processingWorkflow.hasActiveAction();
    }

    return processingWorkflowType.isAssignTypeManual() || processingWorkflowSaveRequest.isAssignCommand();
  }

}
