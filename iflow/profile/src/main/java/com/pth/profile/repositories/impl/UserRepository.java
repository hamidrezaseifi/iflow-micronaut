package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserEntity_;
import com.pth.profile.repositories.IUserRepository;
import io.micronaut.data.annotation.Repository;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.Optional;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class UserRepository extends AEntityRdbmsHibernateRepository<UserEntity>
        implements IUserRepository {
    public UserRepository(EntityManager entityManager) {
        super(UserEntity.class, entityManager);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getByUsername(String username) {

        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.username), username)));
        //ServerFilterPhase s;
    }

    @Override
    public Optional<UserEntity> getByIdentity(String identity) {
        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.identity), identity)));
    }

    @Override
    public Optional<UserEntity> getUserByEmail(String email) {
        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.email), email)));
    }
}
