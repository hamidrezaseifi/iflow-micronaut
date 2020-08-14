package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.repositories.IUserGroupRepository;
import org.springframework.stereotype.Repository;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.Optional;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class UserGroupRepository extends AEntityRdbmsHibernateRepository<UserGroupEntity>
        implements IUserGroupRepository {
    public UserGroupRepository(EntityManager entityManager) {
        super(UserGroupEntity.class, entityManager);
    }


}
