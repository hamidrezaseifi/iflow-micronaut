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

@XmlRootElement(name = "WorkflowList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "WorkflowList" + IFlowJaxbDefinition.TYPE_PREFIX)
public class WorkflowListEdo {

  @NotNull
  @XmlElementWrapper(name = "WorkflowList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "Workflow", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final List<WorkflowEdo> workflows = new ArrayList<>();

  public WorkflowListEdo() {

  }

  public WorkflowListEdo(final List<WorkflowEdo> workflows) {
    this.setWorkflows(workflows);
  }

  public List<WorkflowEdo> getWorkflows() {
    return this.workflows;
  }

  @JsonSetter
  public void setWorkflows(final List<WorkflowEdo> workflows) {
    this.workflows.clear();
    if (workflows != null) {
      this.workflows.addAll(workflows);
    }
  }

}
