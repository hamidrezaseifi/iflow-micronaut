package com.pth.gui.services;

import com.pth.gui.models.workflow.WorkflowMessage;

import java.util.List;
import java.util.UUID;

public interface IWorkflowMessageHandler {

  public void callUserMessageReset(UUID companyId,
                                   UUID userId,
                                   boolean fromController,
                                   String authorization);

  public List<WorkflowMessage> readUserMessages(UUID companyId,
                                                UUID userId);

}
