package com.pth.common.edo.workflow.testthreetask;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonSetter;

public class TestThreeTaskWorkflowListEdo {

  @NotNull
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
