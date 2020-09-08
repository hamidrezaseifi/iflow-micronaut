package com.pth.clients.clients.workflow;

import com.pth.common.edo.WorkflowTypeEdo;
import com.pth.common.edo.WorkflowTypeListEdo;

import java.util.Optional;
import java.util.UUID;

public interface IWorkflowTypeClient {

    Optional<WorkflowTypeEdo> readById(String authorization,
                                       final UUID id);

    Optional<WorkflowTypeListEdo> readByCompanyId(String authorization,
                                                  final UUID id);
}
