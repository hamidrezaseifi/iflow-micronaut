package com.pth.workflow.entities.workflow;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pth.common.edo.enums.EIdentity;
import com.pth.common.edo.enums.EWorkflowActionStatus;
import com.pth.common.edo.enums.EWorkflowStatus;
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
  private UUID companyId;

  @Column(name = "controller")
  private UUID controllerId;

  @Column(name = "created_by")
  private UUID createdById;

  @Column(name = "workflow_type_id")
  private UUID workflowTypeId;

  @Column(name = "current_step")
  private UUID currentStepId;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "current_step", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private WorkflowTypeStepEntity currentStep;

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

  public EWorkflowStatus getStatusAsEnum() {

    return EWorkflowStatus.ofValue(this.status);
  }

  public void setStatus(final Integer status) {

    this.status = status;
  }


  public boolean hasController() {
    return this.controllerId != null;
  }

  public boolean hasCreatedBy() {
    return this.createdById != null;
  }

  public boolean hasWorkflowType() {
    return this.workflowType != null;
  }

  public void addAction(WorkflowActionEntity action) {
    actions.add(action);
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

  public boolean hasAction() {
    return this.actions != null && this.actions.isEmpty() == false;
  }

  public boolean hasActiveAction() {

    return this.getActiveAction() != null;
  }

  public WorkflowActionEntity getActiveAction() {

    for (final WorkflowActionEntity action : this.getActions()) {
      if (action.getIsActive() == true) {
        return action;
      }
    }
    return null;
  }

  public WorkflowActionEntity getLastAction() {

    if (hasAction() == false) {
      return null;
    }

    final List<WorkflowActionEntity> astinList = this.getActions();
    astinList.sort(new Comparator<WorkflowActionEntity>() {

      @Override
      public int compare(final WorkflowActionEntity action1, final WorkflowActionEntity action2) {

        return action1.getCurrentStep().getStepIndex() > action2.getCurrentStep().getStepIndex() ? 1
                                                                                                 : action1.getCurrentStep().getStepIndex() < action2.getCurrentStep().getStepIndex() ? -1 : 0;
      }
    });

    return astinList.get(astinList.size() - 1);
  }

  public boolean isAssigned() {

    return this.hasActiveAction() && this.getActiveAction().isAssigned();
  }

  public void setActiveActionAssignTo(UUID userId) {
    if(hasActiveAction()){
      getActiveAction().setAssignToId(userId);
    }
  }

  public void setActiveActionStatus(EWorkflowActionStatus status) {
    if(hasActiveAction()){
      getActiveAction().setStatus(status.getValue());
    }
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

  public UUID getControllerId() {

    return controllerId;
  }

  public void setControllerId(final UUID controllerId) {

    this.controllerId = controllerId;
  }

  public UUID getCreatedById() {

    return createdById;
  }

  public void setCreatedById(final UUID createdById) {

    this.createdById = createdById;
  }

  public UUID getWorkflowTypeId() {

    return workflowTypeId;
  }

  public void setWorkflowTypeId(final UUID workflowTypeId) {

    this.workflowTypeId = workflowTypeId;
  }

  public UUID getCurrentStepId() {

    return currentStepId;
  }

  public void setCurrentStepId(final UUID currentStepId) {

    this.currentStepId = currentStepId;
  }

  public WorkflowTypeEntity getWorkflowType() {

    return workflowType;
  }

  public UUID getCompanyId() {

    return companyId;
  }

  public void setCompanyId(final UUID companyId) {

    this.companyId = companyId;
  }

  public void setWorkflowType(WorkflowTypeEntity workflowType) {
    this.workflowType = workflowType;
  }

  public WorkflowTypeStepEntity getCurrentStep() {
    return currentStep;
  }

  public void setCurrentStep(WorkflowTypeStepEntity currentStep) {
    this.currentStep = currentStep;
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
