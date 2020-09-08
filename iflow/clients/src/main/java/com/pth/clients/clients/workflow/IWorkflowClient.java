package com.pth.clients.clients.workflow;

import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.WorkflowListEdo;

import java.util.Optional;
import java.util.UUID;

public interface IWorkflowClient {

    Optional<WorkflowListEdo> searchWorkflow(String authorization,
                                             WorkflowSearchFilterEdo workflowSearchFilterEdo);

    Optional<WorkflowEdo> read(String authorization,
                                       final UUID id);

}
