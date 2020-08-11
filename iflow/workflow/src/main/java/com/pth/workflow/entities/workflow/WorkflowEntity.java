package com.pth.workflow.entities.workflow;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pth.common.edo.enums.EIdentity;
import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "workflow")
public class WorkflowEntity extends BaseEntity {

  private static final long serialVersionUID = 6541443032441596046L;


  @Column(name = "identity")
  private String identity;

  @Column(name = "company_id")
  private Long companyId;

  @Column(name = "controller")
  private Long controllerId;

  @Column(name = "created_by")
  private Long createdById;

  @Column(name = "workflow_type_id")
  private Long workflowTypeId;

  @Column(name = "current_step")
  private Long currentStepId;

  @Column(name = "comments")
  private String comments;

  @Column(name = "status")
  private Integer status;


  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Date updatedAt;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workflowEntity", fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  private final List<WorkflowFileEntity> files = new ArrayList<>();

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workflowEntity", fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  private final List<WorkflowActionEntity> actions = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "workflow_type_id", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private WorkflowTypeEntity workflowType;

  public WorkflowEntity() {

  }

  public String getIdentity() {

    return identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public boolean isIdentityNotSet() {

    return EIdentity.NOT_SET.getIdentity().equals(getIdentity());
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

  public List<WorkflowFileEntity> getFiles() {

    return this.files;
  }

  public void setFiles(final List<WorkflowFileEntity> files) {

    this.files.clear();
    if (files != null) {
      for (final WorkflowFileEntity file : files) {
        file.setWorkflowEntity(this);
        this.files.add(file);
      }
    }
  }

  public List<WorkflowActionEntity> getActions() {

    return this.actions;
  }

  public void setActions(final List<WorkflowActionEntity> actions) {

    this.actions.clear();
    if (actions != null) {
      for (final WorkflowActionEntity action : actions) {
        action.setWorkflowEntity(this);
        this.actions.add(action);
      }

    }
  }

  public String getIdentityPreffix() {

    return "w";
  }

  public Long getControllerId() {

    return controllerId;
  }

  public void setControllerId(final Long controllerId) {

    this.controllerId = controllerId;
  }

  public Long getCreatedById() {

    return createdById;
  }

  public void setCreatedById(final Long createdById) {

    this.createdById = createdById;
  }

  public Long getWorkflowTypeId() {

    return workflowTypeId;
  }

  public void setWorkflowTypeId(final Long workflowTypeId) {

    this.workflowTypeId = workflowTypeId;
  }

  public Long getCurrentStepId() {

    return currentStepId;
  }

  public void setCurrentStepId(final Long currentStepId) {

    this.currentStepId = currentStepId;
  }

  public WorkflowTypeEntity getWorkflowType() {

    return workflowType;
  }

  public Long getCompanyId() {

    return companyId;
  }

  public void setCompanyId(final Long companyId) {

    this.companyId = companyId;
  }

  public void increaseVersion() {

    version += 1;

  }

  public void makeAllSubEntityNew() {

    for (final WorkflowActionEntity action : actions) {
      action.setId(null);
    }

    for (final WorkflowFileEntity file : files) {

      for (final WorkflowFileVersionEntity fileVersion : file.getFileVersions()) {
        fileVersion.setId(null);
        fileVersion.getWorkflowFileEntity().setId(null);
        fileVersion.getWorkflowFileEntity().setIdentity("");

      }

      file.setId(null);
      file.setIdentity("");
    }

  }
}
