package com.pth.workflow.models;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class WorkflowSearchFilter {

  private Set<UUID> assignedUserIdSet = new HashSet<>();

  private Set<Integer> statusSet = new HashSet<>();

  private Set<UUID> workflowTypeIdSet = new HashSet<>();

  private Set<UUID> workflowStepIdSet = new HashSet<>();

  private UUID companyId;

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


  public Set<UUID> getWorkflowStepIdSet() {

    return this.workflowStepIdSet;
  }

  public void setWorkflowStepIdSet(final Set<UUID> workflowStepIdSet) {

    this.workflowStepIdSet = new HashSet<>();
    if (workflowStepIdSet != null) {
      this.workflowStepIdSet.addAll(workflowStepIdSet);
    }
  }

  public UUID getCompanyIdentity() {

    return companyId;
  }

  public void setCompanyIdentity(final UUID companyIdentity) {

    this.companyId = companyIdentity;
  }

}
