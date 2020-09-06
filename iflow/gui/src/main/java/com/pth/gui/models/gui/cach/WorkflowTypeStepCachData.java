package com.pth.gui.models.gui.cach;

import com.pth.gui.models.workflow.WorkflowMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


public class WorkflowTypeStepCachData {

  private final Map<UUID, WorkflowMessage> workflowMessages = new HashMap<>();
  private UUID stepId;

  public WorkflowTypeStepCachData(final UUID stepId) {

    this.stepId = stepId;
  }

  public Map<UUID, WorkflowMessage> getWorkflowMessages() {

    this.removeAllExpired();
    return this.workflowMessages;
  }

  public List<WorkflowMessage> getWorkflowMessagesList() {

    this.removeAllExpired();
    return this.workflowMessages.values().stream().collect(Collectors.toList());
  }

  public void setWorkflowMessages(final List<WorkflowMessage> workflowMessages) {

    this.workflowMessages.clear();
    if (workflowMessages != null) {

      for (final WorkflowMessage workflowMessage : workflowMessages) {
        this.workflowMessages.put(workflowMessage.getId(), workflowMessage);
      }

    }
    this.removeAllExpired();
  }

  public void addWorkflowMessage(final WorkflowMessage workflowMessage) {

    if (workflowMessage.isNotExpired()) {
      this.workflowMessages.put(workflowMessage.getId(), workflowMessage);
    }
  }

  public void addWorkflowMessageList(final List<WorkflowMessage> workflowMessageList) {

    this.removeAllExpired();
    for (final WorkflowMessage workflowMessage : workflowMessageList) {
      this.addWorkflowMessage(workflowMessage);
    }
  }

  public UUID getStepId() {

    return this.stepId;
  }

  public void setStepId(final UUID stepId) {

    this.stepId = stepId;
  }

  public boolean isStepId(final UUID stepId) {

    return this.stepId == stepId;
  }

  public void removeAllExpired() {

    final List<UUID> expireds = this.workflowMessages
        .keySet()
        .stream()
        .filter(id -> this.workflowMessages.get(id).isExpired())
        .collect(Collectors.toList());

    for (final UUID id : expireds) {
      this.workflowMessages.remove(id);
    }
  }
}
