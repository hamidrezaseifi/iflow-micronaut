package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.WorkflowEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;

import java.util.Optional;

public interface IWorkflowTypeRepository extends IEntityRepository<WorkflowTypeEntity> {

    Optional<WorkflowTypeEntity> getByIdentity(String identity);
}
