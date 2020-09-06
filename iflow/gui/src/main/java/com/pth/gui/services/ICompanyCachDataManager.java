package com.pth.gui.services;

import com.pth.gui.models.workflow.WorkflowMessage;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


public interface ICompanyCachDataManager {

  public void setUserWorkflowMessages(final UUID companyId,
                                      final UUID userId,
                                      final List<WorkflowMessage> workflowMessageList);

  public List<WorkflowMessage> getUserWorkflowMessages(UUID companyId,
                                                       UUID userId);

  public void resetUserData(UUID companyId,
                            UUID userId,
                            String authorization,
                            boolean fromController);

  public void resetWorkflowStepData(UUID companyId,
                                    UUID workflowId,
                                    String authorization);

  public void resetUserListData(UUID companyId,
                                Collection<UUID> userIdList,
                                String authorization);

  void sendResetMessageToSocket(final UUID userId);
}
