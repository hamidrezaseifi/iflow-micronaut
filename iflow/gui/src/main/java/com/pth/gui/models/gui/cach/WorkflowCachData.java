package com.pth.gui.models.gui.cach;

import com.pth.gui.models.workflow.WorkflowMessage;

import java.util.*;


public class WorkflowCachData {

  private final Map<UUID, WorkflowTypeStepCachData> workflowSteps = new HashMap<>();
  private UUID workflowId;

  public WorkflowCachData(final UUID workflowId) {

    this.workflowId = workflowId;
  }

  public Map<UUID, WorkflowTypeStepCachData> getWorkflowSteps() {

    this.removeAllExpired();
    return this.workflowSteps;
  }

  public List<WorkflowMessage> getWorkflowMessagesList() {

    this.removeAllExpired();

    final List<WorkflowMessage> list = new ArrayList<>();
    for (final WorkflowTypeStepCachData data : this.workflowSteps.values()) {
      list.addAll(data.getWorkflowMessagesList());
    }
    return list;
  }

  public void setWorkflowMessages(final List<WorkflowMessage> workflowMessages) {

    final Map<UUID, List<WorkflowMessage>> mapped = this.getStepMappedMessageList(workflowMessages);

    /*
     * for (final WorkflowMessage message : workflowMessages) { if (mapped.containsKey(message.getStepIdentity()) == false) { final
     * WorkflowTypeStepCachData cachData = new WorkflowTypeStepCachData(message.getStepIdentity());
     * workflowSteps.put(message.getStepIdentity(), cachData); } }
     */

    for (final UUID stepId : mapped.keySet()) {
      final WorkflowTypeStepCachData data = this.getWorkflowTypeStepCachData(stepId, true);
      data.setWorkflowMessages(mapped.get(stepId));

    }

    this.removeAllExpired();

  }

  private Map<UUID, List<WorkflowMessage>> getStepMappedMessageList(final List<WorkflowMessage> workflowMessages) {

    final Map<UUID, List<WorkflowMessage>> mapped = new HashMap<>();

    for (final WorkflowMessage message : workflowMessages) {

      if (message.isExpired()) {
        continue;
      }
      if (mapped.keySet().contains(message.getStepId()) == false) {
        mapped.put(message.getStepId(), new ArrayList<>());
      }
      mapped.get(message.getStepId()).add(message);

    }
    return mapped;
  }

  public WorkflowTypeStepCachData getWorkflowTypeStepCachData(final UUID stepId, final boolean initial) {

    if (initial && !this.hasWorkflowTypeStepCachData(stepId)) {
      final WorkflowTypeStepCachData data = new WorkflowTypeStepCachData(stepId);
      this.workflowSteps.put(stepId, data);
    }
    return !this.hasWorkflowTypeStepCachData(stepId) ? null : this.workflowSteps.get(stepId);
  }

  public boolean hasWorkflowTypeStepCachData(final UUID stepId) {

    return this.workflowSteps.keySet().contains(stepId);
  }

  public void addWorkflowMessage(final WorkflowMessage workflowMessage) {

    if (workflowMessage.isNotExpired()) {
      final WorkflowTypeStepCachData cachData = this.getWorkflowTypeStepCachData(workflowMessage.getId(), true);
      cachData.addWorkflowMessage(workflowMessage);
    }
  }

  public void addWorkflowMessageList(final List<WorkflowMessage> workflowMessageList) {

    final Map<UUID, List<WorkflowMessage>> mapped = this.getStepMappedMessageList(workflowMessageList);

    for (final UUID stepId : mapped.keySet()) {
      final WorkflowTypeStepCachData data = this.getWorkflowTypeStepCachData(stepId, true);
      data.addWorkflowMessageList(mapped.get(stepId));

    }

    this.removeAllExpired();
  }

  public UUID getWorkflowId() {

    return this.workflowId;
  }

  public void setWorkflowId(final UUID workflowId) {

    this.workflowId = workflowId;
  }

  public boolean isWorkflowId(final UUID workflowId) {

    return this.workflowId == workflowId;
  }

  public void removeAllExpired() {

    for (final WorkflowTypeStepCachData cachData : this.workflowSteps.values()) {
      cachData.removeAllExpired();
    }

  }
}
