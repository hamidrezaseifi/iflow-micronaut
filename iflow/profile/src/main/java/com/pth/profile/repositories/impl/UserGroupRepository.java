package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.entities.UserEntity_;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.repositories.IUserGroupRepository;
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
public class UserGroupRepository extends AEntityRdbmsHibernateRepository<UserGroupEntity>
        implements IUserGroupRepository {
    public UserGroupRepository(EntityManager entityManager) {
        super(UserGroupEntity.class, entityManager);
    }


}
