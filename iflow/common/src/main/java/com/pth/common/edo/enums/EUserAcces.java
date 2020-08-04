package com.pth.common.edo.enums;

public enum EUserAcces {
  NONE(0, "user-access-none"),
  ADMIN(1, "user-access-admin"),
  AGENT(5, "user-access-agent"),
  VIEW(10, "user-access-view");

  private final int permission;
  private final String labelId;

  EUserAcces(final int access, final String labelId) {

    this.permission = access;
    this.labelId = labelId;
  }

  public int getPermission() {

    return this.permission;
  }

  public String getLabelId() {

    return this.labelId;
  }

  public static EUserAcces fromPermission(final int permission) {

    for (final EUserAcces item : values()) {
      if (item.getPermission() == permission) {
        return item;
      }
    }

    return null;
  }
}
