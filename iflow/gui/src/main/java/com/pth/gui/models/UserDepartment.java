package com.pth.gui.models;

import com.pth.common.edo.enums.EUserDepartmentMemberType;

import java.util.UUID;

public class UserDepartment {

  private UUID departmentId;

  private EUserDepartmentMemberType memberType;

  public UserDepartment() {
    super();
  }

  public UserDepartment(final UUID departmentId, final EUserDepartmentMemberType memberType) {

    this.departmentId = departmentId;
    this.memberType = memberType;
  }

  public UserDepartment(final UUID departmentId, final int memberType) {

    this.departmentId = departmentId;
    this.memberType = EUserDepartmentMemberType.ofValue(memberType);
  }

  public UUID getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(UUID departmentId) {
    this.departmentId = departmentId;
  }

  public EUserDepartmentMemberType getMemberType() {

    return memberType;
  }

  public void setMemberType(final EUserDepartmentMemberType memberType) {

    this.memberType = memberType;
  }

  public void setMemberType(final int memberType) {

    this.memberType = EUserDepartmentMemberType.ofValue(memberType);
  }

  // EUserDepartmentAssignType
}
