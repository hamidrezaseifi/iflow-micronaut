package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WorkflowActionListEdo {

  @NotNull
  private final List<WorkflowActionEdo> workflowActions = new ArrayList<>();

  public WorkflowActionListEdo() {

  }

  public WorkflowActionListEdo(final List<WorkflowActionEdo> workflowActions) {
    this.setWorkflowActions(workflowActions);
  }

  public List<WorkflowActionEdo> getWorkflowActions() {
    return this.workflowActions;
  }

  @JsonSetter
  public void setWorkflowActions(final List<WorkflowActionEdo> workflowActions) {
    this.workflowActions.clear();
    if (workflowActions != null) {
      this.workflowActions.addAll(workflowActions);
    }
  }

}
