package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
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
