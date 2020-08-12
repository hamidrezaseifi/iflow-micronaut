package com.pth.profile.services.data;

import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ICompanyService {

    Optional<CompanyEntity> save(CompanyEntity model);

    Optional<CompanyEntity> getByIdentity(final String identity);

    List<CompanyWorkflowTypeOcrSettingPresetEntity> readCompanyWorkflowtypeItemOcrSettings(UUID companyId);

    List<CompanyWorkflowTypeOcrSettingPresetEntity> readCompanyWorkflowtypeItemOcrSettingsByCompanyIdentity(String identity);

    Optional<CompanyWorkflowTypeOcrSettingPresetEntity> saveCompanyWorkflowtypeItemOcrSetting(
          final CompanyWorkflowTypeOcrSettingPresetEntity preset);

    void deleteCompanyWorkflowtypeItemOcrSetting(final CompanyWorkflowTypeOcrSettingPresetEntity preset);
}
