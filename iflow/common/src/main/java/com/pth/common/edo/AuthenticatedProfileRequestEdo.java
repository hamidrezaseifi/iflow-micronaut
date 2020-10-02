package com.pth.common.edo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pth.common.edo.enums.EApplication;
import com.pth.common.edo.validation.AEnumNameValidator;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
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
