package com.pth.workflow.services.bl.strategy.steps;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.pth.common.edo.enums.EAssignType;
import com.pth.workflow.exceptions.WorkflowCustomizedException;
import com.pth.workflow.models.AssignItem;
import com.pth.workflow.models.User;
import com.pth.workflow.models.base.IWorkflowBaseEntity;
import com.pth.workflow.models.base.IWorkflowSaveRequest;
import com.pth.workflow.services.bl.strategy.strategies.AbstractWorkflowSaveStrategy;

public class CollectAssignedUserIdListStep<W extends IWorkflowBaseEntity> extends AbstractWorkflowSaveStrategyStep<W> {

  public CollectAssignedUserIdListStep(final AbstractWorkflowSaveStrategy<W> workflowSaveStrategy) {

    super(workflowSaveStrategy);

  }

  @Override
  public void process() throws WorkflowCustomizedException {

    final IWorkflowSaveRequest<W>
            processingWorkflowSaveRequest = this.getWorkflowSaveStrategy().getProcessingWorkflowSaveRequest();

    final Set<UUID> assignedUserIdList = new HashSet<>();

    for (final AssignItem assign : processingWorkflowSaveRequest.getAssigns()) {

      if (assign.getItemType() == EAssignType.USER) {
        assignedUserIdList.add(assign.getItemId());
      }

      if (assign.getItemType() == EAssignType.DEPARTMENT) {
        final List<User> departmentUserIds = this.getWorkflowSaveStrategy().getDepartmentUserList(assign.getItemId());
        assignedUserIdList.addAll(departmentUserIds.stream().map(u -> u.getId()).collect(Collectors.toSet()));
      }

    }

    if (processingWorkflowSaveRequest.isDoneCommand()) {

      final UUID assignToId =
              this.getWorkflowSaveStrategy().getProcessingWorkflow().getLastAction().getAssignToId();
      if (assignToId != null) {
        assignedUserIdList.add(assignToId);
      }

    }

    this.getWorkflowSaveStrategy().setAssignedUsers(assignedUserIdList);

  }

  @Override
  public boolean shouldProcess() {

    return true;
  }

}
