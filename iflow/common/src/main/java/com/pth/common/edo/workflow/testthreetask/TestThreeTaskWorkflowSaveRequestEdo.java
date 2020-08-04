package com.pth.common.edo.workflow.testthreetask;

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
import com.pth.common.edo.AssignItemEdo;
import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.common.edo.validation.AEnumNameValidator;

public class TestThreeTaskWorkflowSaveRequestEdo {

  @NotNull(message = "Command is not allowed to be null!")
  @AEnumNameValidator(enumClazz = EWorkflowProcessCommand.class)
  private String command;

  @NotNull(message = "Workflow must not be null")
  private TestThreeTaskWorkflowEdo workflow;

  @NotNull(message = "ExpireDays must not be null")
  private Integer expireDays;

  @NotNull(message = "AssignedList must not be null")
  private List<AssignItemEdo> assigns = new ArrayList<>();

  public TestThreeTaskWorkflowSaveRequestEdo() {

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
  public TestThreeTaskWorkflowEdo getWorkflow() {
    return this.workflow;
  }

  /**
   * @param workflow the workflow to set
   */
  public void setWorkflow(final TestThreeTaskWorkflowEdo workflow) {
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
