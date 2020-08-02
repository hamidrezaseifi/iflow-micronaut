package com.pth.iflow.common.models.edo.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.edo.WorkflowActionEdo;
import com.pth.iflow.common.models.edo.WorkflowFileEdo;

@XmlRootElement(name = "Workflow", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "Workflow" + IFlowJaxbDefinition.TYPE_PREFIX)
public class WorkflowEdo {

  @NotNull(message = "Identity is not allowed to be null!")
  @XmlElement(name = "Identity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String identity;

  @NotNull(message = "CompanyIdentity is not allowed to be null!")
  @XmlElement(name = "CompanyIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String companyIdentity;

  @NotNull(message = "WorkflowTypeIdentity is not allowed to be null!")
  @XmlElement(name = "WorkflowTypeIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String workflowTypeIdentity;

  @XmlElement(name = "CurrentStepIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String currentStepIdentity;

  @XmlElement(name = "ControllerIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String controllerIdentity;

  @XmlElement(name = "CreatedByIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String createdByIdentity;

  @XmlElement(name = "Comments", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String comments;

  @NotNull(message = "Status is not allowed to be null!")
  @XmlElement(name = "Status", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer status;

  @NotNull(message = "Version is not allowed to be null!")
  @XmlElement(name = "Version", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer version;

  @NotNull(message = "WorkflowFileEdo is not allowed to be null!")
  @XmlElementWrapper(name = "WorkflowFileSet", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "WorkflowFile", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private List<WorkflowFileEdo> files = new ArrayList<>();

  @NotNull(message = "WorkflowActionSet is not allowed to be null!")
  @XmlElementWrapper(name = "WorkflowActionSet", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "WorkflowAction", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private List<WorkflowActionEdo> actions = new ArrayList<>();

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
