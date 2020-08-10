package com.pth.common.edo.enums;

public enum EUserStatus implements IEnumValueValidator {
  ACTIVE(1),
  DISABLED(10),
  LOCKED(20),
  DELETED(30);

  private final Integer id;

  private EUserStatus(final Integer id) {
    this.id = id;
  }

  @Override
  public Integer getValue() {
    return this.id;
  }

}
