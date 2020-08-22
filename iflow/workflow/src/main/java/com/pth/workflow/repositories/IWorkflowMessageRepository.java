package com.pth.workflow.repositories;

import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.WorkflowEntity;
import com.pth.workflow.entities.workflow.WorkflowMessageEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWorkflowMessageRepository extends IEntityRepository<WorkflowMessageEntity> {

    Optional<WorkflowMessageEntity> getByIdentity(String identity);

    List<WorkflowMessageEntity> getListForUser(UUID userId, int status);

    List<WorkflowMessageEntity> getListForWorkflow(UUID workflowID);

    void updateWorkflowMessageStatus(UUID workflowID,
                                     UUID stepId,
                                     EWorkflowMessageStatus status);

    void updateWorkflowMessageStatus(UUID workflowID,
                                     UUID stepId,
                                     UUID userId,
                                     EWorkflowMessageStatus status);
}
