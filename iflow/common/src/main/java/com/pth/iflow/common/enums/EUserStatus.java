package com.pth.iflow.common.enums;

public enum EUserStatus implements IEnumValueValidator {
  ACTIVE(1),
  DISABLED(10),
  DELETED(20);

  private final Integer id;

  private EUserStatus(final Integer id) {
    this.id = id;
  }

  @Override
  public Integer getValue() {
    return this.id;
  }

}
