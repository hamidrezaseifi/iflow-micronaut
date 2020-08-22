package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.WorkflowEntity;

import java.util.Optional;

public interface IWorkflowRepository extends IEntityRepository<WorkflowEntity> {

    Optional<WorkflowEntity> getByIdentity(String identity);
}
