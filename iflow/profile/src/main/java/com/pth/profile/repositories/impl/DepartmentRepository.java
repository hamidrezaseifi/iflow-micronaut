package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.DepartmentEntity;
import com.pth.profile.entities.UserGroupEntity;
import com.pth.profile.repositories.IDepartmentRepository;
import com.pth.profile.repositories.IUserGroupRepository;
import io.micronaut.data.annotation.Repository;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class DepartmentRepository extends AEntityRdbmsHibernateRepository<DepartmentEntity>
        implements IDepartmentRepository {
    public DepartmentRepository(EntityManager entityManager) {
        super(DepartmentEntity.class, entityManager);
    }


}
