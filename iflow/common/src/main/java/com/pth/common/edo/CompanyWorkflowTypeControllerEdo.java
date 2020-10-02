package com.pth.common.edo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class CompanyWorkflowTypeControllerEdo {

  @NotNull(message = "Id must not be null")
  private UUID id;

  @NotNull(message = "WorkflowTypeIdentity must not be null")
  private String workflowTypeIdentity;

  @NotNull(message = "UserIdentity must not be null")
  private String userIdentity;

  @NotNull(message = "Priority must not be null")
  private Integer priority;

  public CompanyWorkflowTypeControllerEdo() {

  }

  public CompanyWorkflowTypeControllerEdo(final String workflowTypeIdentity, final String userIdentity, final Integer priority) {

    this.workflowTypeIdentity = workflowTypeIdentity;
    this.userIdentity = userIdentity;
    this.priority = priority;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getWorkflowTypeIdentity() {

    return this.workflowTypeIdentity;
  }

  public void setWorkflowTypeIdentity(final String workflowTypeIdentity) {

    this.workflowTypeIdentity = workflowTypeIdentity;
  }

  public String getUserIdentity() {

    return this.userIdentity;
  }

  public void setUserIdentity(final String userIdentity) {

    this.userIdentity = userIdentity;
  }

  public Integer getPriority() {

    return this.priority;
  }

  public void setPriority(final Integer priority) {

    this.priority = priority;
  }

}
