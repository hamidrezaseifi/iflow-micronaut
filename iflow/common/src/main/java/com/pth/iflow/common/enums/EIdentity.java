package com.pth.iflow.common.enums;

import org.apache.commons.lang3.StringUtils;

import com.pth.iflow.common.exceptions.IFlowInvalidEnumValueException;

public enum EIdentity implements IEnumNameValidator {
  NOT_SET("notset");

  private String enumName;

  EIdentity(final String enumName) {
    this.enumName = enumName;
  }

  @Override
  public String getIdentity() {
    return this.enumName;
  }

  public static EIdentity valueFromName(final String nameString) {
    for (final EIdentity item : values()) {
      if (item.enumName.equals(nameString)) {
        return item;
      }
    }

    throw new IFlowInvalidEnumValueException("Invalid value('" + nameString + "') for EWorkflowIdentity.");
  }

  public static boolean isNotSet(final String identity) {
    if (StringUtils.isEmpty(identity)) {
      return true;
    }

    return NOT_SET.getIdentity().equals(identity);
  }

}
