package com.pth.common.edo;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class WorkflowSearchFilterEdo {

  @NotNull(message = "AssignedUserIdentitySet must not be null")
  private Set<String> assignedUserIdentitySet = new HashSet<>();

  @NotNull(message = "StatusSet must not be null")
  private Set<Integer> statusSet = new HashSet<>();

  @NotNull(message = "WorkflowTypeIdentitySet must not be null")
  private Set<String> workflowTypeIdentitySet = new HashSet<>();

  @NotNull(message = "WorkflowStepIdentitySet must not be null")
  private Set<String> workflowStepeIdentitySet = new HashSet<>();

  @NotNull(message = "CompanyIdentity must not be null")
  private String companyIdentity;

  public Set<String> getAssignedUserIdentitySet() {

    return this.assignedUserIdentitySet;
  }

  @JsonSetter
  public void setAssignedUserIdentitySet(final Set<String> assignedUserIdentitySet) {

    this.assignedUserIdentitySet = new HashSet<>();
    if (assignedUserIdentitySet != null) {
      this.assignedUserIdentitySet.addAll(assignedUserIdentitySet);
    }
  }

  public Set<Integer> getStatusSet() {

    return this.statusSet;
  }

  @JsonSetter
  public void setStatusSet(final Set<Integer> statusSet) {

    this.statusSet = new HashSet<>();
    if (statusSet != null) {
      this.statusSet.addAll(statusSet);
    }
  }

  public Set<String> getWorkflowTypeIdentitySet() {

    return this.workflowTypeIdentitySet;
  }

  @JsonSetter
  public void setWorkflowTypeIdentitySet(final Set<String> workflowTypeIdentitySet) {

    this.workflowTypeIdentitySet = new HashSet<>();
    if (workflowTypeIdentitySet != null) {
      this.workflowTypeIdentitySet.addAll(workflowTypeIdentitySet);
    }
  }

  public Set<String> getWorkflowStepeIdentitySet() {

    return this.workflowStepeIdentitySet;
  }

  @JsonSetter
  public void setWorkflowStepeIdentitySet(final Set<String> workflowStepeIdentitySet) {

    this.workflowStepeIdentitySet = new HashSet<>();
    if (workflowStepeIdentitySet != null) {
      this.workflowStepeIdentitySet.addAll(workflowStepeIdentitySet);
    }
  }

  public String getCompanyIdentity() {

    return this.companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
  }

}
