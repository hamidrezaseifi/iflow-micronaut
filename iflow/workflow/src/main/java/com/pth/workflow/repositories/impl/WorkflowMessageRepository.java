package com.pth.workflow.repositories.impl;

import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.workflow.entities.WorkflowMessageEntity;
import com.pth.workflow.entities.WorkflowMessageEntity_;
import com.pth.workflow.repositories.IWorkflowMessageRepository;
import io.micronaut.spring.tx.annotation.Transactional;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.UUID;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class WorkflowMessageRepository extends AEntityRdbmsHibernateRepository<WorkflowMessageEntity>
        implements IWorkflowMessageRepository {

    public WorkflowMessageRepository(EntityManager entityManager) {
        super(WorkflowMessageEntity.class, entityManager);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowMessageEntity> getListForUser(UUID userId,
                                                      int status) {
        if(status > 0){
            return queryCollection((cb, root) -> ( cb.and(cb.equal(root.get(WorkflowMessageEntity_.status), status),
                                                          cb.equal(root.get(WorkflowMessageEntity_.userId), userId))));
        }
        return queryCollection((cb, root) -> (cb.equal(root.get(WorkflowMessageEntity_.userId), userId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowMessageEntity> getListForWorkflow(UUID workflowID) {
        return queryCollection((cb, root) -> (cb.equal(root.get(WorkflowMessageEntity_.workflowId), workflowID)));
    }

    @Override
    @Transactional
    public void updateWorkflowMessageStatus(UUID workflowID,
                                            UUID stepId,
                                            EWorkflowMessageStatus status) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<WorkflowMessageEntity> update =
                cb.createCriteriaUpdate(WorkflowMessageEntity.class);

        Root<WorkflowMessageEntity> root = update.from(WorkflowMessageEntity.class);

        update.set(WorkflowMessageEntity_.status, status.getValue());
        update.where(cb.and(cb.equal(root.get(WorkflowMessageEntity_.workflowId), workflowID),
                            cb.equal(root.get(WorkflowMessageEntity_.stepId), stepId)));

        this.getEntityManager().createQuery(update).executeUpdate();
    }

    @Override
    @Transactional
    public void updateWorkflowMessageStatus(UUID workflowID,
                                            UUID stepId,
                                            UUID userId,
                                            EWorkflowMessageStatus status) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<WorkflowMessageEntity> update =
                cb.createCriteriaUpdate(WorkflowMessageEntity.class);

        Root<WorkflowMessageEntity> root = update.from(WorkflowMessageEntity.class);

        update.set(WorkflowMessageEntity_.status, status.getValue());
        update.where(cb.and(cb.equal(root.get(WorkflowMessageEntity_.workflowId), workflowID),
                            cb.equal(root.get(WorkflowMessageEntity_.stepId), stepId),
                            cb.equal(root.get(WorkflowMessageEntity_.userId), userId)));

        this.getEntityManager().createQuery(update).executeUpdate();

    }
}
