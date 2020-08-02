package com.pth.iflow.common.models.edo;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.pth.iflow.common.enums.EUserDepartmentMemberType;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.validation.AEnumValueValidator;

@XmlRootElement(name = "UserDepartment", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "UserDepartment" + IFlowJaxbDefinition.TYPE_PREFIX)
public class UserDepartmentEdo {

  @NotNull(message = "DepartmentIdentity must not be null")
  @XmlElement(name = "DepartmentIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String departmentIdentity;

  @AEnumValueValidator(enumClazz = EUserDepartmentMemberType.class)
  @NotNull(message = "MemberType must not be null")
  @XmlElement(name = "MemberType", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
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
