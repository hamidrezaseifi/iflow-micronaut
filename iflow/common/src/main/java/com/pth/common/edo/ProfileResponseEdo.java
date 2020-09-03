package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class ProfileResponseEdo {

  @NotNull
  private UserEdo user;

  @NotNull
  private CompanyProfileEdo companyProfile;

  @NotNull
  private String sessionid;

  @NotNull(message = "UserDashboardMenuList must not be null")
  private final List<UserDashboardMenuEdo> userDashboardMenus = new ArrayList<>();

  public ProfileResponseEdo() {

    this.user = null;
    this.companyProfile = null;
    this.sessionid = "";
  }

  public ProfileResponseEdo(final UserEdo user,
                            final CompanyProfileEdo company,
                            final String sessionid,
                            final List<UserDashboardMenuEdo> userDashboardMenus) {

    this.user = user;
    this.companyProfile = company;
    this.sessionid = sessionid;
    this.setUserDashboardMenus(userDashboardMenus);
  }

  public UserEdo getUser() {
    return user;
  }

  public void setUser(UserEdo user) {
    this.user = user;
  }

  public CompanyProfileEdo getCompanyProfile() {
    return companyProfile;
  }

  public void setCompanyProfile(CompanyProfileEdo companyProfile) {
    this.companyProfile = companyProfile;
  }

  public String getSessionid() {
    return sessionid;
  }

  public void setSessionid(String sessionid) {
    this.sessionid = sessionid;
  }

  public List<UserDashboardMenuEdo> getUserDashboardMenus() {
    return userDashboardMenus;
  }

  public void setUserDashboardMenus(final List<UserDashboardMenuEdo> userDashboardMenus) {

    this.userDashboardMenus.clear();
    if (userDashboardMenus != null) {
      this.userDashboardMenus.addAll(userDashboardMenus);
    }
  }

}
