package com.pth.workflow.repositories;

import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.WorkflowEntity;
import com.pth.workflow.entities.workflow.WorkflowMessageEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWorkflowMessageRepository extends IEntityRepository<WorkflowMessageEntity> {

    List<WorkflowMessageEntity> getListForUser(final UUID userId, final int status);

    List<WorkflowMessageEntity> getListForWorkflow(final UUID workflowID);

    void updateWorkflowMessageStatus(final UUID workflowID,
                                     final UUID stepId,
                                     final EWorkflowMessageStatus status);

    void updateWorkflowMessageStatus(final UUID workflowID,
                                     final UUID stepId,
                                     final UUID userId,
                                     final EWorkflowMessageStatus status);
}
