package com.pth.profile.services.data;

import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.profile.entities.CompanyEntity;
import com.pth.profile.entities.CompanyWorkflowTypeOcrSettingPresetEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ICompanyService {

    Optional<CompanyEntity> create(CompanyEntity model);

    Optional<CompanyEntity> save(CompanyEntity model);

    Optional<CompanyEntity> getById(final UUID id);

    List<CompanyWorkflowTypeOcrSettingPresetEntity> getCompanyWorkflowtypeItemOcrSettingList(UUID companyId);

    List<CompanyWorkflowTypeOcrSettingPresetEntity> getCompanyWorkflowtypeItemOcrSettingListByCompanyIdentity(UUID id);

    Optional<CompanyWorkflowTypeOcrSettingPresetEntity> saveCompanyWorkflowtypeItemOcrSetting(
          final CompanyWorkflowTypeOcrSettingPresetEntity preset);

    void deleteCompanyWorkflowtypeItemOcrSetting(final CompanyWorkflowTypeOcrSettingPresetEntity preset);
}
