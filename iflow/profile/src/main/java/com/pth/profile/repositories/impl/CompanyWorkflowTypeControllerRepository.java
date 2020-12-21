package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.CompanyWorkflowTypeControllerEntity;
import com.pth.profile.entities.CompanyWorkflowTypeControllerEntity_;
import com.pth.profile.repositories.ICompanyWorkflowTypeControllerRepository;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class CompanyWorkflowTypeControllerRepository
        extends AEntityRdbmsHibernateRepository<CompanyWorkflowTypeControllerEntity>
        implements ICompanyWorkflowTypeControllerRepository {

    public CompanyWorkflowTypeControllerRepository(EntityManager entityManager) {
        super(CompanyWorkflowTypeControllerEntity.class, entityManager);
    }


    @Override
    @Transactional(readOnly = true)
    public List<CompanyWorkflowTypeControllerEntity> getByCompanyId(UUID companyId) {
        return queryCollection((cb, root) -> (cb.equal(root.get(CompanyWorkflowTypeControllerEntity_.companyId),
                                                       companyId)));
    }
}
