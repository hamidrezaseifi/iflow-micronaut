package com.pth.common.edo.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.common.edo.WorkflowActionEdo;
import com.pth.common.edo.WorkflowFileEdo;

public class WorkflowEdo {

  @NotNull(message = "Id is not allowed to be null!")
  private UUID id;

  @NotNull(message = "Identity is not allowed to be null!")
  private String identity;

  @NotNull(message = "CompanyIdentity is not allowed to be null!")
  private String companyIdentity;

  @NotNull(message = "WorkflowTypeIdentity is not allowed to be null!")
  private String workflowTypeIdentity;

  private String currentStepIdentity;

  private String controllerIdentity;

  private String createdByIdentity;

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

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public String getCompanyIdentity() {

    return this.companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
  }

  public String getWorkflowTypeIdentity() {

    return this.workflowTypeIdentity;
  }

  public void setWorkflowTypeIdentity(final String workflowTypeIdentity) {

    this.workflowTypeIdentity = workflowTypeIdentity;
  }

  public String getCurrentStepIdentity() {

    return this.currentStepIdentity;
  }

  public void setCurrentStepIdentity(final String currentStepIdentity) {

    this.currentStepIdentity = currentStepIdentity;
  }

  public String getControllerIdentity() {

    return this.controllerIdentity;
  }

  public void setControllerIdentity(final String controllerIdentity) {

    this.controllerIdentity = controllerIdentity;
  }

  public String getCreatedByIdentity() {

    return this.createdByIdentity;
  }

  public void setCreatedByIdentity(final String createdByIdentity) {

    this.createdByIdentity = createdByIdentity;
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
