package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.WorkflowEntity;
import com.pth.workflow.entities.workflow.WorkflowMessageEntity;

import java.util.Optional;

public interface IWorkflowMessageRepository extends IEntityRepository<WorkflowMessageEntity> {

    Optional<WorkflowMessageEntity> getByIdentity(String identity);
}
