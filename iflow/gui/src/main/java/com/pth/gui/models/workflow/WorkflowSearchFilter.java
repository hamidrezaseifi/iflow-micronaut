package com.pth.gui.models.workflow;

import com.pth.common.edo.enums.EWorkflowStatus;

import java.util.*;
import java.util.stream.Collectors;

public class WorkflowSearchFilter {

  private boolean meAssigned;

  private Set<UUID> assignedUserIdSet = new HashSet<>();

  private Set<Integer> statusSet = new HashSet<>();

  private Set<UUID> workflowTypeIdSet = new HashSet<>();

  private Set<UUID> workflowStepIdSet = new HashSet<>();

  private UUID companyId;

  public boolean isMeAssigned() {
    return meAssigned;
  }

  public void setMeAssigned(boolean meAssigned) {
    this.meAssigned = meAssigned;
  }

  public Set<UUID> getAssignedUserIdSet() {

    return this.assignedUserIdSet;
  }

  public void setAssignedUserIdSet(final Set<UUID> assignedUserIdSet) {

    this.assignedUserIdSet = new HashSet<>();
    if (assignedUserIdSet != null) {
      this.assignedUserIdSet.addAll(assignedUserIdSet);
    }
  }

  public Set<Integer> getStatusSet() {

    return this.statusSet;
  }

  public void setStatusSet(final Set<Integer> statusSet) {

    this.statusSet = new HashSet<>();
    if (statusSet != null) {
      this.statusSet.addAll(statusSet);
    }
  }

  public Set<UUID> getWorkflowTypeIdSet() {

    return this.workflowTypeIdSet;
  }

  public void setWorkflowTypeIdSet(final Set<UUID> workflowTypeIdSet) {

    this.workflowTypeIdSet = new HashSet<>();
    if (workflowTypeIdSet != null) {
      this.workflowTypeIdSet.addAll(workflowTypeIdSet);
    }
  }

  public Set<UUID> getWorkflowStepIdSet() {

    return this.workflowStepIdSet;
  }

  public void setWorkflowStepIdSet(final Set<UUID> workflowStepIdSet) {

    this.workflowStepIdSet = new HashSet<>();
    if (workflowStepIdSet != null) {
      this.workflowStepIdSet.addAll(workflowStepIdSet);
    }
  }


  public UUID getCompanyId() {
    return companyId;
  }

  public void setCompanyId(UUID companyId) {
    this.companyId = companyId;
  }

  public void addAssignedUserId(final UUID assignedUserId) {

    this.assignedUserIdSet.add(assignedUserId);

  }

  public static WorkflowSearchFilter generateNew(final Collection<WorkflowType> workflowTypes) {

    final WorkflowSearchFilter workflowSearchFilter = new WorkflowSearchFilter();

    workflowSearchFilter.setMeAssigned(true);
    workflowSearchFilter
            .setStatusSet(Arrays.stream(EWorkflowStatus.values())
                                .filter(e -> e != EWorkflowStatus.ARCHIVED)
                                .map(e -> e.getValue()).collect(Collectors.toSet()));

    workflowSearchFilter.setWorkflowTypeIdSet(workflowTypes.stream().map(t -> t.getId()).collect(Collectors.toSet()));

    return workflowSearchFilter;
  }
}
