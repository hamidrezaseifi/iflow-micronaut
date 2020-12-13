package com.pth.gui.services;

import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.models.Company;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPreset;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPresetItem;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public interface ICompanyHandler {

  Optional<Company> read(final UUID companyId, final String token);

  Optional<Company> save(final Company company, final String token);

  List<CompanyWorkflowtypeItemOcrSettingPreset>
    readCompanyWorkflowtypeItemOcrSettings(UUID companyId, final String token) ;

  Optional<CompanyWorkflowtypeItemOcrSettingPreset>
    saveCompanyWorkflowtypeItemOcrSetting(CompanyWorkflowtypeItemOcrSettingPreset preset,
                                          final String token);

  List<String> readWorkflowtypeItems(final EWorkflowType workflowType);

  Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem> readPresetAllItems(final String presetName,
                                                                              final UUID CompanyId,
                                                                              EWorkflowType workflowType,
                                                                              final String token);

  Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem>
    readPresetAllItemsFromSession(final String presetName,
                                  List<CompanyWorkflowtypeItemOcrSettingPreset> presetList,
                                  EWorkflowType workflowType);

  Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem>
    extractMappedCompanyWorkflowtypeItemsFromOcrPreset(final CompanyWorkflowtypeItemOcrSettingPreset preset,
                                                       EWorkflowType workflowType);

  void deleteCompanyWorkflowtypeItemOcrSetting(CompanyWorkflowtypeItemOcrSettingPreset preset, String token);

}
