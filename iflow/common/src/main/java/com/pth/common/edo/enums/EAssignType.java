package com.pth.common.edo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pth.common.exceptions.IFlowInvalidEnumValueException;

public enum EAssignType implements IEnumNameValidator {
  NONE("None"),
  USER("User"),
  DEPARTMENT("Department");

  private String enumName;

  EAssignType(final String enumName) {

    this.enumName = enumName;
  }

  @Override
  @JsonValue
  public String getIdentity() {

    return this.enumName;
  }

  @JsonCreator
  public static EAssignType valueFromName(final String nameString) {

    for (final EAssignType item : values()) {
      if (item.enumName.equals(nameString)) {
        return item;
      }
    }

    throw new IFlowInvalidEnumValueException("Invalid value('" + nameString + "') for EAssignType.");
  }

}
