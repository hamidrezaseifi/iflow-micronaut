package com.pth.workflow.repositories.impl;

import com.pth.common.edo.enums.EWorkflowActionStatus;
import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.workflow.entities.WorkflowActionEntity;
import com.pth.workflow.entities.WorkflowActionEntity_;
import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.entities.WorkflowEntity_;
import com.pth.workflow.models.WorkflowSearchFilter;
import com.pth.workflow.repositories.IWorkflowRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import org.springframework.stereotype.Repository;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

@Singleton
@Repository
@Transactional
@Named("workflowRepository")
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class WorkflowRepository extends AEntityRdbmsHibernateRepository<WorkflowEntity> implements IWorkflowRepository {

    public WorkflowRepository(EntityManager entityManager) {
        super(WorkflowEntity.class, entityManager);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkflowEntity> getByIdentity(String identity) {
        return queryScala((cb, root) -> (cb.equal(root.get(WorkflowEntity_.identity), identity)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowEntity> getListForUser(UUID id,
                                               int status) {

        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<WorkflowEntity> query = criteriaBuilder.createQuery(WorkflowEntity.class);
        final Root<WorkflowEntity> root = query.from(WorkflowEntity.class);
        query.select(root);

        final Subquery<UUID> assignSubQuery = query.subquery(UUID.class);
        final Root<WorkflowActionEntity> assignRoot = assignSubQuery.from(WorkflowActionEntity.class);

        final Path<UUID> assignPath = assignRoot.get(WorkflowActionEntity_.assignToId);
        final Path<UUID> workflowIdPath = assignRoot.get(WorkflowActionEntity_.workflowEntity).get(WorkflowEntity_.id);

        final Predicate assignInPredicate = assignPath.in(Arrays.asList(id));
        final Predicate workflowIdPredicate = criteriaBuilder.equal(workflowIdPath, root.get(WorkflowActionEntity_.ID));

        assignSubQuery.select(workflowIdPath).where(criteriaBuilder.and(assignInPredicate, workflowIdPredicate));

        final Path<UUID> path = root.get(WorkflowActionEntity_.ID);
        final Predicate predicate = path.in(assignSubQuery);

        Predicate finalPredicate = predicate;

        if (status > -1) {
            finalPredicate = criteriaBuilder.and(finalPredicate, criteriaBuilder.equal(root.get("status"), status));
        }
        if (finalPredicate != null) {
            query.where(finalPredicate);
        }

        final TypedQuery<WorkflowEntity> typedQuery = getEntityManager().createQuery(query);

        // final String qr =
        // typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
        // System.out.println("search workflow query: " + qr);
        final List<WorkflowEntity> list = typedQuery.getResultList();
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowEntity> getListByIdentityList(Set<String> identityList) {
        return queryCollection((cb, root) -> ( root.in(identityList)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowEntity> search(WorkflowSearchFilter workflowSearchFilter) {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<WorkflowEntity> query = criteriaBuilder.createQuery(WorkflowEntity.class);
        final Root<WorkflowEntity> root = query.from(WorkflowEntity.class);
        query.select(root);

        Predicate finalPredicate = criteriaBuilder.equal(root.get(WorkflowEntity_.companyId), workflowSearchFilter.getCompanyId());

        if (workflowSearchFilter.getStatusSet().isEmpty() == false) {
            final Path<Integer> path = root.get(WorkflowEntity_.status);
            final Predicate predicate = path.in(workflowSearchFilter.getStatusSet());
            finalPredicate = finalPredicate == null ? predicate : criteriaBuilder.and(finalPredicate, predicate);
            // query.where(predicate);
        }

        if (workflowSearchFilter.getAssignedUserIdSet().isEmpty() == false) {

            final Subquery<UUID> assignSubQuery = query.subquery(UUID.class);
            final Root<WorkflowActionEntity> assignRoot = assignSubQuery.from(WorkflowActionEntity.class);

            final Path<UUID> assignPath = assignRoot.get(WorkflowActionEntity_.assignToId);
            final Path<UUID> workflowIdPath = assignRoot.get(WorkflowActionEntity_.workflowEntity).get(WorkflowEntity_.id);
            final Path<Integer> actionStatusPath = assignRoot.get(WorkflowActionEntity_.status);

            final Predicate assignInPredicate = assignPath.in(workflowSearchFilter.getAssignedUserIdSet());
            final Predicate workflowIdPredicate = criteriaBuilder.equal(workflowIdPath, root.get(WorkflowEntity_.id));
            final Predicate actionIsActivePredicate = actionStatusPath
                    .in(EWorkflowActionStatus.OPEN.getValue(),
                        EWorkflowActionStatus.INITIALIZE.getValue());

            assignSubQuery
                    .select(workflowIdPath)
                    .where(criteriaBuilder.and(assignInPredicate, criteriaBuilder.and(actionIsActivePredicate, workflowIdPredicate)));

            final Path<UUID> path = root.get(WorkflowEntity_.id);
            final Predicate predicate = path.in(assignSubQuery);

            finalPredicate = finalPredicate == null ? predicate : criteriaBuilder.and(finalPredicate, predicate);
        }

        if (workflowSearchFilter.getWorkflowStepIdSet().isEmpty() == false) {
            final Path<UUID> path = root.get(WorkflowEntity_.currentStepId);
            final Predicate predicate = path.in(workflowSearchFilter.getWorkflowStepIdSet());
            finalPredicate = finalPredicate == null ? predicate : criteriaBuilder.and(finalPredicate, predicate);
        }

        if (workflowSearchFilter.getWorkflowTypeIdSet().isEmpty() == false) {
            final Path<UUID> path = root.get(WorkflowEntity_.workflowTypeId);
            final Predicate predicate = path.in(workflowSearchFilter.getWorkflowTypeIdSet());
            finalPredicate = finalPredicate == null ? predicate : criteriaBuilder.and(finalPredicate, predicate);
            // query.where(predicate);
        }

        if (finalPredicate != null) {
            query.where(finalPredicate);
        }

        final TypedQuery<WorkflowEntity> typedQuery = getEntityManager().createQuery(query);

        final String qr = typedQuery.unwrap(org.hibernate.query.Query.class).getQueryString();
        System.out.println("search workflow query: " + qr);
        final List<WorkflowEntity> list = typedQuery.getResultList();
        return list;
    }
}
