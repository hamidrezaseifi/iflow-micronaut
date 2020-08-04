package com.pth.common.edo.workflow;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSetter;

public class WorkflowListEdo {

  @NotNull
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
