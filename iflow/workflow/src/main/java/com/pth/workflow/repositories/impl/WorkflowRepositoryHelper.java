package com.pth.workflow.repositories.impl;

import com.pth.workflow.entities.workflow.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.UUID;

public class WorkflowRepositoryHelper {

    public static <W> TypedQuery<W> createQueryForGetListForUser(EntityManager entityManager,
                                                                 Class queryClass,
                                                                 UUID id,
                                                                 int status){
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<W> query = criteriaBuilder.createQuery(queryClass);
        final Root<W> root = query.from(queryClass);
        query.select(root);

        final Subquery<UUID> assignSubQuery = query.subquery(UUID.class);
        final Root<WorkflowActionEntity> assignRoot = assignSubQuery.from(WorkflowActionEntity.class);

        final Path<UUID> assignPath = assignRoot.get(WorkflowActionEntity_.assignToId);
        final Path<UUID> workflowIdPath = assignRoot.get(WorkflowActionEntity_.workflowEntity).get(WorkflowEntity_.id);

        final Predicate assignInPredicate = assignPath.in(Arrays.asList(id));
        final Predicate workflowIdPredicate = criteriaBuilder.equal(workflowIdPath, root.get("id"));

        assignSubQuery.select(workflowIdPath).where(criteriaBuilder.and(assignInPredicate, workflowIdPredicate));

        final Path<UUID> path = root.get("workflowId");
        final Predicate predicate = path.in(assignSubQuery);

        Predicate finalPredicate = predicate;

        if (status > -1) {
            finalPredicate = criteriaBuilder.and(finalPredicate, criteriaBuilder.equal(root.get("status"), status));
        }
        if (finalPredicate != null) {
            query.where(finalPredicate);
        }

        final TypedQuery<W> typedQuery = entityManager.createQuery(query);

        return typedQuery;
    }

}
