package com.pth.common.edo;

import com.pth.common.edo.enums.EWorkflowActionStatus;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class WorkflowActionEdo {


  @NotNull(message = "id must not be null")
  protected UUID id;

  private String  assignToIdentity;

  @NotNull(message = "CurrentStepIdentity must not be null")
  private String  currentStepIdentity;

  private String  comments;

  @NotNull(message = "Status must not be null")
  private Integer status;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getAssignToIdentity() {
    return this.assignToIdentity;
  }

  public void setAssignToIdentity(final String assignToIdentity) {
    this.assignToIdentity = assignToIdentity;
  }

  public String getCurrentStepIdentity() {
    return this.currentStepIdentity;
  }

  public void setCurrentStepIdentity(final String currectStepIdentity) {
    this.currentStepIdentity = currectStepIdentity;
  }

  public String getComments() {
    return this.comments;
  }

  public void setComments(final String comments) {
    this.comments = comments;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(final Integer status) {
    this.status = status;
  }

  public boolean getIsActive() {
    return EWorkflowActionStatus.getIsActive(this.status);
  }

}
