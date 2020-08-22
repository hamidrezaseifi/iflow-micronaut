package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.models.base.IWorkflowBaseEntity;

import java.util.Optional;

public interface IWorkflowBaseRepository<W extends IWorkflowBaseEntity> extends IEntityRepository<W> {

    Optional<W> getByIdentity(String identity);
}
