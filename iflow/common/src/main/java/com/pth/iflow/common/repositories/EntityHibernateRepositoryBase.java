package com.pth.iflow.common.repositories;

import com.pth.iflow.common.exceptions.EntityAlreadyExistsException;
import com.pth.iflow.common.exceptions.EntityPropertyMissingException;
import com.pth.iflow.common.exceptions.LostUpdateException;
import io.micronaut.spring.tx.annotation.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import com.pth.iflow.common.repositories.HibernatePredicateQueryAdapter.PredicateBuilder;
import com.pth.iflow.common.repositories.HibernatePredicateQueryAdapter.OrderBuilder;

@Repository
public abstract class EntityHibernateRepositoryBase<TEntity>
        implements IEntityRepository<TEntity> {

    @PersistenceContext
    private final EntityManager entityManager;
    private final Class<TEntity> entityType;

    private final HibernatePredicateQueryAdapter hibernatePredicateQueryAdapter;

    public EntityHibernateRepositoryBase(Class<TEntity> entityType,
                                         EntityManager entityManager) {
        super();

        this.entityType = entityType;
        this.entityManager = entityManager;

        this.hibernatePredicateQueryAdapter = new HibernatePredicateQueryAdapter();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TEntity> getAll() {
        return queryCollection();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TEntity> getById(UUID id) {
        return Optional.ofNullable(entityManager.find(entityType, id));
    }

    @Override
    @Transactional(readOnly = false)
    public void save(TEntity entity) {

        try {
            entityManager.persist(entity);
            entityManager.flush();
        } catch (PersistenceException exception) {
            Throwable innerException = exception.getCause();

            if (   innerException != null
                && innerException instanceof ConstraintViolationException) {

                ConstraintViolationException constraintException = (ConstraintViolationException) innerException;
                String constraintName = constraintException.getConstraintName();

                if (constraintName != null
                    && (constraintName.replace("\"", "").toLowerCase().startsWith("pk")
                        || constraintName.replace("\"", "").toLowerCase().startsWith("public.pk")
                        || constraintName.replace("\"", "").toLowerCase().startsWith("uk")
                        || constraintName.replace("\"", "").toLowerCase().startsWith("public.uk"))) {

                    throw new EntityAlreadyExistsException(
                            "Failed to persist entity since id / name entity already taken", exception);
                }

                throw new EntityPropertyMissingException("Failed to persist entity since missing required fields",
                                                         exception);
            }

            throw exception;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void update(TEntity entity) {
        try{
            entityManager.merge(entity);
            entityManager.flush();
        }
        catch (OptimisticLockException exception){
                throw new LostUpdateException("The Entity you trying to Update is Outdated please refresh and try it again", exception);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(TEntity entity) {
        entityManager.remove(entity);
        entityManager.flush();
    }

    protected Optional<TEntity> queryScala(PredicateBuilder<TEntity> predicateBuilder) {
        return hibernatePredicateQueryAdapter.queryScala(entityManager, entityType, predicateBuilder);
    }

    protected Optional<TEntity> queryScala(PredicateBuilder<TEntity> predicateBuilder,
                                           OrderBuilder<TEntity> orderBuilder) {
        return hibernatePredicateQueryAdapter.queryScala(entityManager, entityType, predicateBuilder, orderBuilder);
    }

    protected List<TEntity> queryCollection() {
        return hibernatePredicateQueryAdapter.queryCollection(entityManager, entityType, null);
    }

    protected List<TEntity> queryCollection(PredicateBuilder<TEntity> predicateBuilder) {
        return hibernatePredicateQueryAdapter.queryCollection(entityManager, entityType, predicateBuilder);
    }


    protected List<TEntity> queryCollection(PredicateBuilder<TEntity> predicateBuilder,
                                            OrderBuilder<TEntity> orderBuilder) {
        return hibernatePredicateQueryAdapter.queryCollection(entityManager, entityType, predicateBuilder, orderBuilder);
    }

    protected int queryCountItems(PredicateBuilder<TEntity> predicateBuilder) {

        return hibernatePredicateQueryAdapter.queryCountItems(entityManager, entityType, predicateBuilder);
    }
}