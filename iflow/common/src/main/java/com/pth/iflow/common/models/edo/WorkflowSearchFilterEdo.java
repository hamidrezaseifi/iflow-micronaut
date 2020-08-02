package com.pth.iflow.common.models.edo;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;

@XmlRootElement(name = "WorkflowSearchFilter", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "WorkflowSearchFilter" + IFlowJaxbDefinition.TYPE_PREFIX)
public class WorkflowSearchFilterEdo {

  @NotNull(message = "AssignedUserIdentitySet must not be null")
  @XmlElementWrapper(name = "AssignedUserIdentitySet", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "AssignedUserId", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Set<String> assignedUserIdentitySet = new HashSet<>();

  @NotNull(message = "StatusSet must not be null")
  @XmlElementWrapper(name = "StatusSet", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "Status", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Set<Integer> statusSet = new HashSet<>();

  @NotNull(message = "WorkflowTypeIdentitySet must not be null")
  @XmlElementWrapper(name = "WorkflowTypeIdentitySet", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "WorkflowTypeIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Set<String> workflowTypeIdentitySet = new HashSet<>();

  @NotNull(message = "WorkflowStepIdentitySet must not be null")
  @XmlElementWrapper(name = "WorkflowStepIdentitySet", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "WorkflowStepIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Set<String> workflowStepeIdentitySet = new HashSet<>();

  @NotNull(message = "CompanyIdentity must not be null")
  @XmlElement(name = "CompanyIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String companyIdentity;

  public Set<String> getAssignedUserIdentitySet() {

    return this.assignedUserIdentitySet;
  }

  @JsonSetter
  public void setAssignedUserIdentitySet(final Set<String> assignedUserIdentitySet) {

    this.assignedUserIdentitySet = new HashSet<>();
    if (assignedUserIdentitySet != null) {
      this.assignedUserIdentitySet.addAll(assignedUserIdentitySet);
    }
  }

  public Set<Integer> getStatusSet() {

    return this.statusSet;
  }

  @JsonSetter
  public void setStatusSet(final Set<Integer> statusSet) {

    this.statusSet = new HashSet<>();
    if (statusSet != null) {
      this.statusSet.addAll(statusSet);
    }
  }

  public Set<String> getWorkflowTypeIdentitySet() {

    return this.workflowTypeIdentitySet;
  }

  @JsonSetter
  public void setWorkflowTypeIdentitySet(final Set<String> workflowTypeIdentitySet) {

    this.workflowTypeIdentitySet = new HashSet<>();
    if (workflowTypeIdentitySet != null) {
      this.workflowTypeIdentitySet.addAll(workflowTypeIdentitySet);
    }
  }

  public Set<String> getWorkflowStepeIdentitySet() {

    return this.workflowStepeIdentitySet;
  }

  @JsonSetter
  public void setWorkflowStepeIdentitySet(final Set<String> workflowStepeIdentitySet) {

    this.workflowStepeIdentitySet = new HashSet<>();
    if (workflowStepeIdentitySet != null) {
      this.workflowStepeIdentitySet.addAll(workflowStepeIdentitySet);
    }
  }

  public String getCompanyIdentity() {

    return this.companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
  }

}
