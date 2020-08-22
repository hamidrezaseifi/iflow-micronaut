package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.SingleTaskWorkflowEntity;
import com.pth.workflow.entities.workflow.WorkflowEntity;

import java.util.Optional;

public interface ISingleTaskWorkflowRepository extends IWorkflowBaseRepository<SingleTaskWorkflowEntity> {

    Optional<SingleTaskWorkflowEntity> getByIdentity(String identity);
}
