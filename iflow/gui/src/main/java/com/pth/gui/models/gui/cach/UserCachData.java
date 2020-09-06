package com.pth.gui.models.gui.cach;

import com.pth.gui.models.workflow.WorkflowMessage;

import java.util.*;
import java.util.stream.Collectors;

public class UserCachData {

  private final Map<UUID, WorkflowCachData> workflowDataList = new HashMap<>();
  private UUID userId;

  public UserCachData(final UUID userId) {

    this.userId = userId;
  }

  public Map<UUID, WorkflowCachData> getWorkflowDatas() {

    this.removeAllExpired();
    return this.workflowDataList;
  }

  public List<WorkflowCachData> getWorkflowDataList() {

    this.removeAllExpired();
    return this.workflowDataList.values().stream().collect(Collectors.toList());
  }

  public void setWorkflowMessages(final UUID workflowId, final List<WorkflowMessage> workflowMessages) {

    final WorkflowCachData workflowCachData = this.getWorkflowCachData(workflowId, true);
    workflowCachData.removeAllExpired();
    if (workflowMessages != null) {

      workflowCachData.setWorkflowMessages(workflowMessages);

    }
    this.removeAllExpired();
  }

  public WorkflowCachData getWorkflowCachData(final UUID workflowId, final boolean initialUserCachData) {

    if (this.workflowDataList.containsKey(workflowId) == false && initialUserCachData) {
      this.initialWorkflowCachData(workflowId);
    }
    if (this.workflowDataList.containsKey(workflowId)) {
      return this.workflowDataList.get(workflowId);
    }
    return null;
  }

  private void initialWorkflowCachData(final UUID workflowId) {

    if (this.hasWorkflowCachData(workflowId) == false) {
      final WorkflowCachData workflowCachData = new WorkflowCachData(workflowId);
      this.addWorkflowCachData(workflowCachData);
    }
  }

  private void addWorkflowCachData(final WorkflowCachData workflowCachData) {

    this.workflowDataList.put(workflowCachData.getWorkflowId(), workflowCachData);
  }

  public boolean hasWorkflowCachData(final UUID workflowId) {

    return this.workflowDataList.containsKey(workflowId);
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public boolean isUserId(final UUID userId) {

    return this.userId == userId;
  }

  private void removeAllExpired() {

    for (final WorkflowCachData data : this.workflowDataList.values()) {
      data.removeAllExpired();
    }
  }

  public void setWorkflowMessageList(final List<WorkflowMessage> workflowMessageList) {

    final Map<UUID, List<WorkflowMessage>> workflowMap = new HashMap<>();
    for (final WorkflowMessage message : workflowMessageList) {
      if (workflowMap.containsKey(message.getWorkflowId()) == false) {
        workflowMap.put(message.getWorkflowId(), new ArrayList<>());
      }
      workflowMap.get(message.getWorkflowId()).add(message);
    }

    for (final UUID workflowId : workflowMap.keySet()) {
      this.setWorkflowMessages(workflowId, workflowMap.get(workflowId));
    }
  }

  public List<WorkflowMessage> getWorkflowMessagesList() {

    final List<WorkflowMessage> list = new ArrayList<>();
    for (final WorkflowCachData data : this.workflowDataList.values()) {
      list.addAll(data.getWorkflowMessagesList());
    }
    return list;
  }

  public boolean removeWorkflowCachData(final UUID workflowId) {

    if (this.workflowDataList.containsKey(workflowId)) {
      this.workflowDataList.remove(workflowId);
      return true;
    }
    return false;

  }

}
