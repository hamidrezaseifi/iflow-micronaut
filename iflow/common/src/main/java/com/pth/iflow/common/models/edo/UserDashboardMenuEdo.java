package com.pth.iflow.common.models.edo;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.pth.iflow.common.enums.EApplication;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.validation.AEnumNameValidator;

@XmlRootElement(name = "UserDashboardMenu", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "UserDashboardMenu" + IFlowJaxbDefinition.TYPE_PREFIX)
public class UserDashboardMenuEdo {

  @NotNull(message = "UserIdentity must not be null")
  @XmlElement(name = "UserIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String userIdentity;

  @AEnumNameValidator(enumClazz = EApplication.class)
  @NotNull(message = "AppId must not be null")
  @XmlElement(name = "AppId", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String appId;

  @NotNull(message = "MenuId must not be null")
  @XmlElement(name = "MenuId", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String menuId;

  @NotNull(message = "RowIndex must not be null")
  @XmlElement(name = "RowIndex", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer rowIndex;

  @NotNull(message = "ColumnIndex must not be null")
  @XmlElement(name = "ColumnIndex", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer columnIndex;

  @NotNull(message = "Status must not be null")
  @XmlElement(name = "Status", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer status;

  @NotNull(message = "Version must not be null")
  @XmlElement(name = "Version", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer version;

  public String getUserIdentity() {

    return this.userIdentity;
  }

  public void setUserIdentity(final String userIdentity) {

    this.userIdentity = userIdentity;
  }

  public String getAppId() {

    return this.appId;
  }

  public void setAppId(final String appId) {

    this.appId = appId;
  }

  public String getMenuId() {

    return this.menuId;
  }

  public void setMenuId(final String menuId) {

    this.menuId = menuId;
  }

  public Integer getRowIndex() {

    return this.rowIndex;
  }

  public void setRowIndex(final Integer rowIndex) {

    this.rowIndex = rowIndex;
  }

  public Integer getColumnIndex() {

    return this.columnIndex;
  }

  public void setColumnIndex(final Integer columnIndex) {

    this.columnIndex = columnIndex;
  }

  public Integer getStatus() {

    return this.status;
  }

  public void setStatus(final Integer status) {

    this.status = status;
  }

  public Integer getVersion() {

    return this.version;
  }

  public void setVersion(final Integer version) {

    this.version = version;
  }

}
