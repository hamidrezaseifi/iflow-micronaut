package com.pth.profile.repositories;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.repositories.IUserRepository;
import io.micronaut.context.annotation.Requires;
import io.micronaut.spring.tx.annotation.Transactional;
import org.springframework.stereotype.Repository;
import com.pth.profile.entities.UserEntity_;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
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
    @Transactional(readOnly = true)
    public Optional<UserEntity> getByUsername(String username) {

        return queryScala((cb, root) -> (cb.equal(root.get(UserEntity_.username), username)));
    }
}
