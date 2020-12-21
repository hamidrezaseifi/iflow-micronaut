package com.pth.common.edo.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.common.edo.WorkflowActionEdo;
import com.pth.common.edo.WorkflowFileEdo;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class WorkflowEdo {

  @NotNull(message = "id is not allowed to be null!")
  protected UUID id;

  private String identity;

  @NotNull(message = "CompanyId is not allowed to be null!")
  private UUID companyId;

  @NotNull(message = "WorkflowTypeId is not allowed to be null!")
  private UUID workflowTypeId;

  private UUID currentStepId;

  private UUID controllerId;

  private UUID createdById;

  private String comments;

  @NotNull(message = "Status is not allowed to be null!")
  private Integer status;

  @NotNull(message = "Version is not allowed to be null!")
  private Integer version;

  @NotNull(message = "WorkflowFileEdo is not allowed to be null!")
  private List<WorkflowFileEdo> files = new ArrayList<>();

  @NotNull(message = "WorkflowActionSet is not allowed to be null!")
  private List<WorkflowActionEdo> actions = new ArrayList<>();

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(String identity) {
    this.identity = identity;
  }

  public UUID getCompanyId() {

    return this.companyId;
  }

  public void setCompanyId(final UUID companyId) {

    this.companyId = companyId;
  }

  public UUID getWorkflowTypeId() {

    return this.workflowTypeId;
  }

  public void setWorkflowTypeId(final UUID workflowTypeId) {

    this.workflowTypeId = workflowTypeId;
  }

  public UUID getCurrentStepId() {

    return this.currentStepId;
  }

  public void setCurrentStepId(final UUID currentStepId) {

    this.currentStepId = currentStepId;
  }

  public UUID getControllerId() {

    return this.controllerId;
  }

  public void setControllerId(final UUID controllerId) {

    this.controllerId = controllerId;
  }

  public UUID getCreatedById() {

    return this.createdById;
  }

  public void setCreatedById(final UUID createdById) {

    this.createdById = createdById;
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

  public Integer getVersion() {

    return this.version;
  }

  public void setVersion(final Integer version) {

    this.version = version;
  }

  public List<WorkflowFileEdo> getFiles() {

    return this.files;
  }

  @JsonSetter
  public void setFiles(final List<WorkflowFileEdo> files) {

    this.files = new ArrayList<>();
    if (files != null) {
      this.files.addAll(files);
    }
  }

  public List<WorkflowActionEdo> getActions() {

    return this.actions;
  }

  @JsonSetter
  public void setActions(final List<WorkflowActionEdo> actions) {

    this.actions = new ArrayList<>();
    if (actions != null) {
      this.actions.addAll(actions);
    }
  }

}
