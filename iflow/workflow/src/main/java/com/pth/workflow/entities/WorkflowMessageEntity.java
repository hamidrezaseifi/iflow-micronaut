package com.pth.workflow.entities;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "workflow_message")
public class WorkflowMessageEntity extends BaseEntity {

  private static final long serialVersionUID = -3112387006573750725L;

  @Column(name = "workflow_id")
  private UUID workflowId;

  @Column(name = "step_id")
  private UUID stepId;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "message")
  private String message;

  @Column(name = "created_by")
  private UUID createdById;

  @Column(name = "message_type")
  private Integer messageType;

  @Column(name = "status")
  private Integer status;

  @Column(name = "expire_days")
  private Integer expireDays;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Timestamp createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Timestamp updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workflow_id", nullable = false, insertable = false, updatable = false)
  private WorkflowEntity workflow;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "step_id", nullable = false, insertable = false, updatable = false)
  private WorkflowTypeStepEntity step;

  public WorkflowMessageEntity() {

  }

  public UUID getWorkflowId() {

    return workflowId;
  }

  public void setWorkflowId(final UUID workflowId) {

    this.workflowId = workflowId;
  }

  public UUID getStepId() {

    return stepId;
  }

  public void setStepId(final UUID stepId) {

    this.stepId = stepId;
  }

  public UUID getUserId() {

    return userId;
  }

  public void setUserId(final UUID userId) {

    this.userId = userId;
  }

  public String getMessage() {

    return message;
  }

  public void setMessage(final String message) {

    this.message = message;
  }

  public UUID getCreatedById() {

    return createdById;
  }

  public void setCreatedById(final UUID createdBy) {

    this.createdById = createdBy;
  }

  public Integer getMessageType() {

    return messageType;
  }

  public EWorkflowMessageType getMessageTypeEnum() {

    return EWorkflowMessageType.ofValue(messageType);
  }

  public void setMessageType(final Integer messageType) {

    this.messageType = messageType;
  }

  public void setMessageTypeEnum(final EWorkflowMessageType messageType) {

    this.messageType = messageType.getValue();
  }

  public Integer getStatus() {

    return this.status;
  }

  public void setStatus(final Integer status) {

    this.status = status;
  }

  public EWorkflowMessageStatus getStatusEnum() {

    return EWorkflowMessageStatus.ofValue(status);
  }

  public void setStatusEnum(final EWorkflowMessageStatus status) {

    this.status = status.getValue();
  }

  @Override
  public Integer getVersion() {

    return this.version;
  }

  @Override
  public void setVersion(final Integer version) {

    this.version = version;
  }

  public Integer getExpireDays() {

    return this.expireDays;
  }

  public void setExpireDays(final Integer expireDays) {

    this.expireDays = expireDays;
  }

  public Timestamp getCreatedAt() {

    return this.createdAt;
  }

  public void setCreatedAt(final Timestamp createdAt) {

    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {

    return updatedAt;
  }

  public void setUpdatedAt(final Timestamp updatedAt) {

    this.updatedAt = updatedAt;
  }

  public String getIdentity() {

    return "";
  }

  public void setIdentity(final String identity) {

  }

  public String getIdentityPreffix() {

    return "";
  }

  public void increaseVersion() {

    version += 1;
  }

  public void updateFromExists(final WorkflowMessageEntity exists) {

    if (exists == null) {
      return;
    }
    this.createdById = exists.createdById;
    this.expireDays = exists.expireDays;
    this.message = exists.message;
    this.messageType = exists.messageType;
    this.status = exists.status;
    this.version = exists.version;
    this.stepId = exists.stepId;
    this.userId = exists.userId;
    this.workflowId = exists.workflowId;

  }

}
