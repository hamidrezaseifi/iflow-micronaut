package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.models.WorkflowSearchFilter;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IWorkflowRepository extends IEntityRepository<WorkflowEntity> {

    List<WorkflowEntity> getListForUser(UUID id, int status);

    List<WorkflowEntity> search(final WorkflowSearchFilter workflowSearchFilter);
}
