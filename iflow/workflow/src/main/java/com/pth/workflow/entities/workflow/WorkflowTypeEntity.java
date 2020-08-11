package com.pth.core.entities.workflow;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pth.common.edo.enums.EWorkflowTypeAssignType;
import com.pth.common.entities.BaseEntity;
import com.pth.core.entities.CompanyEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "workflow_type")
public class WorkflowTypeEntity extends BaseEntity {

  private static final long serialVersionUID = -8971151977689234657L;

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
  private final List<WorkflowTypeStepEntity> steps = new ArrayList<>();

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
             name = "company_workflow_type", joinColumns = { @JoinColumn(name = "workflow_type_id") }, inverseJoinColumns = { @JoinColumn(name = "company_id") }
  )
  private CompanyEntity company;

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

  public List<WorkflowTypeStepEntity> getSteps() {

    return this.steps;
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

  public CompanyEntity getCompany() {

    return company;
  }

  public String getIdentityPreffix() {

    return "wtp";
  }

  public void increaseVersion() {

    version += 1;
  }
}
