package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.entities.CompanyEntity_;
import com.pth.profile.repositories.ICompanyRepository;
import io.micronaut.data.annotation.Repository;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.Optional;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class CompanyRepository extends AEntityRdbmsHibernateRepository<CompanyEntity>
        implements ICompanyRepository {
    public CompanyRepository(EntityManager entityManager) {
        super(CompanyEntity.class, entityManager);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyEntity> getByIdentity(String identity) {
        return queryScala((cb, root) -> (cb.equal(root.get(CompanyEntity_.identity), identity)));
    }
}
