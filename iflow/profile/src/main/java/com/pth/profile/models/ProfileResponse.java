package com.pth.profile.models;

import com.pth.profile.entities.*;

import java.util.ArrayList;
import java.util.List;


public class ProfileResponse {

  private UserEntity user;
  private CompanyProfile companyProfile;
  private String token;
  private final List<UserDashboardMenuEntity> userDashboardMenus = new ArrayList<>();

  public ProfileResponse() {

    this.user = null;
    this.companyProfile = null;
    this.token = "";
  }

  public ProfileResponse(final UserEntity user, final CompanyProfile companyProfile, final String token) {

    this.user = user;
    this.companyProfile = companyProfile;
    this.token = token;
  }

  public ProfileResponse(final UserEntity user,
                         final CompanyEntity company,
                         final List<DepartmentEntity> departments,
                         final List<UserGroupEntity> userGroups,
                         final List<CompanyWorkflowTypeOcrSettingPresetEntity> ocrPresetSettings,
                         final List<UserDashboardMenuEntity> userDashboardMenus,
                         final List<CompanyWorkflowTypeControllerEntity> workflowTypeControllers,
                         final String token) {

    this.user = user;
    this.companyProfile = new CompanyProfile(company,
                                             departments,
                                             userGroups,
                                             ocrPresetSettings,
                                             workflowTypeControllers);
    this.token = token;
    setUserDashboardMenus(userDashboardMenus);
  }

  public UserEntity getUser() {

    return this.user;
  }

  public void setUser(final UserEntity user) {

    this.user = user;
  }

  public CompanyProfile getCompanyProfile() {

    return this.companyProfile;
  }

  public void setCompanyProfile(final CompanyProfile companyProfile) {

    this.companyProfile = companyProfile;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public List<UserDashboardMenuEntity> getUserDashboardMenus() {

    return userDashboardMenus;
  }

  public void setUserDashboardMenus(final List<UserDashboardMenuEntity> userDashboardMenus) {

    this.userDashboardMenus.clear();
    if (userDashboardMenus != null) {
      this.userDashboardMenus.addAll(userDashboardMenus);
    }
  }

}
