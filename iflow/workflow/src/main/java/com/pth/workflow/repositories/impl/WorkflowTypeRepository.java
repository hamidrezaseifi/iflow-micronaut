package com.pth.workflow.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.workflow.entities.*;
import com.pth.workflow.repositories.IWorkflowTypeRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

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

        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<WorkflowTypeEntity> query = criteriaBuilder.createQuery(WorkflowTypeEntity.class);
        final Root<WorkflowTypeEntity> root = query.from(WorkflowTypeEntity.class);


        SetJoin<WorkflowTypeEntity, CompanyWorkflowTypeEntity> companyWorkflowTypes = root.join(WorkflowTypeEntity_.companyWorkflowTypes);

        query.select(root).where(criteriaBuilder.equal(companyWorkflowTypes.get(CompanyWorkflowTypeEntity_.companyId), id));
        final TypedQuery<WorkflowTypeEntity> typedQuery = getEntityManager().createQuery(query);

         final String qr = typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
         System.out.println("search workflow query: " + qr);
        final List<WorkflowTypeEntity> list = typedQuery.getResultList();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowTypeEntity> getListByIdentityList(Set<String> identityList) {
        return queryCollection((cb, root) -> ( root.in(identityList)));
    }
}
