package com.pth.profile.repositories.impl;

import com.pth.common.exceptions.EntityAlreadyExistsException;
import com.pth.common.exceptions.EntityPropertyMissingException;
import com.pth.common.exceptions.LostUpdateException;
import com.pth.common.repositories.HibernatePredicateQueryAdapter;
import com.pth.profile.authentication.entities.RefreshTokenEntity;
import com.pth.profile.authentication.entities.RefreshTokenEntity_;
import com.pth.profile.repositories.IRefreshTokenRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import io.micronaut.spring.tx.annotation.Transactional;
import org.hibernate.exception.ConstraintViolationException;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Singleton
@Repository("h2")
@Transactional("h2")
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class H2RefreshTokenRepository implements IRefreshTokenRepository {

    @PersistenceContext (name="h2", unitName = "h2")
    private final EntityManager entityManager;

    public H2RefreshTokenRepository(@Named("h2") EntityManager entityManager) {
        this.entityManager = entityManager;

    }


    @Override
    @Transactional(readOnly = false)
    public Optional<RefreshTokenEntity> save(String username,
                                             String accessToken,
                                             String refreshToken,
                                             Date issuedAt) {
        RefreshTokenEntity tokenEntity = new RefreshTokenEntity(username, accessToken, refreshToken, issuedAt);
        this.save(tokenEntity);
        return Optional.of(tokenEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshTokenEntity> findByRefreshToken(@NonNull @NotBlank String refreshToken){
        return queryScala((cb, root) -> (cb.equal(root.get(RefreshTokenEntity_.refreshToken), refreshToken)));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshTokenEntity> findByUsername(@NonNull @NotBlank String username){
        return queryScala((cb, root) -> (cb.equal(root.get(RefreshTokenEntity_.username), username)));
    }



    private Optional<RefreshTokenEntity> queryScala(HibernatePredicateQueryAdapter.PredicateBuilder<RefreshTokenEntity> predicateBuilder) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RefreshTokenEntity> criteriaQuery = criteriaBuilder.createQuery(RefreshTokenEntity.class);

        Root<RefreshTokenEntity> root = criteriaQuery.from(RefreshTokenEntity.class);
        CriteriaQuery<RefreshTokenEntity> criteriaRootQuery = criteriaQuery.select(root);

        if (null != predicateBuilder) {
            criteriaRootQuery.where(predicateBuilder.build(criteriaBuilder, root));
        }

        TypedQuery<RefreshTokenEntity> typedQuery = entityManager.createQuery(criteriaRootQuery);

        typedQuery.setMaxResults(1);

        RefreshTokenEntity entity = null;

        try {
            entity = typedQuery.getSingleResult();
        } catch (NoResultException exception) {
            //Info: Ignore!
        }

        return Optional.ofNullable(entity);
    }

    @Transactional(readOnly = false)
    public void save(RefreshTokenEntity entity) {

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

    @Transactional(readOnly = false)
    public void update(RefreshTokenEntity entity) {
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
    public void delete(RefreshTokenEntity entity) {
        try{
            entityManager.remove(entity);
            entityManager.flush();
        }
        catch (OptimisticLockException exception){
            throw new LostUpdateException("The Entity you trying to Delete is Outdated please refresh and try it again", exception);
        }
    }

    @Transactional(readOnly = false)
    public void updateOrCreate(String username,
                               String accessToken,
                               String refreshToken,
                               Date issuedAt) {

        Optional<RefreshTokenEntity> entityOptional = findByUsername(username);
        if(entityOptional.isPresent()){
            RefreshTokenEntity entity = entityOptional.get();
            entity.setIssuedAt(issuedAt);
            entity.setRefreshToken(refreshToken);
            entity.setAccessToken(accessToken);
            update(entity);
        }
        else{
            save(username, accessToken, refreshToken, issuedAt);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<RefreshTokenEntity> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RefreshTokenEntity> criteriaQuery = criteriaBuilder.createQuery(RefreshTokenEntity.class);

        Root<RefreshTokenEntity> root = criteriaQuery.from(RefreshTokenEntity.class);
        CriteriaQuery<RefreshTokenEntity> criteriaRootQuery = criteriaQuery.select(root);


        TypedQuery<RefreshTokenEntity> typedQuery = entityManager.createQuery(criteriaRootQuery);

        return typedQuery.getResultList();
    }

}
