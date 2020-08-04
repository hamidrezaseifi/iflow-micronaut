package com.pth.common.edo;

import javax.validation.constraints.NotNull;

public class UserPasswordChangeRequestEdo {

  @NotNull(message = "Identity must not be null")
  private String identity;

  @NotNull(message = "Password must not be null")
  private String password;

  @NotNull(message = "CompanyIdentity must not be null")
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

  public String getCompanyIdentity() {

    return this.companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
  }

}
