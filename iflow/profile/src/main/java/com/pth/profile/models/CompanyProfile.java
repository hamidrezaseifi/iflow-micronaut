package com.pth.profile.models;

import com.pth.common.edo.CompanyWorkflowtypeItemOcrSettingPresetEdo;
import com.pth.profile.entities.*;

import java.util.ArrayList;
import java.util.List;


public class CompanyProfile {

  private CompanyEntity company;
  private final List<DepartmentEntity> departments = new ArrayList<>();
  private final List<UserGroupEntity> userGroups = new ArrayList<>();
  private final List<CompanyWorkflowTypeOcrSettingPresetEntity> ocrPresetSettings = new ArrayList<>();
  private final List<CompanyWorkflowTypeControllerEntity> workflowTypeControllers = new ArrayList<>();

  public CompanyProfile() {

  }

  public CompanyProfile(final CompanyEntity company,
                        final List<DepartmentEntity> departments,
                        final List<UserGroupEntity> userGroups,
                        final List<CompanyWorkflowTypeOcrSettingPresetEntity> ocrPresetSettings,
                        final List<CompanyWorkflowTypeControllerEntity> workflowTypeControllers) {

    this.setDepartments(departments);
    this.setUserGroups(userGroups);
    this.setCompany(company);
    this.setOcrPresetSettings(ocrPresetSettings);
    this.setWorkflowTypeControllers(workflowTypeControllers);
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

  public List<CompanyWorkflowTypeControllerEntity> getWorkflowTypeControllers() {
    return workflowTypeControllers;
  }

  public void setWorkflowTypeControllers(List<CompanyWorkflowTypeControllerEntity> workflowTypeControllers) {
    this.workflowTypeControllers.clear();
    if (workflowTypeControllers != null) {
      this.workflowTypeControllers.addAll(workflowTypeControllers);
    }
  }
}
