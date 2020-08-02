package com.pth.iflow.common.models.edo.workflow.invoice;

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

@XmlRootElement(name = "InvoiceWorkflowList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "InvoiceWorkflowList" + IFlowJaxbDefinition.TYPE_PREFIX)
public class InvoiceWorkflowListEdo {

  @NotNull
  @XmlElementWrapper(name = "InvoiceWorkflowList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "InvoiceWorkflow", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final List<InvoiceWorkflowEdo> workflows = new ArrayList<>();

  public InvoiceWorkflowListEdo() {

  }

  public InvoiceWorkflowListEdo(final List<InvoiceWorkflowEdo> workflows) {
    this.setWorkflows(workflows);
  }

  public List<InvoiceWorkflowEdo> getWorkflows() {
    return this.workflows;
  }

  @JsonSetter
  public void setWorkflows(final List<InvoiceWorkflowEdo> workflows) {
    this.workflows.clear();
    if (workflows != null) {
      this.workflows.addAll(workflows);
    }
  }

}
