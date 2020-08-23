package com.pth.workflow.entities;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "workflow_type_step")
public class WorkflowTypeStepEntity extends BaseEntity {

  private static final long serialVersionUID = -3052100216046817054L;

  @Column(name = "identity")
  private String identity;

  @Column(name = "workflow_type_id")
  private UUID workflowTypeId;

  @Column(name = "title")
  private String title;

  @Column(name = "step_index")
  private Integer stepIndex;

  @Column(name = "view_name")
  private String viewName;

  @Column(name = "expire_days")
  private Integer expireDays;

  @Column(name = "commecnts")
  private String comments;

  @Column(name = "status")
  private Integer status;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Date updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workflow_type_id", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private WorkflowTypeEntity workflowType;

  public WorkflowTypeStepEntity() {

  }

  public UUID getWorkflowTypeId() {

    return this.workflowTypeId;
  }

  public String getIdentity() {

    return identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public void setWorkflowTypeId(final UUID workflowTypeId) {

    this.workflowTypeId = workflowTypeId;
  }

  public String getTitle() {

    return this.title;
  }

  public void setTitle(final String title) {

    this.title = title;
  }

  /**
   * @return the stepIndex
   */
  public Integer getStepIndex() {

    return this.stepIndex;
  }

  /**
   * @param stepIndex the stepIndex to set
   */
  public void setStepIndex(final Integer stepIndex) {

    this.stepIndex = stepIndex;
  }

  public String getViewName() {

    return this.viewName;
  }

  public void setViewName(final String viewName) {

    this.viewName = viewName;
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

  @Override
  public Integer getVersion() {

    return this.version;
  }

  @Override
  public void setVersion(final Integer version) {

    this.version = version;
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

  public Integer getExpireDays() {

    return expireDays;
  }

  public void setExpireDays(final Integer expireDays) {

    this.expireDays = expireDays;
  }

  public String getIdentityPreffix() {

    return "wtps";
  }

  public void increaseVersion() {

    version += 1;
  }
}
