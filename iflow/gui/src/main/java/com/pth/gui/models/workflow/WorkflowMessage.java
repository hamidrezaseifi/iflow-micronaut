package com.pth.gui.models.workflow;

import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.gui.models.GuiBaseModel;
import com.pth.gui.models.workflow.workflow.Workflow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class WorkflowMessage extends GuiBaseModel {

  private UUID workflowId;

  private Workflow workflow;

  private UUID stepId;

  private WorkflowTypeStep step;

  private UUID userId;

  private String message;

  private UUID createdById;

  private EWorkflowMessageType messageType;

  private EWorkflowMessageStatus status;

  private Integer version;

  private Integer expireDays;

  private LocalDateTime createdAt;

  public WorkflowMessage() {
    super();
  }


  public UUID getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(UUID workflowId) {
    this.workflowId = workflowId;
  }

  public UUID getStepId() {
    return stepId;
  }

  public void setStepId(UUID stepId) {
    this.stepId = stepId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public UUID getCreatedById() {
    return createdById;
  }

  public void setCreatedById(UUID createdById) {
    this.createdById = createdById;
  }

  public WorkflowTypeStep getStep() {

    return this.step;
  }

  public void setStep(final WorkflowTypeStep step) {

    this.step = step;
  }

  public Workflow getWorkflow() {

    return this.workflow;
  }

  public void setWorkflow(final Workflow workflow) {

    this.workflow = workflow;
  }

  public String getMessage() {

    return this.message;
  }

  public void setMessage(final String message) {

    this.message = message;
  }

  public EWorkflowMessageType getMessageType() {

    return this.messageType;
  }

  public void setMessageType(final EWorkflowMessageType messageType) {

    this.messageType = messageType;
  }

  public EWorkflowMessageStatus getStatus() {

    return this.status;
  }

  public boolean isOffering() {

    return this.status == EWorkflowMessageStatus.OFFERING;
  }

  public void setStatus(final EWorkflowMessageStatus status) {

    this.status = status;
  }

  public Integer getVersion() {

    return this.version;
  }

  public void setVersion(final Integer version) {

    this.version = version;
  }

  public boolean isExpired() {

    return LocalDateTime.now().isAfter(this.createdAt.plusDays(this.expireDays));
  }

  public boolean isNotExpired() {

    return this.isExpired() == false;
  }

  public Integer getExpireDays() {

    return this.expireDays;
  }

  public void setExpireDays(final Integer expireDays) {

    this.expireDays = expireDays;
  }

  public LocalDateTime getCreatedAt() {

    return this.createdAt;
  }

  public String getCreatedAtString() {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");
    return this.createdAt.format(formatter);
  }

  public void setCreatedAt(final LocalDateTime createdAt) {

    this.createdAt = createdAt;
  }

  public int getRemainingDays() {

    final LocalDate deadline = this.createdAt.plusDays(this.expireDays).toLocalDate();
    final Period p = LocalDate.now().until(deadline);

    return p.getDays();
  }

  public String getIdentityPath() {

    return this.workflowId + "-" + this.stepId + "-" + this.userId;
  }

}
