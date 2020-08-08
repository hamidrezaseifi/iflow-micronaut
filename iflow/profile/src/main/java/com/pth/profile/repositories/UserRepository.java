package com.pth.profile.repositories;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserEntity_;
import io.micronaut.data.annotation.Repository;
import io.micronaut.transaction.annotation.ReadOnly;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
@Repository
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class UserRepository extends AEntityRdbmsHibernateRepository<UserEntity>
        implements IUserRepository {
    public UserRepository(EntityManager entityManager) {
        super(UserEntity.class, entityManager);
    }

    @Override
    @ReadOnly
    public Optional<UserEntity> getByUsername(String username) {

        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.username), username)));
        //ServerFilterPhase s;
    }
}
