package com.pth.iflow.profile.model;

import java.time.LocalDateTime;

import com.pth.iflow.common.enums.EWorkflowMessageStatus;
import com.pth.iflow.common.enums.EWorkflowMessageType;

public class WorkflowMessage {

  private String                 workflowIdentity;
  private String                 stepIdentity;
  private String                 userIdentity;
  private String                 createdByIdentity;
  private String                 message;
  private EWorkflowMessageType   messageType;
  private EWorkflowMessageStatus status;
  private Integer                version;
  private Integer                expireDays;
  private LocalDateTime          createdAt;

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

  public EWorkflowMessageType getMessageType() {
    return this.messageType;
  }

  public void setMessageType(final EWorkflowMessageType messageType) {
    this.messageType = messageType;
  }

  public EWorkflowMessageStatus getStatus() {
    return this.status;
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
    return this.createdAt.plusDays(this.expireDays).isAfter(LocalDateTime.now()) == false;
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

  public void setCreatedAt(final LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getIdentityPath() {
    return this.workflowIdentity + "-" + this.stepIdentity + "-" + this.userIdentity;
  }

}
