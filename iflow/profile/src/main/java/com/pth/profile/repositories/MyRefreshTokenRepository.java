package com.pth.profile.repositories;

import com.pth.common.exceptions.EntityAlreadyExistsException;
import com.pth.common.exceptions.EntityPropertyMissingException;
import com.pth.common.exceptions.LostUpdateException;
import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.common.repositories.HibernatePredicateQueryAdapter;
import com.pth.profile.entities.RefreshTokenEntity;
import com.pth.profile.entities.RefreshTokenEntity_;
import com.pth.profile.entities.UserEntity;
import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.transaction.annotation.ReadOnly;
import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Singleton
@Repository
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class MyRefreshTokenRepository implements
                                                                                                  IRefreshTokenRepository {

    //@PersistenceContext(name = "h2")

    @PersistenceContext(name = "h2")
    private final EntityManager entityManager;

    public MyRefreshTokenRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public RefreshTokenEntity save(@NonNull @NotBlank String username,
                                   @NonNull @NotBlank String refreshToken,
                                   @NonNull @NotNull Boolean revoked){

        RefreshTokenEntity tokenEntity = new RefreshTokenEntity(username, refreshToken, revoked);
        this.save(tokenEntity);
        return tokenEntity;
    }

    @Transactional
    private void save(RefreshTokenEntity entity) {

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

    private Optional<RefreshTokenEntity> queryScala(HibernatePredicateQueryAdapter.PredicateBuilder<RefreshTokenEntity> predicateBuilderr) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RefreshTokenEntity> criteriaQuery = criteriaBuilder.createQuery(RefreshTokenEntity.class);

        Root<RefreshTokenEntity> root = criteriaQuery.from(RefreshTokenEntity.class);
        CriteriaQuery<RefreshTokenEntity> criteriaRootQuery = criteriaQuery.select(root);

        if (null != predicateBuilderr) {
            criteriaRootQuery.where(predicateBuilderr.build(criteriaBuilder, root));
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

    @Transactional
    private void update(RefreshTokenEntity entity) {
        try{
            entityManager.merge(entity);
            entityManager.flush();
        }
        catch (OptimisticLockException exception){
            throw new LostUpdateException("The Entity you trying to Update is Outdated please refresh and try it again", exception);
        }
    }

    @Override
    @ReadOnly
    public Optional<RefreshTokenEntity> findByRefreshToken(@NonNull @NotBlank String refreshToken){
        return queryScala((cb, root) -> (cb.equal(root.get(RefreshTokenEntity_.refreshToken), refreshToken)));
    }

    @Override
    @Transactional
    public long updateByUsername(@NonNull @NotBlank String username,
                                 @NonNull @NotNull Boolean revoked){
        Optional<RefreshTokenEntity> tokenEntityOptional = queryScala((cb, root) -> (cb.equal(root.get(RefreshTokenEntity_.username), username)));

        if(tokenEntityOptional.isPresent()){
            RefreshTokenEntity tokenEntity = tokenEntityOptional.get();
            tokenEntity.setRevoked(revoked);
            this.update(tokenEntity);
            return 1;
        }

        return 0;
    }

}
