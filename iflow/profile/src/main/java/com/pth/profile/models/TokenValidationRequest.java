package com.pth.profile.models;

import com.pth.common.edo.enums.EApplication;
import com.pth.common.edo.validation.AEnumNameValidator;
import io.micronaut.security.authentication.Authentication;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class TokenValidationRequest {

  private Authentication authentication;

  private String token;

  private EApplication app;

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

  public EApplication getApp() {
    return app;
  }

  public void setApp(EApplication app) {
    this.app = app;
  }

}
