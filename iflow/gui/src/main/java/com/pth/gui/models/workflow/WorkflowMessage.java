package com.pth.gui.models.workflow;

import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.gui.models.GuiBaseModel;
import com.pth.gui.models.workflow.workflow.Workflow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class WorkflowMessage extends GuiBaseModel {

  private String workflowIdentity;

  private Workflow workflow;

  private String stepIdentity;

  private WorkflowTypeStep step;

  private String userIdentity;

  private String message;

  private String createdByIdentity;

  private EWorkflowMessageType messageType;

  private EWorkflowMessageStatus status;

  private Integer version;

  private Integer expireDays;

  private LocalDateTime createdAt;

  public WorkflowMessage() {
    super();
  }

  public String getWorkflowIdentity() {

    return this.workflowIdentity;
  }

  public void setWorkflowIdentity(final String workflowIdentity) {

    this.workflowIdentity = workflowIdentity;
  }

  public String getStepIdentity() {

    return this.stepIdentity;
  }

  public void setStepIdentity(final String stepId) {

    this.stepIdentity = stepId;
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

  public String getUserIdentity() {

    return this.userIdentity;
  }

  public void setUserIdentity(final String userIdentity) {

    this.userIdentity = userIdentity;
  }

  public String getMessage() {

    return this.message;
  }

  public void setMessage(final String message) {

    this.message = message;
  }

  public String getCreatedByIdentity() {

    return this.createdByIdentity;
  }

  public void setCreatedByIdentity(final String createdByIdentity) {

    this.createdByIdentity = createdByIdentity;
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

    return this.workflowIdentity + "-" + this.stepIdentity + "-" + this.userIdentity;
  }

}
