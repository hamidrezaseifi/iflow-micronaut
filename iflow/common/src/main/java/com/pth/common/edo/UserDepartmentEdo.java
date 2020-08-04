package com.pth.common.edo;

import com.pth.common.edo.enums.EUserDepartmentMemberType;
import com.pth.common.edo.validation.AEnumValueValidator;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

public class UserDepartmentEdo {

  @NotNull(message = "DepartmentIdentity must not be null")
  private String departmentIdentity;

  @AEnumValueValidator(enumClazz = EUserDepartmentMemberType.class)
  @NotNull(message = "MemberType must not be null")
  private int memberType;

  public UserDepartmentEdo() {

  }

  public UserDepartmentEdo(final String departmentIdentity, final int memberType) {

    this.departmentIdentity = departmentIdentity;
    this.memberType = memberType;
  }

  public String getDepartmentIdentity() {

    return this.departmentIdentity;
  }

  public void setDepartmentIdentity(final String departmentIdentity) {

    this.departmentIdentity = departmentIdentity;
  }

  public int getMemberType() {

    return this.memberType;
  }

  public void setMemberType(final int memberType) {

    this.memberType = memberType;
  }

}
