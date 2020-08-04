package com.pth.common.edo;

import javax.validation.constraints.NotNull;

public class CompanyWorkflowTypeControllerEdo {

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
