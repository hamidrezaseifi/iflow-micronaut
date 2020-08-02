package com.pth.iflow.common.enums;

import com.pth.iflow.common.exceptions.IFlowInvalidEnumValueException;

public enum EWorkflowTypeAssignType implements IEnumValueValidator {
  NO_TYPE(0),
  MANUAL(1),
  OFFER(2);

  private final int id;

  private EWorkflowTypeAssignType(final int id) {
    this.id = id;
  }

  public static EWorkflowTypeAssignType ofValue(final int value) {
    for (final EWorkflowTypeAssignType status : values()) {
      if (status.getValue().intValue() == value) {
        return status;
      }
    }

    throw new IFlowInvalidEnumValueException("Invalid value '" + value + "' for enum EWorkflowTypeAssignType");
  }

  @Override
  public Integer getValue() {
    return this.id;
  }

}
