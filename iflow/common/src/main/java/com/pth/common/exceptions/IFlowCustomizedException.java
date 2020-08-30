package com.pth.common.exceptions;

import javax.validation.ValidationException;


public class IFlowCustomizedException extends ValidationException {

  private static final long     serialVersionUID = 1L;
  private final String          detailes;
  private final String          moduleName;
  private final EIFlowErrorType errorType;

  public IFlowCustomizedException(final String message, final String detailes, final String moduleName) {
    super(message);
    this.detailes = detailes;
    this.moduleName = moduleName;
    this.errorType = EIFlowErrorType.NONE;
  }

  public IFlowCustomizedException(final String message, final String detailes, final String moduleName,
                                  final EIFlowErrorType errorType) {
    super(message);
    this.detailes = detailes;
    this.moduleName = moduleName;
    this.errorType = errorType;
  }

  public IFlowCustomizedException(final String message, final String moduleName,
                                  final EIFlowErrorType errorType) {
    super(message);
    this.detailes = "";
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

  public static String stackListToString(final StackTraceElement[] list) {
    String res = "";
    for (final StackTraceElement el : list) {
      res += el.toString() + "\n";
    }

    return res;
  }
}
