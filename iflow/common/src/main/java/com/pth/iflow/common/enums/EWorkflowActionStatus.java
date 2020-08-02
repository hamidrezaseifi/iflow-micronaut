package com.pth.iflow.common.enums;

import com.pth.iflow.common.exceptions.IFlowInvalidEnumValueException;

public enum EWorkflowActionStatus implements IEnumValueValidator {
  INITIALIZE(0),
  OPEN(5),
  DONE(20),
  ERROR(30);

  private final int id;

  private EWorkflowActionStatus(final int id) {
    this.id = id;
  }

  public static EWorkflowActionStatus ofValue(final int value) {
    for (final EWorkflowActionStatus status : values()) {
      if (status.getValue().intValue() == value) {
        return status;
      }
    }

    throw new IFlowInvalidEnumValueException("Invalid value '" + value + "' for enum EWorkflowActionStatus");
  }

  @Override
  public Integer getValue() {
    return this.id;
  }

  public boolean isActive() {
    return (this.id < DONE.getValue());
  }

  public static boolean getIsActive(final Integer status) {
    return (status < DONE.getValue());
  }

}
