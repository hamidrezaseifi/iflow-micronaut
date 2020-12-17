package com.pth.common.edo;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class WorkflowSearchFilterEdo {

  @NotNull(message = "AssignedUserIdSet must not be null")
  private Set<UUID> assignedUserIdSet = new HashSet<>();

  @NotNull(message = "StatusSet must not be null")
  private Set<Integer> statusSet = new HashSet<>();

  @NotNull(message = "WorkflowTypeIdSet must not be null")
  private Set<UUID> workflowTypeIdSet = new HashSet<>();

  @NotNull(message = "WorkflowStepIdSet must not be null")
  private Set<UUID> workflowStepeIdSet = new HashSet<>();

  @NotNull(message = "CompanyId must not be null")
  private UUID companyId;

  public Set<UUID> getAssignedUserIdSet() {

    return this.assignedUserIdSet;
  }

  @JsonSetter
  public void setAssignedUserIdSet(final Set<UUID> assignedUserIdSet) {

    this.assignedUserIdSet = new HashSet<>();
    if (assignedUserIdSet != null) {
      this.assignedUserIdSet.addAll(assignedUserIdSet);
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

  public Set<UUID> getWorkflowTypeIdSet() {

    return this.workflowTypeIdSet;
  }

  @JsonSetter
  public void setWorkflowTypeIdSet(final Set<UUID> workflowTypeIdSet) {

    this.workflowTypeIdSet = new HashSet<>();
    if (workflowTypeIdSet != null) {
      this.workflowTypeIdSet.addAll(workflowTypeIdSet);
    }
  }

  public Set<UUID> getWorkflowStepeIdSet() {

    return this.workflowStepeIdSet;
  }

  @JsonSetter
  public void setWorkflowStepeIdSet(final Set<UUID> workflowStepeIdSet) {

    this.workflowStepeIdSet = new HashSet<>();
    if (workflowStepeIdSet != null) {
      this.workflowStepeIdSet.addAll(workflowStepeIdSet);
    }
  }

  public UUID getCompanyId() {

    return this.companyId;
  }

  public void setCompanyId(final UUID companyId) {

    this.companyId = companyId;
  }

}
