package com.pth.iflow.common.enums;

import java.util.HashMap;
import java.util.Map;

import com.pth.iflow.common.exceptions.IFlowInvalidEnumValueException;

public enum EInvoiceType implements IEnumValueValidator {
  NO_TYPE(0),
  SUPPLIER(1), // Lieferant
  WORKER(2), // Handwerk
  PAYMENT(3); // Gutschrift

  private final int id;

  private EInvoiceType(final int id) {
    this.id = id;
  }

  public static EInvoiceType ofValue(final int value) {
    for (final EInvoiceType type : values()) {
      if (type.getValue().intValue() == value) {
        return type;
      }
    }

    throw new IFlowInvalidEnumValueException("Invalid value '" + value + "' for enum EInvoiceType");
  }

  @Override
  public Integer getValue() {
    return this.id;
  }

  public static Map<Integer, String> nameValueMap() {

    final Map<Integer, String> map = new HashMap<>();
    for (final EInvoiceType type : values()) {
      map.put(type.getValue(), type.toString());
    }

    return map;

  }

}
