package com.pth.iflow.common.models.edo.workflow.singletask;

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

@XmlRootElement(name = "SingleTaskWorkflowList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "SingleTaskWorkflowList" + IFlowJaxbDefinition.TYPE_PREFIX)
public class SingleTaskWorkflowListEdo {

  @NotNull
  @XmlElementWrapper(name = "SingleTaskWorkflowList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "SingleTaskWorkflow", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final List<SingleTaskWorkflowEdo> workflows = new ArrayList<>();

  public SingleTaskWorkflowListEdo() {

  }

  public SingleTaskWorkflowListEdo(final List<SingleTaskWorkflowEdo> workflows) {
    this.setWorkflows(workflows);
  }

  public List<SingleTaskWorkflowEdo> getWorkflows() {
    return this.workflows;
  }

  @JsonSetter
  public void setWorkflows(final List<SingleTaskWorkflowEdo> workflows) {
    this.workflows.clear();
    if (workflows != null) {
      this.workflows.addAll(workflows);
    }
  }

}
