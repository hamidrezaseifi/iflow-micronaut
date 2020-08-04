package com.pth.common.exceptions;


import com.pth.common.edo.enums.IEnumValueValidator;

public enum EIFlowErrorType implements IEnumValueValidator {
  NONE(0, ""),
  MESSAGE_CONVERSION_FAILURE(11, "The message sent could not be converted"),
  INVALID_USERNAMEPASSWORD(10, ""),
  USER_NOTFOUND(20, ""),
  USERPROFILE_NOTFOUND(25, ""),
  COMPANY_NOTFOUND(30, ""),
  INVALID_COMPANY(31, ""),
  INVALID_TOKEN(40, ""),
  NO_SESSION_FOUND(50, ""),
  UNKNOWN_WORKFLOW_STATUS(60, ""),
  UNKNOWN_WORKFLOW_TYPE_STEP(61, ""),
  INVALID_WORKFLOW_STEP(62, ""),
  UNKNOWN_WORKFLOW_ASSIGN(63, ""),
  INVALID_WORKFLOW_DETAIL(65, ""),
  INVALID_WORKFLOW_STATUS(70, ""),
  UNKNOWN_WORKFLOW_STRATEGY(90, ""),
  NO_WORKFLOW_ASSIGN_CREATE_STRATEGY(91, ""),
  INVALID_WORKFLOW_ASSIGN_LIST(100, ""),

  OPTIMISTICLOCK_FAILURE(110, ""),
  DAO_STORAGE_FAILURE(120, ""),

  GUI_INVALID_SESSION(210, ""),

  PROFILE_MANAGER_FOR_DEPARTMENT_EXISTS(310, ""),
  PROFILE_DEPUTY_FOR_DEPARTMENT_EXISTS(311, ""),

  SERVICE_NOT_IMPLEMENTED(820, ""),

  RUNTIME_UNKNOWN(910, ""),
  SERVICE_NOT_FOUND(920, ""),

  UNKNOWN(1000, "");

  private final Integer errorCode;
  private final String message;

  private EIFlowErrorType(final Integer errorCode, final String message) {

    this.errorCode = errorCode;
    this.message = message;
  }

  @Override
  public Integer getValue() {

    return this.errorCode;
  }

  public String getName() {

    return this.message;
  }

}
