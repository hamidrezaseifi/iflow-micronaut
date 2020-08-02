package com.pth.iflow.profile.model;

import java.util.ArrayList;
import java.util.List;

public class ProfileResponse {

  private User user;
  private CompanyProfile companyProfile;
  private String sessionid;
  private final List<UserDashboardMenu> userDashboardMenus = new ArrayList<>();

  public ProfileResponse() {

    this.user = null;
    this.companyProfile = null;
    this.sessionid = "";
  }

  public ProfileResponse(final User user, final CompanyProfile companyProfile, final String sessionid,
      final List<UserDashboardMenu> userDashboardMenus) {

    this.user = user;
    this.companyProfile = companyProfile;
    this.sessionid = sessionid;
    this.setUserDashboardMenus(userDashboardMenus);
  }

  public User getUser() {

    return this.user;
  }

  public void setUser(final User user) {

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

  public List<UserDashboardMenu> getUserDashboardMenus() {

    return this.userDashboardMenus;
  }

  public void setUserDashboardMenus(final List<UserDashboardMenu> userDashboardMenus) {

    this.userDashboardMenus.clear();
    if (userDashboardMenus != null) {
      this.userDashboardMenus.addAll(userDashboardMenus);
    }
  }
}
