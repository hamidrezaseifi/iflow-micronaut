package com.pth.workflow.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.workflow.entities.workflow.WorkflowEntity;
import com.pth.workflow.entities.workflow.WorkflowEntity_;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity_;
import com.pth.workflow.repositories.IWorkflowTypeRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class WorkflowTypeRepository extends AEntityRdbmsHibernateRepository<WorkflowTypeEntity>
        implements IWorkflowTypeRepository {

    public WorkflowTypeRepository(EntityManager entityManager) {
        super(WorkflowTypeEntity.class, entityManager);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkflowTypeEntity> getByIdentity(String identity) {
        return queryScala((cb, root) -> (cb.equal(root.get(WorkflowTypeEntity_.identity), identity)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowTypeEntity> getListByCompanyId(UUID id) {
        return queryCollection((cb, root) -> (cb.equal(root.get(WorkflowTypeEntity_.companyId), id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowTypeEntity> getListByIdentityList(Set<String> identityList) {
        return queryCollection((cb, root) -> ( root.in(identityList)));
    }
}
