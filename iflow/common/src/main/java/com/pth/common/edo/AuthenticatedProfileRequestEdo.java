package com.pth.common.edo;

import com.pth.common.edo.enums.EApplication;
import com.pth.common.edo.validation.AEnumNameValidator;

import javax.validation.constraints.NotNull;

public class AuthenticatedProfileRequestEdo {

  @NotNull
  private String userIdentity;

  @NotNull
  private String token;

  @AEnumNameValidator(enumClazz = EApplication.class)
  @NotNull(message = "AppId must not be null")
  private String appId;

  public String getUserIdentity() {

    return this.userIdentity;
  }

  public void setUserIdentity(final String userIdentity) {

    this.userIdentity = userIdentity;
  }

  public String getToken() {

    return this.token;
  }

  public void setToken(final String token) {

    this.token = token;
  }

  public String getAppId() {

    return this.appId;
  }

  public void setAppId(final String appId) {

    this.appId = appId;
  }

}
