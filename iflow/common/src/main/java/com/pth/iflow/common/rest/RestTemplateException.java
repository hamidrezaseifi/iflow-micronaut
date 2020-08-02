package com.pth.iflow.common.rest;

import javax.validation.ValidationException;

import com.pth.iflow.common.enums.EModule;
import com.pth.iflow.common.exceptions.EIFlowErrorType;

public class RestTemplateException extends ValidationException {

  private static final long     serialVersionUID = 1L;
  private final String          detailes;
  private final String          moduleName;
  private final EIFlowErrorType errorType;

  public RestTemplateException(final String message) {
    super(message);
    this.detailes = "";
    this.moduleName = EModule.GUI.getModuleName();
    this.errorType = EIFlowErrorType.RUNTIME_UNKNOWN;
  }

  public RestTemplateException(final String message, final StackTraceElement[] stackTraceElement) {
    super(message);
    this.detailes = stackListToString(stackTraceElement);
    this.moduleName = EModule.GUI.getModuleName();
    this.errorType = EIFlowErrorType.RUNTIME_UNKNOWN;
  }

  public RestTemplateException(final String message, final String moduleName, final StackTraceElement[] stackTraceElement) {
    super(message);
    this.detailes = stackListToString(stackTraceElement);
    this.moduleName = moduleName;
    this.errorType = EIFlowErrorType.RUNTIME_UNKNOWN;
  }

  public RestTemplateException(final String message, final String moduleName, final EIFlowErrorType errorType) {
    super(message);
    this.detailes = "";
    this.moduleName = moduleName;
    this.errorType = errorType;
  }

  public RestTemplateException(final String message, final String moduleName, final EIFlowErrorType errorType,
      final StackTraceElement[] stackTraceElement) {
    super(message);
    this.detailes = stackListToString(stackTraceElement);
    this.moduleName = moduleName;
    this.errorType = errorType;
  }

  public String getDetailes() {
    return this.detailes;
  }

  public String getModuleName() {
    return this.moduleName;
  }

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
