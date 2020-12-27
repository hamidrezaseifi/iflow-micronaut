package com.pth.common.repositories;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

public class HibernatePredicateQueryAdapter {

    public <T> Optional<T> queryScala(EntityManager entityManager,
                                      Class<T> entityClass,
                                      PredicateBuilder<T> predicateBuilder) {
        return queryScala(entityManager, entityClass, predicateBuilder, null);
    }

    public <T> Optional<T> queryScala(EntityManager entityManager,
                                      Class<T> entityClass,
                                      PredicateBuilder<T> predicateBuilder,
                                      OrderBuilder<T> orderBuilder) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);

        Root<T> root = criteriaQuery.from(entityClass);
        CriteriaQuery<T> criteriaRootQuery = criteriaQuery.select(root);

        if (null != predicateBuilder) {
            criteriaRootQuery.where(predicateBuilder.build(criteriaBuilder, root));
        }

        if (null != orderBuilder) {
            criteriaRootQuery.orderBy(orderBuilder.build(criteriaBuilder, root));
        }

        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaRootQuery);

        typedQuery.setMaxResults(1);

        T entity = null;

        try {
            entity = typedQuery.getSingleResult();
        } catch (NoResultException exception) {
            //Info: Ignore!
        }

        return Optional.ofNullable(entity);
    }

    public <T> List<T> queryCollection(EntityManager entityManager,
                                       Class<T> entityClass) {
        return queryCollection(entityManager, entityClass, null, null);
    }

    public <T> List<T> queryCollection(EntityManager entityManager,
                                       Class<T> entityClass,
                                       PredicateBuilder<T> predicateBuilder) {
        return queryCollection(entityManager, entityClass, predicateBuilder, null);
    }

    public <T> List<T> queryCollection(EntityManager entityManager,
                                       Class<T> entityClass,
                                       PredicateBuilder<T> predicateBuilder,
                                       OrderBuilder<T> orderBuilder) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);

        Root<T> root = criteriaQuery.from(entityClass);
        CriteriaQuery<T> criteriaRootQuery = criteriaQuery.select(root);

        if (null != predicateBuilder) {
            criteriaRootQuery.where(predicateBuilder.build(criteriaBuilder, root));
        }

        if (null != orderBuilder) {
            criteriaRootQuery.orderBy(orderBuilder.build(criteriaBuilder, root));
        }

        TypedQuery<T> typedQuery = entityManager.createQuery(criteriaRootQuery);

        return typedQuery.getResultList();
    }

    public <T> int deleteCollection(EntityManager entityManager,
                                       Class<T> entityClass,
                                       PredicateBuilder<T> predicateBuilder) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> criteriaDelete = criteriaBuilder.createCriteriaDelete(entityClass);

        Root<T> root = criteriaDelete.from(entityClass);

        if (null != predicateBuilder) {
            criteriaDelete.where(predicateBuilder.build(criteriaBuilder, root));
        }

        Query typedQuery = entityManager.createQuery(criteriaDelete);
        return typedQuery.executeUpdate();
    }

    public <T> int queryCountItems(EntityManager entityManager,
                                   Class<T> entityClass,
                                   PredicateBuilder<T> predicateBuilder) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = countQuery.from(entityClass);
        countQuery.select(criteriaBuilder.count(root));
        if (null != predicateBuilder) {
            countQuery.where(predicateBuilder.build(criteriaBuilder, root));
        }

        Long total = entityManager.createQuery(countQuery).getSingleResult();


        return total.intValue();
    }

    public interface PredicateBuilder<T> {
        Predicate build(CriteriaBuilder criteriaBuilder,
                        Root<T> root);
    }

    public interface OrderBuilder<T> {
        List<Order> build(CriteriaBuilder criteriaBuilder,
                          Root<T> root);
    }

}