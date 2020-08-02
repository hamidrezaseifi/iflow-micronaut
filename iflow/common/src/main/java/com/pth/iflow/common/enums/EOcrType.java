package com.pth.iflow.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.pth.iflow.common.exceptions.IFlowInvalidEnumValueException;

public enum EOcrType implements IEnumValueValidator {
  NO_OCR(0),
  SEARCH_WORD(1),
  FIXED_POSITION(2);

  private final Integer id;

  private EOcrType(final Integer id) {

    this.id = id;
  }

  @Override
  public Integer getValue() {

    return this.id;
  }

  @JsonCreator
  public static EOcrType valueFromInteger(final Integer id) {

    for (final EOcrType item : values()) {
      if (item.getValue() == id) {
        return item;
      }
    }

    throw new IFlowInvalidEnumValueException("Invalid value('" + id + "') for EOcrType.");
  }

}
