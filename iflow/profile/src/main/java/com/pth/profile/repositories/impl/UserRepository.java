package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.repositories.IUserRepository;
import io.micronaut.data.annotation.Repository;
import io.micronaut.spring.tx.annotation.Transactional;
import io.micronaut.transaction.annotation.ReadOnly;

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
}
