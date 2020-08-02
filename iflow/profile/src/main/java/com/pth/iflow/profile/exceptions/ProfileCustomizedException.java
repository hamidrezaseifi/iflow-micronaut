package com.pth.iflow.profile.exceptions;

import javax.validation.ValidationException;

import com.pth.iflow.common.exceptions.EIFlowErrorType;

public class ProfileCustomizedException extends ValidationException {

  private static final long     serialVersionUID = 1L;
  private final String          detailes;
  private final String          moduleName;
  private final EIFlowErrorType errorType;

  public ProfileCustomizedException(final String message, final String detailes, final String moduleName) {
    super(message);
    this.detailes = detailes;
    this.moduleName = moduleName;
    this.errorType = EIFlowErrorType.NONE;
  }

  public ProfileCustomizedException(final String message, final String detailes, final String moduleName,
      final EIFlowErrorType errorType) {
    super(message);
    this.detailes = detailes;
    this.moduleName = moduleName;
    this.errorType = errorType;
  }

  public String getDetailes() {
    return this.detailes;
  }

  public String getModuleName() {
    return this.moduleName;
  }

  /**
   * @return the errorType
   */
  public EIFlowErrorType getErrorType() {
    return this.errorType;
  }

}
