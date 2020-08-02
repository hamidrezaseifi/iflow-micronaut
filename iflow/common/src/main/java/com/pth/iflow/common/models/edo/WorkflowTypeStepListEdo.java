package com.pth.iflow.common.models.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;

@XmlRootElement(name = "WorkflowTypeStepList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "WorkflowTypeStepList" + IFlowJaxbDefinition.TYPE_PREFIX)
public class WorkflowTypeStepListEdo {

  @NotNull
  @Value("#{T(java.util.Collections).emptyList()}")
  @XmlElementWrapper(name = "WorkflowTypeStepList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "WorkflowTypeStep", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final List<WorkflowTypeStepEdo> workflowTypeSteps = new ArrayList<>();

  public WorkflowTypeStepListEdo() {

  }

  public WorkflowTypeStepListEdo(final List<WorkflowTypeStepEdo> workflowTypes) {
    this.setWorkflowTypeSteps(workflowTypes);
  }

  public List<WorkflowTypeStepEdo> getWorkflowTypeSteps() {
    return this.workflowTypeSteps;
  }

  @JsonSetter
  public void setWorkflowTypeSteps(final List<WorkflowTypeStepEdo> workflowTypeSteps) {
    this.workflowTypeSteps.clear();
    if (workflowTypeSteps != null) {
      this.workflowTypeSteps.addAll(workflowTypeSteps);
    }
  }

}
