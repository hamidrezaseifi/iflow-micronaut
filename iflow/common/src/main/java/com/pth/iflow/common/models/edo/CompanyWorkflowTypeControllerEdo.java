package com.pth.iflow.common.models.edo;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.pth.iflow.common.models.base.IFlowJaxbDefinition;

@XmlRootElement(name = "CompanyWorkflowTypeController", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "CompanyWorkflowTypeController" + IFlowJaxbDefinition.TYPE_PREFIX)
public class CompanyWorkflowTypeControllerEdo {

  @NotNull(message = "WorkflowTypeIdentity must not be null")
  @XmlElement(name = "WorkflowTypeIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String workflowTypeIdentity;

  @NotNull(message = "UserIdentity must not be null")
  @XmlElement(name = "UserIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String userIdentity;

  @NotNull(message = "Priority must not be null")
  @XmlElement(name = "Priority", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer priority;

  public CompanyWorkflowTypeControllerEdo() {

  }

  public CompanyWorkflowTypeControllerEdo(final String workflowTypeIdentity, final String userIdentity, final Integer priority) {

    this.workflowTypeIdentity = workflowTypeIdentity;
    this.userIdentity = userIdentity;
    this.priority = priority;
  }

  public String getWorkflowTypeIdentity() {

    return this.workflowTypeIdentity;
  }

  public void setWorkflowTypeIdentity(final String workflowTypeIdentity) {

    this.workflowTypeIdentity = workflowTypeIdentity;
  }

  public String getUserIdentity() {

    return this.userIdentity;
  }

  public void setUserIdentity(final String userIdentity) {

    this.userIdentity = userIdentity;
  }

  public Integer getPriority() {

    return this.priority;
  }

  public void setPriority(final Integer priority) {

    this.priority = priority;
  }

}
