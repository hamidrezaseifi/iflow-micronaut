package com.pth.iflow.common.models.edo.workflow.testthreetask;

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

@XmlRootElement(name = "TestThreeTaskWorkflowList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "TestThreeTaskWorkflowList" + IFlowJaxbDefinition.TYPE_PREFIX)
public class TestThreeTaskWorkflowListEdo {

  @NotNull
  @XmlElementWrapper(name = "TestThreeTaskWorkflowList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "TestThreeTaskWorkflow", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final List<TestThreeTaskWorkflowEdo> workflows = new ArrayList<>();

  public TestThreeTaskWorkflowListEdo() {

  }

  public TestThreeTaskWorkflowListEdo(final List<TestThreeTaskWorkflowEdo> workflows) {
    this.setWorkflows(workflows);
  }

  public List<TestThreeTaskWorkflowEdo> getWorkflows() {
    return this.workflows;
  }

  @JsonSetter
  public void setWorkflows(final List<TestThreeTaskWorkflowEdo> workflows) {
    this.workflows.clear();
    if (workflows != null) {
      this.workflows.addAll(workflows);
    }
  }

}
