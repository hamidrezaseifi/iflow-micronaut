package com.pth.clients.workflow;

import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.WorkflowListEdo;

import java.util.Optional;
import java.util.UUID;

public interface IWorkflowClient {

    Optional<WorkflowListEdo> searchWorkflow(String authorization,
                                             WorkflowSearchFilterEdo workflowSearchFilterEdo);

    Optional<WorkflowEdo> readWorkflow(String authorization,
                                       final UUID id);

}
