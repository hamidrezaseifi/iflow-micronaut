package com.pth.workflow.entities;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.*;

import com.pth.core.entities.CompanyWorkflowTypeControllerId;
import com.pth.core.entities.workflow.WorkflowTypeEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "company_workflowtype_controller")
public class CompanyWorkflowTypeControllerEntity {

  @Id
  private UUID id;

  private Integer priority;

  @Column(name = "company_id")
  private UUID companyId;

  @Column(name = "user_id")
  private UUID userId;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workflow_type_id", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private WorkflowTypeEntity workflowType;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Date getCreatedAt() {

    return createdAt;
  }

  public void setCreatedAt(final Date createdAt) {

    this.createdAt = createdAt;
  }

  public UUID getCompanyId() {
    return companyId;
  }

  public void setCompanyId(UUID companyId) {
    this.companyId = companyId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public WorkflowTypeEntity getWorkflowType() {

    return workflowType;
  }

  public void setWorkflowType(WorkflowTypeEntity workflowType) {
    this.workflowType = workflowType;
  }
}
