package com.pth.common.edo.workflow.singletask;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonSetter;

public class SingleTaskWorkflowListEdo {

  @NotNull
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
