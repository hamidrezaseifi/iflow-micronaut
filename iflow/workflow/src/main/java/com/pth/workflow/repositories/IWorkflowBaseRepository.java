package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.models.base.IWorkflowBaseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IWorkflowBaseRepository<W extends IWorkflowBaseEntity> extends IEntityRepository<W> {

    Optional<W> getByWorkflowId(UUID workflowId);

    Optional<W> getByIdentity(String identity);

    List<W> getListForUser(String identity, int status);

    List<W> getListByIdentityList(Set<String> identityList);
}
