package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonSetter;

public class WorkflowMessageListEdo {

  @NotNull
  private final List<WorkflowMessageEdo> workflowMessages = new ArrayList<>();

  public WorkflowMessageListEdo() {

  }

  public WorkflowMessageListEdo(final List<WorkflowMessageEdo> workflowMessages) {
    this.setWorkflowMessages(workflowMessages);
  }

  public List<WorkflowMessageEdo> getWorkflowMessages() {
    return this.workflowMessages;
  }

  @JsonSetter
  public void setWorkflowMessages(final List<WorkflowMessageEdo> workflowMessages) {
    this.workflowMessages.clear();
    if (workflowMessages != null) {
      this.workflowMessages.addAll(workflowMessages);
    }
  }

}
