package com.pth.core.entities.workflow;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pth.common.entities.BaseEntity;
import com.pth.core.entities.UserEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "workflow_actions")
public class WorkflowActionEntity extends BaseEntity {

  @Column(name = "comments")
  private String comments;

  @Column(name = "assign_to")
  private Long assignToId;

  @Column(name = "current_step_id")
  private Long currentStepId;

  @Column(name = "status")
  private Integer status;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Date updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workflow_id", nullable = false)
  private WorkflowEntity workflowEntity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assign_to", nullable = true, insertable = false, updatable = false)
  @NotFound(action = NotFoundAction.IGNORE)
  private UserEntity assignToUser;

  public WorkflowActionEntity() {

    assignToId = 0L;

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

  public Long getCurrentStepId() {

    return currentStepId;
  }

  public void setCurrentStepId(final Long currentStepId) {

    this.currentStepId = currentStepId;
  }

  public Long getAssignToId() {

    return assignToId;
  }

  public void setAssignToId(final Long assignToId) {

    this.assignToId = assignToId;
  }

  public WorkflowEntity getWorkflowEntity() {

    return workflowEntity;
  }

  public void setWorkflowEntity(final WorkflowEntity workflowEntity) {

    this.workflowEntity = workflowEntity;
  }

}
