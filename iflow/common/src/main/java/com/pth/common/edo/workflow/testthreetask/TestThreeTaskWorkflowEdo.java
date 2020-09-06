package com.pth.common.edo.workflow.testthreetask;

import com.pth.common.edo.enums.EWorkflowType;
import com.pth.common.edo.workflow.WorkflowEdo;

import javax.validation.constraints.NotNull;


public class TestThreeTaskWorkflowEdo {

  @NotNull(message = "Workflow is not allowed to be null!")
  private WorkflowEdo workflow;

  public WorkflowEdo getWorkflow() {
    return this.workflow;
  }

  public void setWorkflow(final WorkflowEdo workflow) {
    this.workflow = workflow;
  }

}
