package com.pth.common.edo;

import com.pth.common.edo.enums.EWorkflowActionStatus;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class WorkflowActionEdo {


  @NotNull(message = "id must not be null")
  protected UUID id;

  private UUID assignToId;

  @NotNull(message = "CurrentStepId must not be null")
  private UUID currentStepId;

  private String  comments;

  @NotNull(message = "Status must not be null")
  private Integer status;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getAssignToId() {
    return this.assignToId;
  }

  public void setAssignToId(final UUID assignToId) {
    this.assignToId = assignToId;
  }

  public UUID getCurrentStepId() {
    return this.currentStepId;
  }

  public void setCurrentStepId(final UUID currentStepId) {
    this.currentStepId = currentStepId;
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
