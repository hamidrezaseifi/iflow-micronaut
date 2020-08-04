package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WorkflowTypeListEdo {

  @NotNull
  private final List<WorkflowTypeEdo> workflowTypes = new ArrayList<>();

  public WorkflowTypeListEdo() {

  }

  public WorkflowTypeListEdo(final List<WorkflowTypeEdo> workflowTypes) {
    this.setWorkflowTypes(workflowTypes);
  }

  public List<WorkflowTypeEdo> getWorkflowTypes() {
    return this.workflowTypes;
  }

  @JsonSetter
  public void setWorkflowTypes(final List<WorkflowTypeEdo> workflowTypes) {
    this.workflowTypes.clear();
    if (workflowTypes != null) {
      this.workflowTypes.addAll(workflowTypes);
    }
  }

}
