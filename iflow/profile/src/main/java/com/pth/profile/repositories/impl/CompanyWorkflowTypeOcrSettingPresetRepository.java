package com.pth.profile.repositories.impl;

import com.pth.common.repositories.AEntityRdbmsHibernateRepository;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.entities.CompanyEntity_;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity_;
import com.pth.profile.repositories.ICompanyRepository;
import com.pth.profile.repositories.ICompanyWorkflowTypeOcrSettingPresetRepository;
import io.micronaut.data.annotation.Repository;
import io.micronaut.spring.tx.annotation.Transactional;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
@Repository
@Transactional
//@Requires(property = "micronaut.extensions.repositories.type", value = "RdbmsHibernate")
public class CompanyWorkflowTypeOcrSettingPresetRepository extends AEntityRdbmsHibernateRepository<CompanyWorkflowTypeOcrSettingPresetEntity>
        implements ICompanyWorkflowTypeOcrSettingPresetRepository {
    public CompanyWorkflowTypeOcrSettingPresetRepository(EntityManager entityManager) {
        super(CompanyWorkflowTypeOcrSettingPresetEntity.class, entityManager);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyWorkflowTypeOcrSettingPresetEntity> getByCompanyId(UUID companyId) {
        return queryCollection((cb, root) -> (cb.equal(root.get(CompanyWorkflowTypeOcrSettingPresetEntity_.companyId), companyId)));
    }
}
