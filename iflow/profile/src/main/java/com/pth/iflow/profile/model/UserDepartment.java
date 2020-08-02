package com.pth.iflow.profile.model;

import com.pth.iflow.common.enums.EUserDepartmentMemberType;

public class UserDepartment {

  private String departmentIdentity;

  private EUserDepartmentMemberType memberType;

  public UserDepartment() {

  }

  public UserDepartment(final String departmentIdentity, final EUserDepartmentMemberType memberType) {

    this.departmentIdentity = departmentIdentity;
    this.memberType = memberType;
  }

  public UserDepartment(final String departmentIdentity, final int memberType) {

    this.departmentIdentity = departmentIdentity;
    this.memberType = EUserDepartmentMemberType.ofValue(memberType);
  }

  public String getDepartmentIdentity() {

    return departmentIdentity;
  }

  public void setDepartmentIdentity(final String departmentIdentity) {

    this.departmentIdentity = departmentIdentity;
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
