package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.*;
import com.pth.profile.repositories.IDepartmentRepository;
import org.springframework.stereotype.Repository;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class DepartmentRepository extends AEntityRdbmsHibernateRepository<DepartmentEntity>
        implements IDepartmentRepository {


    @PersistenceContext
    private final EntityManager entityManager;

    public DepartmentRepository(EntityManager entityManager) {
        super(DepartmentEntity.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DepartmentEntity> getByIdentity(String identity) {
        return queryScala((cb, root) -> (cb.equal(root.get(DepartmentEntity_.identity), identity)));
    }

    @Override
    public List<DepartmentEntity> getListByIdentityList(Collection<String> identityList) {
        return queryCollection((cb, root) -> ( root.in(identityList)));
    }

    @Override
    public List<DepartmentEntity> getListByIdCompanyIdentity(String identity) {
        return queryCollection((cb, root) -> (cb.equal(root.get(DepartmentEntity_.companyId), identity)));
    }
}
