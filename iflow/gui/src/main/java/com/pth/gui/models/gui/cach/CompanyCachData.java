package com.pth.gui.models.gui.cach;

import com.pth.gui.models.workflow.WorkflowMessage;
import com.pth.gui.services.ICompanyCachDataManager;

import java.util.*;
import java.util.stream.Collectors;


public class CompanyCachData {

  private final Map<UUID, UserCachData> userCachList = new HashMap<>();
  private UUID companyId;

  public CompanyCachData(final UUID companyId) {

    this.companyId = companyId;
  }

  public UUID getCompanyId() {

    return this.companyId;
  }

  public void setCompanyId(final UUID companyId) {

    this.companyId = companyId;
  }

  public boolean isCompanyId(final UUID companyId) {

    return this.companyId == companyId;
  }

  public Map<UUID, UserCachData> getUserCachList() {

    return this.userCachList;
  }

  public void setUserCachDataList(final List<UserCachData> userCachDataList) {

    this.userCachList.clear();
    this.addUserCachDataList(userCachDataList);
  }

  public void addUserCachDataList(final List<UserCachData> userCachDataList) {

    if (userCachDataList != null) {

      this.userCachList.putAll(userCachDataList.stream().collect(Collectors.toMap(ud -> ud.getUserId(), ud -> ud)));

    }
  }

  public void addUserCachData(final UserCachData userCachData) {

    this.userCachList.put(userCachData.getUserId(), userCachData);
  }

  public UserCachData getUserCachData(final UUID userId, final boolean initialUserCachData) {

    if (this.userCachList.containsKey(userId) == false && initialUserCachData) {
      this.initialUserCachData(userId);
    }
    if (this.userCachList.containsKey(userId)) {
      return this.userCachList.get(userId);
    }
    return null;
  }

  public void removeUserCachData(final UUID userId) {

    if (this.userCachList.containsKey(userId)) {
      this.userCachList.remove(userId);
    }
  }

  public boolean hasUserCachData(final UUID userId) {

    return this.userCachList.containsKey(userId);
  }

  public List<WorkflowMessage> getUserWorkflowMessages(final UUID userId) {

    return this.getUserCachData(userId, true).getWorkflowMessagesList();
  }

  private void initialUserCachData(final UUID userId) {

    if (this.hasUserCachData(userId) == false) {
      final UserCachData userCachData = new UserCachData(userId);
      this.addUserCachData(userCachData);
    }
  }

  public List<UUID> setWorkflowWorkflowMessages(final UUID workflowId, final List<WorkflowMessage> workflowMessageList,
      final ICompanyCachDataManager companyCachDataManager) {

    final List<UUID> userIdentityList = new ArrayList<>();
    final Set<UUID> messageUserIdentityList = workflowMessageList.stream().map(m -> m.getUserId()).collect(Collectors.toSet());

    for (final UUID userIdentity : messageUserIdentityList) {
      if (this.userCachList.containsKey(userIdentity) == false) {
        this.initialUserCachData(userIdentity);
      }

    }

    for (final UserCachData userCachData : this.userCachList.values()) {
      if (userCachData.hasWorkflowCachData(workflowId)) {

        final WorkflowCachData workflowCachData = userCachData.getWorkflowCachData(workflowId, false);
        workflowCachData.setWorkflowMessages(workflowMessageList);
        companyCachDataManager.sendResetMessageToSocket(userCachData.getUserId());
        userIdentityList.add(userCachData.getUserId());
      }
      else {
        if (messageUserIdentityList.contains(userCachData.getUserId())) {

          final List<WorkflowMessage> userMessageList = workflowMessageList
              .stream()
              .filter(m -> m.getUserId() == userCachData.getUserId())
              .collect(Collectors.toList());

          userCachData.setWorkflowMessageList(userMessageList);
          companyCachDataManager.sendResetMessageToSocket(userCachData.getUserId());
          userIdentityList.add(userCachData.getUserId());
        }

      }
    }

    return userIdentityList;

  }

  public List<UUID> removeWorkflowCachData(final UUID workflowId) {

    final List<UUID> userIdentityList = new ArrayList<>();

    for (final UserCachData userCachData : this.userCachList.values()) {
      if (userCachData.removeWorkflowCachData(workflowId)) {
        userIdentityList.add(userCachData.getUserId());
      }
    }

    return userIdentityList;
  }

}
