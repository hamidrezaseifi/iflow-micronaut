package com.pth.common.edo;

import com.pth.common.edo.enums.EUserDepartmentMemberType;
import com.pth.common.edo.validation.AEnumValueValidator;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import java.util.UUID;

@Introspected
public class UserDepartmentEdo {

  @NotNull(message = "departmentId must not be null")
  private UUID departmentId;

  @NotNull(message = "userId must not be null")
  private UUID userId;

  @AEnumValueValidator(enumClazz = EUserDepartmentMemberType.class)
  @NotNull(message = "MemberType must not be null")
  private int memberType;

  public UserDepartmentEdo() {

  }

  public UserDepartmentEdo(final UUID departmentId, final int memberType) {

    this.departmentId = departmentId;
    this.memberType = memberType;
  }

  public UUID getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(UUID departmentId) {
    this.departmentId = departmentId;
  }

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public int getMemberType() {

    return this.memberType;
  }

  public void setMemberType(final int memberType) {

    this.memberType = memberType;
  }

}
