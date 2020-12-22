package com.pth.workflow.entities;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pth.common.edo.enums.EWorkflowActionStatus;
import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "workflow_actions")
public class WorkflowActionEntity extends BaseEntity {

  @Column(name = "comments")
  private String comments;

  @Column(name = "assign_to")
  private UUID assignToId;

  @Column(name = "current_step_id")
  private UUID currentStepId;

  @Column(name = "workflow_id")
  private UUID workflowId;

  @Column(name = "status")
  private Integer status;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Date updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workflow_id", nullable = false, insertable = false, updatable = false)
  private WorkflowEntity workflowEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "current_step_id", nullable = false, insertable = false, updatable = false)
  private WorkflowTypeStepEntity currentStep;

  public WorkflowActionEntity() {

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

  public void setStatus(final EWorkflowActionStatus status) {

    this.status = status.getValue();
  }

  public Date getCreatedAt() {

    return this.createdAt;
  }

  public void setCreatedAt(final Date createdAt) {

    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {

    return this.updatedAt;
  }

  public void setUpdatedAt(final Date updatedAt) {

    this.updatedAt = updatedAt;
  }

  public UUID getCurrentStepId() {

    return currentStepId;
  }

  public void setCurrentStepId(final UUID currentStepId) {

    this.currentStepId = currentStepId;
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

  public void setAssignToId(final UUID assignToId) {

    this.assignToId = assignToId;
  }

  public WorkflowEntity getWorkflowEntity() {

    return workflowEntity;
  }

  public void setWorkflowEntity(final WorkflowEntity workflowEntity) {

    this.workflowEntity = workflowEntity;
  }


  public boolean isAssigned() {

    return this.assignToId != null;
  }

  public boolean getIsActive() {
    return EWorkflowActionStatus.getIsActive(this.getStatus());
  }

  public WorkflowTypeStepEntity getCurrentStep() {
    return currentStep;
  }

  public void setCurrentStep(WorkflowTypeStepEntity currentStep) {
    this.currentStep = currentStep;
  }

  void fill(WorkflowActionEntity other){
    this.setWorkflowId(other.workflowId);
    this.setComments(other.comments);
    this.setAssignToId(other.assignToId);
    this.setCurrentStepId(other.currentStepId);
    this.setStatus(other.status);

  }
}
