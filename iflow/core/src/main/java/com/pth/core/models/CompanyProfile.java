package com.pth.core.models;

import com.pth.core.entities.CompanyEntity;
import com.pth.core.entities.CompanyWorkflowTypeOcrSettingPresetEntity;
import com.pth.core.entities.DepartmentEntity;
import com.pth.core.entities.UserGroupEntity;

import java.util.ArrayList;
import java.util.List;


public class CompanyProfile {

  private CompanyEntity company;
  private final List<DepartmentEntity> departments = new ArrayList<>();
  private final List<UserGroupEntity> userGroups = new ArrayList<>();
  private final List<CompanyWorkflowTypeOcrSettingPresetEntity> ocrPresetSettings = new ArrayList<>();

  // CompanyWorkflowtypeItemOcrSettingEntity
  public CompanyProfile() {

  }

  public CompanyProfile(final CompanyEntity company, final List<DepartmentEntity> departments,
      final List<UserGroupEntity> userGroups, final List<CompanyWorkflowTypeOcrSettingPresetEntity> ocrPresetSettings) {

    this.setDepartments(departments);
    this.setUserGroups(userGroups);
    this.setCompany(company);
    this.setOcrPresetSettings(ocrPresetSettings);
  }

  /**
   * @return the company
   */
  public CompanyEntity getCompany() {

    return this.company;
  }

  /**
   * @param company the company to set
   */
  public void setCompany(final CompanyEntity company) {

    this.company = company;
  }

  public List<DepartmentEntity> getDepartments() {

    return this.departments;
  }

  public void setDepartments(final List<DepartmentEntity> departments) {

    this.departments.clear();
    if (departments != null) {
      this.departments.addAll(departments);
    }
  }

  public List<UserGroupEntity> getUserGroups() {

    return this.userGroups;
  }

  public void setUserGroups(final List<UserGroupEntity> users) {

    this.userGroups.clear();
    if (users != null) {
      this.userGroups.addAll(users);
    }
  }

  public List<CompanyWorkflowTypeOcrSettingPresetEntity> getOcrPresetSettings() {

    return ocrPresetSettings;
  }

  public void setOcrPresetSettings(final List<CompanyWorkflowTypeOcrSettingPresetEntity> ocrPresetSettings) {

    this.ocrPresetSettings.clear();
    if (ocrPresetSettings != null) {
      this.ocrPresetSettings.addAll(ocrPresetSettings);
    }
  }

  public Integer getVersion() {

    return null;
  }

  public Long getId() {

    return null;
  }

  public void setVersion(final Integer version) {

  }

}
