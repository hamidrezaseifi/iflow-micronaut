package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class WorkflowTypeStepListEdo {

  @NotNull
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
