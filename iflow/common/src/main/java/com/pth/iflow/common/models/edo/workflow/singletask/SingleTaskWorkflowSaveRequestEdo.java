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
import com.pth.iflow.common.enums.EWorkflowProcessCommand;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.edo.AssignItemEdo;
import com.pth.iflow.common.models.validation.AEnumNameValidator;

@XmlRootElement(name = "SingleTaskWorkflowSaveRequest", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "SingleTaskWorkflowSaveRequest" + IFlowJaxbDefinition.TYPE_PREFIX)
public class SingleTaskWorkflowSaveRequestEdo {

  @NotNull(message = "Command is not allowed to be null!")
  @AEnumNameValidator(enumClazz = EWorkflowProcessCommand.class)
  @XmlElement(name = "Command", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String command;

  @NotNull(message = "Workflow must not be null")
  @XmlElement(name = "Workflow", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private SingleTaskWorkflowEdo workflow;

  @NotNull(message = "ExpireDays must not be null")
  @XmlElement(name = "ExpireDays", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer expireDays;

  @NotNull(message = "AssignedList must not be null")
  @XmlElementWrapper(name = "AssignedList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "AssignedList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private List<AssignItemEdo> assigns = new ArrayList<>();

  public SingleTaskWorkflowSaveRequestEdo() {

  }

  public String getCommand() {
    return this.command;
  }

  public void setCommand(final String command) {
    this.command = command;
  }

  /**
   * @return the workflow
   */
  public SingleTaskWorkflowEdo getWorkflow() {
    return this.workflow;
  }

  /**
   * @param workflow the workflow to set
   */
  public void setWorkflow(final SingleTaskWorkflowEdo workflow) {
    this.workflow = workflow;
  }

  public Integer getExpireDays() {
    return this.expireDays;
  }

  public void setExpireDays(final Integer expireDays) {
    this.expireDays = expireDays;
  }

  public List<AssignItemEdo> getAssigns() {
    return this.assigns;
  }

  /**
   * @param assignedUsers the assignedUsers to set
   */
  @JsonSetter
  public void setAssigns(final List<AssignItemEdo> assigns) {
    this.assigns = new ArrayList<>();
    if (assigns != null) {
      this.assigns.addAll(assigns);
    }
  }

}
