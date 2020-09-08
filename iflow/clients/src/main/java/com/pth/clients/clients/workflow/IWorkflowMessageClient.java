package com.pth.clients.clients.workflow;

import com.pth.common.edo.WorkflowMessageListEdo;

import java.util.Optional;
import java.util.UUID;

public interface IWorkflowMessageClient {

    Optional<WorkflowMessageListEdo> readUserWorkflowMessageList(String authorization,
                                                                 UUID id,
                                                                 int status);

    Optional<WorkflowMessageListEdo> readUserWorkflowMessageList(String authorization,
                                                                 UUID id);

    Optional<WorkflowMessageListEdo> readWorkflowWorkflowMessageList(String authorization,
                                                                     final UUID id);
}
