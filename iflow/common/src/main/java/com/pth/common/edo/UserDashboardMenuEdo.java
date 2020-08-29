package com.pth.common.edo;

import com.pth.common.edo.enums.EApplication;
import com.pth.common.edo.validation.AEnumNameValidator;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UserDashboardMenuEdo {


  @NotNull(message = "id must not be null")
  protected UUID id;

  @NotNull(message = "UserIdentity must not be null")
  private String userIdentity;

  @AEnumNameValidator(enumClazz = EApplication.class)
  @NotNull(message = "AppId must not be null")
  private String appId;

  @NotNull(message = "MenuId must not be null")
  private String menuId;

  @NotNull(message = "RowIndex must not be null")
  private Integer rowIndex;

  @NotNull(message = "ColumnIndex must not be null")
  private Integer columnIndex;

  @NotNull(message = "Status must not be null")
  private Integer status;

  @NotNull(message = "Version must not be null")
  private Integer version;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

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
