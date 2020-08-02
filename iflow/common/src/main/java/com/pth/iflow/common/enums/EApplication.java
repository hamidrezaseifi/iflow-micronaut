package com.pth.iflow.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pth.iflow.common.exceptions.IFlowInvalidEnumValueException;

public enum EApplication implements IEnumNameValidator {
  IFLOW("iflow");

  private String enumName;

  EApplication(final String enumName) {

    this.enumName = enumName;
  }

  @Override
  @JsonValue
  public String getIdentity() {

    return this.enumName;
  }

  @JsonCreator
  public static EApplication valueFromName(final String nameString) {

    for (final EApplication item : values()) {
      if (item.enumName.equals(nameString)) {
        return item;
      }
    }

    throw new IFlowInvalidEnumValueException("Invalid value('" + nameString + "') for EApplication.");
  }

}
