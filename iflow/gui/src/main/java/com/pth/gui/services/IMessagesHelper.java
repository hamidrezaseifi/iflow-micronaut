package com.pth.gui.services;


import com.pth.common.exceptions.EIFlowErrorType;

public interface IMessagesHelper {

  String get(String code);

  String get(String code, Object... args);

  String getErrorMessage(EIFlowErrorType errorType);

  String getErrorMessage(EIFlowErrorType errorType, Object... args);

}
