package com.pth.core.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pth.core.entities.workflow.WorkflowTypeEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "company_workflowtype_controller")
public class CompanyWorkflowTypeControllerEntity {

  @EmbeddedId
  private CompanyWorkflowTypeControllerId id;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private CompanyEntity company;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private UserEntity user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workflow_type_id", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private WorkflowTypeEntity workflowType;

  public CompanyWorkflowTypeControllerId getId() {

    return id;
  }

  public void setId(final CompanyWorkflowTypeControllerId id) {

    this.id = id;
  }

  public Date getCreatedAt() {

    return createdAt;
  }

  public void setCreatedAt(final Date createdAt) {

    this.createdAt = createdAt;
  }

  public CompanyEntity getCompany() {

    return company;
  }

  public UserEntity getUser() {

    return user;
  }

  public WorkflowTypeEntity getWorkflowType() {

    return workflowType;
  }

}
