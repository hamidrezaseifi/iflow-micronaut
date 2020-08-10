package com.pth.core.models;

import com.pth.core.entities.*;

import java.util.ArrayList;
import java.util.List;

public class ProfileResponse {

  private UserEntity user;
  private CompanyProfile companyProfile;
  private String sessionid;
  private final List<UserDashboardMenuEntity> userDashboardMenus = new ArrayList<>();

  public ProfileResponse() {

    this.user = null;
    this.companyProfile = null;
    this.sessionid = "";
  }

  public ProfileResponse(final UserEntity user, final CompanyProfile companyProfile, final String sessionid) {

    this.user = user;
    this.companyProfile = companyProfile;
    this.sessionid = sessionid;
  }

  public ProfileResponse(final UserEntity user, final CompanyEntity company, final List<DepartmentEntity> departments,
                         final List<UserGroupEntity> userGroups, final List<CompanyWorkflowTypeOcrSettingPresetEntity> ocrPresetSettings,
                         final List<UserDashboardMenuEntity> userDashboardMenus,
                         final String sessionid) {

    this.user = user;
    this.companyProfile = new CompanyProfile(company, departments, userGroups, ocrPresetSettings);
    this.sessionid = sessionid;
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

  public String getSessionid() {

    return this.sessionid;
  }

  public void setSessionid(final String sessionid) {

    this.sessionid = sessionid;
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
