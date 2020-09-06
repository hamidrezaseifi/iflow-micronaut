package com.pth.common.edo;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pth.common.edo.contants.IsoFormats;
import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.common.edo.validation.AEnumValueValidator;

public class WorkflowMessageEdo {


  @NotNull(message = "id must not be null")
  protected UUID id;

  @NotNull(message = "WorkflowIdentity must not be null")
  private UUID        workflowId;

  @NotNull(message = "StepIdentity must not be null")
  private UUID        stepId;

  @NotNull(message = "UserId must not be null")
  private UUID        userId;

  private String        message;

  @NotNull(message = "CreatedByIdentity must not be null")
  private UUID createdById;

  @NotNull(message = "MessageType must not be null")
  @AEnumValueValidator(enumClazz = EWorkflowMessageType.class)
  private Integer       messageType;

  @NotNull(message = "status must not be null")
  @AEnumValueValidator(enumClazz = EWorkflowMessageStatus.class)
  private Integer       status;

  @NotNull(message = "version must not be null")
  private Integer       version;

  @NotNull(message = "expired must not be null")
  private Integer       expireDays;

  @JsonFormat(pattern = IsoFormats.DATETIME_FORMAT_ISO)
  private LocalDateTime createdAt;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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

  public String getMessage() {
    return this.message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public Integer getMessageType() {
    return this.messageType;
  }

  public void setMessageType(final Integer messageType) {
    this.messageType = messageType;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(final Integer status) {
    this.status = status;
  }

  public Integer getVersion() {
    return this.version;
  }

  public void setVersion(final Integer version) {
    this.version = version;
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

  public void setCreatedAt(final LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

}
