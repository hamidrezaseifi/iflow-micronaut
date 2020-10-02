package com.pth.common.edo.workflow.singletask;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.common.edo.AssignItemEdo;
import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.common.edo.validation.AEnumNameValidator;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class SingleTaskWorkflowSaveRequestEdo {

  @NotNull(message = "Command is not allowed to be null!")
  @AEnumNameValidator(enumClazz = EWorkflowProcessCommand.class)
  private String command;

  @NotNull(message = "Workflow must not be null")
  private SingleTaskWorkflowEdo workflow;

  @NotNull(message = "ExpireDays must not be null")
  private Integer expireDays;

  @NotNull(message = "AssignedList must not be null")
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

  @JsonSetter
  public void setAssigns(final List<AssignItemEdo> assigns) {
    this.assigns = new ArrayList<>();
    if (assigns != null) {
      this.assigns.addAll(assigns);
    }
  }

}
