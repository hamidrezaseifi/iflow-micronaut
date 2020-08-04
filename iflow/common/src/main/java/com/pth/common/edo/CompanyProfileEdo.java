package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

public class CompanyProfileEdo {

  @NotNull
  private CompanyEdo company;

  @NotNull
  private final List<DepartmentEdo> departments = new ArrayList<>();

  @NotNull
  private final List<UserGroupEdo> userGroups = new ArrayList<>();

  @NotNull
  private final List<CompanyWorkflowTypeControllerEdo> workflowTypeControllers = new ArrayList<>();

  private final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> ocrPresets = new ArrayList<>();

  public CompanyProfileEdo() {

  }

  public CompanyProfileEdo(final CompanyEdo company, final List<DepartmentEdo> departments, final List<UserGroupEdo> userGroups,
      final List<CompanyWorkflowTypeControllerEdo> workflowTypeControllers,
      final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> workflowtypeItemOcrSettingPresets) {

    this.setDepartments(departments);
    this.setUserGroups(userGroups);
    this.setCompany(company);
    this.setWorkflowTypeControllers(workflowTypeControllers);
    this.setOcrPresets(workflowtypeItemOcrSettingPresets);

  }

  /**
   * @return the company
   */
  public CompanyEdo getCompany() {

    return this.company;
  }

  /**
   * @param company the company to set
   */
  public void setCompany(final CompanyEdo company) {

    this.company = company;
  }

  public List<DepartmentEdo> getDepartments() {

    return this.departments;
  }

  @JsonSetter
  public void setDepartments(final List<DepartmentEdo> departments) {

    this.departments.clear();
    if (departments != null) {
      this.departments.addAll(departments);
    }
  }

  public List<UserGroupEdo> getUserGroups() {

    return this.userGroups;
  }

  @JsonSetter
  public void setUserGroups(final List<UserGroupEdo> users) {

    this.userGroups.clear();
    if (users != null) {
      this.userGroups.addAll(users);
    }
  }

  public List<CompanyWorkflowTypeControllerEdo> getWorkflowTypeControllers() {

    return this.workflowTypeControllers;
  }

  @JsonSetter
  public void setWorkflowTypeControllers(final List<CompanyWorkflowTypeControllerEdo> workflowTypeController) {

    this.workflowTypeControllers.clear();
    if (workflowTypeController != null) {
      this.workflowTypeControllers.addAll(workflowTypeController);
    }
  }

  public List<CompanyWorkflowtypeItemOcrSettingPresetEdo> getOcrPresets() {

    return this.ocrPresets;
  }

  @JsonSetter(contentNulls = Nulls.AS_EMPTY)
  public void
      setOcrPresets(final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> ocrPresets) {

    this.ocrPresets.clear();
    if (ocrPresets != null) {
      this.ocrPresets.addAll(ocrPresets);
    }

  }

}
