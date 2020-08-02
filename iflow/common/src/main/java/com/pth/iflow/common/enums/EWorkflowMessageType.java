package com.pth.iflow.common.enums;

import com.pth.iflow.common.exceptions.IFlowInvalidEnumValueException;

public enum EWorkflowMessageType implements IEnumValueValidator {
  NO_TYPE(0),
  OFFERING_WORKFLOW(1),
  ASSIGNED_WORKFLOW(5);

  private final int id;

  private EWorkflowMessageType(final int id) {

    this.id = id;
  }

  public static EWorkflowMessageType ofValue(final int value) {

    for (final EWorkflowMessageType status : values()) {
      if (status.getValue().intValue() == value) {
        return status;
      }
    }

    throw new IFlowInvalidEnumValueException("Invalid value '" + value + "' for enum EWorkflowMessageType");
  }

  @Override
  public Integer getValue() {

    return this.id;
  }
}
