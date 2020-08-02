package com.pth.iflow.common.enums;

import com.pth.iflow.common.exceptions.IFlowInvalidEnumValueException;

public enum EWorkflowMessageStatus implements IEnumValueValidator {
  NO_STATUS(0),
  OFFERING(1),
  ASSIGNED(2),
  CLOSED(6),
  DELETED(10);

  private final int id;

  private EWorkflowMessageStatus(final int id) {
    this.id = id;
  }

  public static EWorkflowMessageStatus ofValue(final int value) {
    for (final EWorkflowMessageStatus status : values()) {
      if (status.getValue().intValue() == value) {
        return status;
      }
    }

    throw new IFlowInvalidEnumValueException("Invalid value '" + value + "' for enum EWorkflowOfferStatus");
  }

  @Override
  public Integer getValue() {
    return this.id;
  }

}
