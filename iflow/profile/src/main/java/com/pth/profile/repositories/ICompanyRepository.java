package com.pth.profile.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICompanyRepository extends IEntityRepository<CompanyEntity> {

    Optional<CompanyEntity> getByIdentity(String identity);
}
