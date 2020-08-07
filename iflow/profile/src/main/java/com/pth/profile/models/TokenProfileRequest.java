package com.pth.profile.models;

import com.pth.common.edo.enums.EApplication;
import com.pth.common.edo.validation.AEnumNameValidator;

import javax.validation.constraints.NotNull;

public class TokenProfileRequest {

  private String token;

  private EApplication app;

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
