package com.pth.profile.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.profile.entities.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICompanyWorkflowTypeOcrSettingPresetRepository extends IEntityRepository<CompanyWorkflowTypeOcrSettingPresetEntity> {
    List<CompanyWorkflowTypeOcrSettingPresetEntity> getByCompanyId(UUID companyId);
}
