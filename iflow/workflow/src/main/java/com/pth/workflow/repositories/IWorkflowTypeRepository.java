package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.WorkflowTypeEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IWorkflowTypeRepository extends IEntityRepository<WorkflowTypeEntity> {

    List<WorkflowTypeEntity> getListByCompanyId(UUID id);

}
