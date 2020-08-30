package com.pth.gui.models.workflow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.common.edo.enums.EWorkflowActionStatus;
import com.pth.gui.models.GuiBaseModel;
import com.pth.gui.models.User;

import java.util.UUID;

@JsonIgnoreProperties(value = { "running" })
public class WorkflowAction extends GuiBaseModel {

  private UUID                workflowId;
  private UUID                assignToId;
  private UUID                currentStepId;
  private String                comments;
  private EWorkflowActionStatus status;

  private WorkflowTypeStep      currentStep;
  private User assignToUser;



  public WorkflowAction() {
    super();
  }


  public UUID getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(UUID workflowId) {
    this.workflowId = workflowId;
  }

  public UUID getAssignToId() {
    return assignToId;
  }

  public void setAssignToId(UUID assignToId) {
    this.assignToId = assignToId;
  }

  public UUID getCurrentStepId() {
    return currentStepId;
  }

  public void setCurrentStepId(UUID currentStepId) {
    this.currentStepId = currentStepId;
  }

  public String getComments() {
    return this.comments;
  }

  public void setComments(final String comments) {
    this.comments = comments;
  }

  public EWorkflowActionStatus getStatus() {
    return this.status;
  }

  public void setStatus(final int status) {
    this.status = EWorkflowActionStatus.ofValue(status);
  }

  @JsonSetter
  public void setStatus(final EWorkflowActionStatus status) {
    this.status = status;
  }

  public boolean isAssigned() {
    return this.assignToId != null;
  }

  public boolean getIsActive() {
    return EWorkflowActionStatus.getIsActive(this.getStatus().getValue());
  }

  public WorkflowTypeStep getCurrentStep() {
    return this.currentStep;
  }

  public void setCurrentStep(final WorkflowTypeStep currentStep) {
    this.currentStep = currentStep;
  }

  public User getAssignToUser() {
    return this.assignToUser;
  }

  public void setAssignToUser(final User assignToUser) {
    this.assignToUser = assignToUser;
  }

  public String getAssignToUserName() {
    return this.assignToUser != null ? this.assignToUser.getIdentity() : "";
  }

  public boolean isAssignTo(final UUID userId) {

    return this.assignToId == userId;
  }

}
