package com.pth.workflow.services;

import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.workflow.entities.workflow.WorkflowMessageEntity;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWorkflowMessageService {
    List<WorkflowMessageEntity> getListForUser(UUID userId, int status);

    List<WorkflowMessageEntity> getListForWorkflow(UUID workflowID);

    Optional<WorkflowMessageEntity> save(WorkflowMessageEntity message);

    void updateWorkflowMessageStatus(UUID workflowID,
                                     UUID stepId,
                                     EWorkflowMessageStatus status);

    void updateUserAndWorkflowMessageStatus(UUID workflowID,
                                            UUID stepId,
                                            UUID userId,
                                            EWorkflowMessageStatus status);
}
