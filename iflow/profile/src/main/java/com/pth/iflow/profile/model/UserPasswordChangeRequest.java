package com.pth.iflow.profile.model;

public class UserPasswordChangeRequest {

  private String identity;

  private String password;

  private String token;

  private String companyIdentity;

  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public String getPassword() {

    return this.password;
  }

  public void setPassword(final String password) {

    this.password = password;
  }

  public String getToken() {

    return this.token;
  }

  public void setToken(final String token) {

    this.token = token;
  }

  public String getCompanyIdentity() {

    return this.companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
  }

}
