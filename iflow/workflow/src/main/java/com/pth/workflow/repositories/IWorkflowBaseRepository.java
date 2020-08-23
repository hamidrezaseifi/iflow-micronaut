package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.models.base.IWorkflowBaseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IWorkflowBaseRepository<W extends IWorkflowBaseEntity> extends IEntityRepository<W> {

    boolean isWorkflowNew(UUID workflowId);

    Optional<W> getByWorkflowId(UUID workflowId);

    Optional<W> getByIdentity(String identity);

    List<W> getListForUser(UUID id, int status);

    List<W> getListByIdentityList(Set<String> identityList);
}
