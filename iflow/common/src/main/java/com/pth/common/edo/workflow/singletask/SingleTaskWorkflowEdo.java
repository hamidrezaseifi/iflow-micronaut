package com.pth.common.edo.workflow.singletask;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.common.edo.workflow.WorkflowEdo;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class SingleTaskWorkflowEdo {

  @NotNull(message = "Workflow is not allowed to be null!")
  private WorkflowEdo workflow;

  public WorkflowEdo getWorkflow() {
    return this.workflow;
  }

  public void setWorkflow(final WorkflowEdo workflow) {
    this.workflow = workflow;
  }

}
