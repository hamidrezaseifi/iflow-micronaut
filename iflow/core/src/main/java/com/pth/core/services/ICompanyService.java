package com.pth.core.services;

import com.pth.common.edo.CompanyEdo;
import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.core.entities.CompanyEntity;
import com.pth.core.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.core.services.base.ICoreModelEdoMapperService;

import java.util.List;


public interface ICompanyService extends ICoreModelEdoMapperService<CompanyEntity, CompanyEdo> {

  CompanyEntity save(CompanyEntity model);

  CompanyEntity getByIdentity(final String identifyId);

  List<CompanyWorkflowTypeOcrSettingPresetEntity> readCompanyWorkflowtypeItemOcrSettings(Long id);

  List<CompanyWorkflowTypeOcrSettingPresetEntity> readCompanyWorkflowtypeItemOcrSettingsByCompanyIdentity(String identity);

  CompanyWorkflowTypeOcrSettingPresetEntity saveCompanyWorkflowtypeItemOcrSetting(
          final CompanyWorkflowTypeOcrSettingPresetEntity preset);

  void deleteCompanyWorkflowtypeItemOcrSetting(final CompanyWorkflowTypeOcrSettingPresetEntity preset);

  CompanyWorkflowTypeOcrSettingPresetEntity
      fromCompanyWorkflowtypeItemOcrSettingPresetEdo(final CompanyWorkflowtypeItemOcrSettingPresetEdo edo);

  List<CompanyWorkflowTypeOcrSettingPresetEntity>
      fromCompanyWorkflowtypeItemOcrSettingPresetEdoList(List<CompanyWorkflowtypeItemOcrSettingPresetEdo> edoList);

  List<CompanyWorkflowtypeItemOcrSettingPresetEdo>
      toCompanyWorkflowtypeItemOcrSettingPresetEdoList(List<CompanyWorkflowTypeOcrSettingPresetEntity> modelList);

  CompanyWorkflowtypeItemOcrSettingPresetEdo
      toCompanyWorkflowtypeItemOcrSettingPresetEdo(CompanyWorkflowTypeOcrSettingPresetEntity modelSaved);
}
