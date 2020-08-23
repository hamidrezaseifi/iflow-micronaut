package com.pth.workflow.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.workflow.entities.*;
import com.pth.workflow.repositories.IInvoiceWorkflowRepository;
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
public class InvoiceWorkflowRepository extends AEntityRdbmsHibernateRepository<InvoiceWorkflowEntity>
        implements IInvoiceWorkflowRepository {


    public InvoiceWorkflowRepository(EntityManager entityManager) {
        super(InvoiceWorkflowEntity.class, entityManager);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isWorkflowNew(UUID workflowId) {
        Optional<InvoiceWorkflowEntity> modelOptional = getByWorkflowId(workflowId);
        return modelOptional.isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceWorkflowEntity> getByWorkflowId(UUID workflowId) {
        return queryScala((cb, root) -> (cb.equal(root.get(InvoiceWorkflowEntity_.workflowId), workflowId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceWorkflowEntity> getByIdentity(String identity) {
        return queryScala((cb, root) -> (cb.equal(
                root.get(InvoiceWorkflowEntity_.workflow).get(WorkflowEntity_.IDENTITY), identity)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceWorkflowEntity> getListForUser(UUID id,
                                                      int status) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<InvoiceWorkflowEntity> query = criteriaBuilder.createQuery(InvoiceWorkflowEntity.class);
        final Root<InvoiceWorkflowEntity> root = query.from(InvoiceWorkflowEntity.class);
        query.select(root);

        final Subquery<UUID> assignSubQuery = query.subquery(UUID.class);
        final Root<WorkflowActionEntity> assignRoot = assignSubQuery.from(WorkflowActionEntity.class);

        final Path<UUID> assignPath = assignRoot.get(WorkflowActionEntity_.assignToId);
        final Path<UUID> workflowIdPath = assignRoot.get(WorkflowActionEntity_.workflowEntity).get(WorkflowEntity_.id);

        final Predicate assignInPredicate = assignPath.in(Arrays.asList(id));
        final Predicate workflowIdPredicate =
                criteriaBuilder.equal(workflowIdPath,
                                      root.get(InvoiceWorkflowEntity_.workflow).get(WorkflowEntity_.id));

        assignSubQuery.select(workflowIdPath).where(criteriaBuilder.and(assignInPredicate, workflowIdPredicate));

        final Path<UUID> path = root.get(InvoiceWorkflowEntity_.workflowId);
        final Predicate predicate = path.in(assignSubQuery);

        Predicate finalPredicate = predicate;

        if (status > -1) {
            finalPredicate = criteriaBuilder.and(finalPredicate, criteriaBuilder.equal(root.get("status"), status));
        }
        if (finalPredicate != null) {
            query.where(finalPredicate);
        }

        final TypedQuery<InvoiceWorkflowEntity> typedQuery = getEntityManager().createQuery(query);

        // final String qr =
        // typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
        // System.out.println("search workflow query: " + qr);
        final List<InvoiceWorkflowEntity> list = typedQuery.getResultList();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<InvoiceWorkflowEntity> getListByIdentityList(Set<String> identityList) {
        return queryCollection((cb, root) -> ( root.in(identityList)));
    }
}
