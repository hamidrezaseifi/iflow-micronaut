package com.pth.workflow.models;

public class ProfileResponse {

  private User user;
  private CompanyProfile companyProfile;
  private String sessionid;

  public ProfileResponse() {

    this.user = null;
    this.companyProfile = null;
    this.sessionid = "";
  }

  public ProfileResponse(final User user, final CompanyProfile companyProfile, final String sessionid) {

    this.user = user;
    this.companyProfile = companyProfile;
    this.sessionid = sessionid;
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

}
