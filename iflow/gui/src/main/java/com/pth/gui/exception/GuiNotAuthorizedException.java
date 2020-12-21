package com.pth.gui.exception;

import com.pth.common.edo.enums.EModule;
import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.common.exceptions.IFlowCustomizedException;

import javax.validation.ValidationException;

public class GuiNotAuthorizedException extends ValidationException {

  private final String token;

  public GuiNotAuthorizedException(final String message, String token) {
    super(message);
    this.token = token;
  }

  public String getToken() {
    return token;
  }
}
