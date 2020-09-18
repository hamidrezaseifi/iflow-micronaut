package com.pth.profile.exception;

import com.pth.common.edo.enums.EModule;
import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.common.exceptions.IFlowCustomizedException;

import javax.validation.ValidationException;
import java.util.UUID;

public class UserNotFoundException extends ValidationException {

  private final String userId;

  public UserNotFoundException(String userId) {
    super(String.format("User with id %s not found!", userId.toString()));
    this.userId = userId;
  }

}
