package com.pth.gui.exception;

import com.pth.common.edo.enums.EModule;
import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.common.exceptions.IFlowCustomizedException;

import javax.validation.ValidationException;

public class GuiCustomizedException extends ValidationException {

  private static final long     serialVersionUID = 1L;
  private final String          detailes;
  private final String          moduleName;
  private final EIFlowErrorType errorType;

  public GuiCustomizedException(final String message) {
    super(message);
    this.detailes = "";
    this.moduleName = EModule.GUI.getModuleName();
    this.errorType = EIFlowErrorType.RUNTIME_UNKNOWN;
  }

  public GuiCustomizedException(final String message, final StackTraceElement[] stackTraceElement) {
    super(message);
    this.detailes = IFlowCustomizedException.stackListToString(stackTraceElement);
    this.moduleName = EModule.GUI.getModuleName();
    this.errorType = EIFlowErrorType.RUNTIME_UNKNOWN;
  }

  public GuiCustomizedException(final String message, final String moduleName, final StackTraceElement[] stackTraceElement) {
    super(message);
    this.detailes = IFlowCustomizedException.stackListToString(stackTraceElement);
    this.moduleName = moduleName;
    this.errorType = EIFlowErrorType.RUNTIME_UNKNOWN;
  }

  public GuiCustomizedException(final String message, final String moduleName, final EIFlowErrorType errorType) {
    super(message);
    this.detailes = "";
    this.moduleName = moduleName;
    this.errorType = errorType;
  }

  public GuiCustomizedException(final String message, final String moduleName, final EIFlowErrorType errorType,
      final StackTraceElement[] stackTraceElement) {
    super(message);
    this.detailes = IFlowCustomizedException.stackListToString(stackTraceElement);
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

}
