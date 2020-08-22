package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.WorkflowEntity;
import com.pth.workflow.models.WorkflowSearchFilter;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IWorkflowRepository extends IEntityRepository<WorkflowEntity> {

    Optional<WorkflowEntity> getByIdentity(String identity);

    List<WorkflowEntity> getListForUser(String identity, int status);

    List<WorkflowEntity> getListByIdentityList(Set<String> identityList);

    List<WorkflowEntity> search(final WorkflowSearchFilter workflowSearchFilter);
}
