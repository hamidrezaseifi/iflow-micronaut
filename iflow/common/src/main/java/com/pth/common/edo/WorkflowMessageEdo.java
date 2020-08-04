package com.pth.common.edo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pth.common.edo.contants.IsoFormats;
import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.common.edo.validation.AEnumValueValidator;

public class WorkflowMessageEdo {

  @NotNull(message = "WorkflowIdentity must not be null")
  private String        workflowIdentity;

  @NotNull(message = "StepIdentity must not be null")
  private String        stepIdentity;

  @NotNull(message = "UserId must not be null")
  private String        userIdentity;

  private String        message;

  @NotNull(message = "CreatedByIdentity must not be null")
  private String        createdByIdentity;

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

  public String getWorkflowIdentity() {
    return this.workflowIdentity;
  }

  public void setWorkflowIdentity(final String workflowIdentity) {
    this.workflowIdentity = workflowIdentity;
  }

  public String getStepIdentity() {
    return this.stepIdentity;
  }

  public void setStepIdentity(final String stepIdentity) {
    this.stepIdentity = stepIdentity;
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
