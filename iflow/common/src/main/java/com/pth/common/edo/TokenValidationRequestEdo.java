package com.pth.common.edo;

import com.pth.common.edo.enums.EApplication;
import com.pth.common.edo.validation.AEnumNameValidator;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.security.authentication.Authentication;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Introspected
public class TokenValidationRequestEdo {

  @NotNull(message = "Authentication must not be null")
  private Authentication authentication;

  @NotNull(message = "Token must not be null")
  private String token;

  @AEnumNameValidator(enumClazz = EApplication.class)
  @NotNull(message = "AppId must not be null")
  private String appId;

  public Authentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
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
