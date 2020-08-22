package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.pth.iflow.common.enums.EAssignType;
import com.pth.iflow.common.exceptions.IFlowMessageConversionFailureException;
import com.pth.iflow.common.models.helper.IdentityModel;
import com.pth.iflow.workflow.bl.strategy.strategies.AbstractWorkflowSaveStrategy;
import com.pth.iflow.workflow.exceptions.WorkflowCustomizedException;
import com.pth.iflow.workflow.models.AssignItem;
import com.pth.iflow.workflow.models.User;
import com.pth.iflow.workflow.models.base.IWorkflow;
import com.pth.iflow.workflow.models.base.IWorkflowSaveRequest;

public class CollectAssignedUserIdListStep<W extends IWorkflow> extends AbstractWorkflowSaveStrategyStep<W> {

  public CollectAssignedUserIdListStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {

    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException, MalformedURLException, IFlowMessageConversionFailureException {

    final IWorkflowSaveRequest<W> processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();

    final Set<String> assignedUsers = new HashSet<>();

    for (final AssignItem assign : processingWorkflowSaveRequest.getAssigns()) {

      if (assign.getItemType() == EAssignType.USER) {
        assignedUsers.add(assign.getItemIdentity());
      }

      if (assign.getItemType() == EAssignType.DEPARTMENT) {
        final List<User> departmentUserIds = this.getWorkflowSaveStrategy().getDepartmentUserList(assign.getItemIdentity());
        assignedUsers.addAll(departmentUserIds.stream().map(u -> u.getIdentity()).collect(Collectors.toSet()));
      }

    }

    if (processingWorkflowSaveRequest.isDoneCommand()) {

      final String assignToIdentity = this.getWorkflowSaveStrategy().getProcessingWorkflow().getLastAction().getAssignToIdentity();
      if (IdentityModel.isIdentityNew(assignToIdentity) == false) {
        assignedUsers.add(assignToIdentity);
      }

    }

    this.getWorkflowSaveStrategy().setAssignedUsers(assignedUsers);

  }

  @Override
  public boolean shouldProcess() {

    return true;
  }

}
