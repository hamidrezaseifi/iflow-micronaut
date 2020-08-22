package com.pth.workflow.models;

public class CompanyWorkflowTypeController {

  private String workflowTypeIdentity;

  private String userIdentity;

  private Integer priority;

  public CompanyWorkflowTypeController() {

  }

  public CompanyWorkflowTypeController(final String workflowTypeIdentity, final String userIdentity, final Integer priority) {

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
