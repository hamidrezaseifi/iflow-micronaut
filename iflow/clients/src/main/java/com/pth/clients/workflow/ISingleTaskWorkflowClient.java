package com.pth.clients.workflow;

import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowListEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowSaveRequestEdo;

import java.util.Optional;
import java.util.UUID;

public interface ISingleTaskWorkflowClient {

    Optional<SingleTaskWorkflowEdo> read(String authorization,
                                         final UUID id);

    Optional<SingleTaskWorkflowEdo> create(String authorization,
                                           SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo);

    Optional<SingleTaskWorkflowEdo> save(String authorization,
                                         SingleTaskWorkflowSaveRequestEdo requestEdo);

    Optional<SingleTaskWorkflowListEdo> readListForUser(String authorization,
                                                        final UUID id,
                                                        final int status);

    Optional<?> validate(String authorization,
                         SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo);
    
}
