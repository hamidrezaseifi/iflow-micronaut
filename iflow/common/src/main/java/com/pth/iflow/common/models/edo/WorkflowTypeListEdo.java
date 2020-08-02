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

@XmlRootElement(name = "WorkflowTypeList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "WorkflowTypeList" + IFlowJaxbDefinition.TYPE_PREFIX)
public class WorkflowTypeListEdo {

  @NotNull
  @Value("#{T(java.util.Collections).emptyList()}")
  @XmlElementWrapper(name = "WorkflowTypeList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "WorkflowType", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final List<WorkflowTypeEdo> workflowTypes = new ArrayList<>();

  public WorkflowTypeListEdo() {

  }

  public WorkflowTypeListEdo(final List<WorkflowTypeEdo> workflowTypes) {
    this.setWorkflowTypes(workflowTypes);
  }

  public List<WorkflowTypeEdo> getWorkflowTypes() {
    return this.workflowTypes;
  }

  @JsonSetter
  public void setWorkflowTypes(final List<WorkflowTypeEdo> workflowTypes) {
    this.workflowTypes.clear();
    if (workflowTypes != null) {
      this.workflowTypes.addAll(workflowTypes);
    }
  }

}
