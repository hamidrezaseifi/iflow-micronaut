package com.pth.workflow.models;

import java.util.HashSet;
import java.util.Set;

public class WorkflowSearchFilter {

  private Set<String> assignedUserIdentitySet = new HashSet<>();

  private Set<Integer> statusSet = new HashSet<>();

  private Set<String> workflowTypeIdentitySet = new HashSet<>();

  private Set<String> workflowStepIdentitySet = new HashSet<>();

  private String companyIdentity;

  public Set<String> getAssignedUserIdentitySet() {

    return this.assignedUserIdentitySet;
  }

  public void setAssignedUserIdentitySet(final Set<String> assignedUserIdSet) {

    this.assignedUserIdentitySet = new HashSet<>();
    if (assignedUserIdSet != null) {
      this.assignedUserIdentitySet.addAll(assignedUserIdSet);
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

  public Set<String> getWorkflowTypeIdentitySet() {

    return this.workflowTypeIdentitySet;
  }

  public void setWorkflowTypeIdentitySet(final Set<String> workflowTypeIdSet) {

    this.workflowTypeIdentitySet = new HashSet<>();
    if (workflowTypeIdSet != null) {
      this.workflowTypeIdentitySet.addAll(workflowTypeIdSet);
    }
  }

  public Set<String> getWorkflowStepIdentitySet() {

    return this.workflowStepIdentitySet;
  }

  public void setWorkflowStepIdentitySet(final Set<String> workflowStepIdSet) {

    this.workflowStepIdentitySet = new HashSet<>();
    if (workflowStepIdSet != null) {
      this.workflowStepIdentitySet.addAll(workflowStepIdSet);
    }
  }

  public String getCompanyIdentity() {

    return companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
  }

}
