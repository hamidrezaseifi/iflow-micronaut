package com.pth.workflow.entities;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.pth.common.edo.enums.EWorkflowTypeAssignType;
import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "workflow_type")
public class WorkflowTypeEntity extends BaseEntity {

  //@Column(name = "company_id")
  //private UUID companyId;

  @Column(name = "identity")
  private String identity;

  @Column(name = "title")
  private String title;

  @Column(name = "commecnts")
  private String comments;

  @Column(name = "status")
  private Integer status;

  @Column(name = "send_to_controller")
  private Boolean sendToController;

  @Column(name = "assign_type")
  private EWorkflowTypeAssignType assignType;

  @Column(name = "increase_step_automatic")
  private Boolean increaseStepAutomatic;

  @Column(name = "allow_assign")
  private Boolean allowAssign;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Date updatedAt;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "workflow_type_id")
  private Set<WorkflowTypeStepEntity> steps = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "workflow_type_id", insertable = false, updatable = false)
  private Set<CompanyWorkflowTypeEntity> companyWorkflowTypes = new HashSet<>();

  public String getIdentity() {

    return identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public EWorkflowTypeAssignType getAssignType() {

    return assignType;
  }

  public String getTitle() {

    return this.title;
  }

  public void setTitle(final String title) {

    this.title = title;
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

  public Boolean getSendToController() {

    return this.sendToController;
  }

  public void setSendToController(final Boolean sendToController) {

    this.sendToController = sendToController;
  }

  public EWorkflowTypeAssignType geAssignType() {

    return this.assignType;
  }

  public void setAssignType(final EWorkflowTypeAssignType assignType) {

    this.assignType = assignType;
  }

  public Boolean getIncreaseStepAutomatic() {

    return this.increaseStepAutomatic;
  }

  public void setIncreaseStepAutomatic(final Boolean increaseStepAutomatic) {

    this.increaseStepAutomatic = increaseStepAutomatic;
  }

  public Boolean getAllowAssign() {

    return this.allowAssign;
  }

  public void setAllowAssign(final Boolean allowAssign) {

    this.allowAssign = allowAssign;
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

  public Set<WorkflowTypeStepEntity> getSteps() {

    return this.steps;
  }

  public List<WorkflowTypeStepEntity> getStepsAsList() {

    return this.steps.stream().collect(Collectors.toList());
  }

  public void setSteps(final List<WorkflowTypeStepEntity> steps) {

    this.steps.clear();
    if (steps != null) {
      this.steps.addAll(steps);
    }
  }

  public void addStep(final WorkflowTypeStepEntity stepId) {

    this.steps.add(stepId);
  }

  public Set<CompanyWorkflowTypeEntity> getCompanyWorkflowTypes() {
    return companyWorkflowTypes;
  }

  public void setCompanyWorkflowTypes(Set<CompanyWorkflowTypeEntity> companyWorkflowTypes) {
    this.companyWorkflowTypes = companyWorkflowTypes;
  }

  public String getIdentityPreffix() {

    return "wtp";
  }

  public void increaseVersion() {

    version += 1;
  }

  public boolean isAssignTypeOffering() {
    return assignType == EWorkflowTypeAssignType.OFFER;
  }

  public boolean isAssignTypeManual() {
    return assignType == EWorkflowTypeAssignType.MANUAL;
  }
}
