package com.pth.iflow.profile.repositories;

import com.pth.iflow.common.repositories.EntityHibernateRepositoryBase;
import com.pth.iflow.profile.entities.UserEntity;
import io.micronaut.context.annotation.Requires;
import org.springframework.stereotype.Repository;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Repository
public class UsersRepository extends EntityHibernateRepositoryBase<UserEntity> {


    public UsersRepository(EntityManager entityManager) {
        super(UserEntity.class, entityManager);
    }
}
