package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSetter;

public class CompanyWorkflowtypeItemOcrSettingPresetListEdo {

  @NotNull
  private final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> companyWorkflowtypeItemOcrSettings = new ArrayList<>();

  public CompanyWorkflowtypeItemOcrSettingPresetListEdo() {

  }

  public CompanyWorkflowtypeItemOcrSettingPresetListEdo(
      final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> companyWorkflowtypeItemOcrSettings) {

    this.setCompanyWorkflowtypeItemOcrSettings(companyWorkflowtypeItemOcrSettings);
  }

  public List<CompanyWorkflowtypeItemOcrSettingPresetEdo> getCompanyWorkflowtypeItemOcrSettings() {

    return this.companyWorkflowtypeItemOcrSettings;
  }

  @JsonSetter
  public void
      setCompanyWorkflowtypeItemOcrSettings(final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> companyWorkflowtypeItemOcrSettings) {

    this.companyWorkflowtypeItemOcrSettings.clear();
    if (companyWorkflowtypeItemOcrSettings != null) {
      this.companyWorkflowtypeItemOcrSettings.addAll(companyWorkflowtypeItemOcrSettings);
    }
  }

}
