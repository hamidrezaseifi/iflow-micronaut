package com.pth.profile.entities;

import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "user_dashboard_menus")
public class UserDashboardMenuEntity extends BaseEntity {

  private static final long serialVersionUID = 2937568589389217869L;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "menu_id")
  private String menuId;

  @Column(name = "app_id")
  private String appId;

  @Column(name = "row_index")
  private Integer rowIndex;

  @Column(name = "column_index")
  private Integer columnIndex;

  @Column(name = "status")
  private Integer status;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  private UserEntity user;


  public UUID getUserId() {

    return userId;
  }

  public void setUserId(final UUID userId) {

    this.userId = userId;
  }

  public String getAppId() {

    return appId;
  }

  public void setAppId(final String appId) {

    this.appId = appId;
  }

  public String getMenuId() {

    return menuId;
  }

  public void setMenuId(final String menuId) {

    this.menuId = menuId;
  }

  public Integer getRowIndex() {

    return rowIndex;
  }

  public void setRowIndex(final Integer rowIndex) {

    this.rowIndex = rowIndex;
  }

  public Integer getColumnIndex() {

    return columnIndex;
  }

  public void setColumnIndex(final Integer columnIndex) {

    this.columnIndex = columnIndex;
  }

  public Integer getStatus() {

    return status;
  }

  public void setStatus(final Integer status) {

    this.status = status;
  }

  @Override
  public Integer getVersion() {

    return version;
  }

  @Override
  public void setVersion(final Integer version) {

    this.version = version;
  }

  public Date getCreatedAt() {

    return createdAt;
  }

  public void setCreatedAt(final Date createdAt) {

    this.createdAt = createdAt;
  }

  public UserEntity getUser() {

    return user;
  }

  public void increaseVersion() {

    version += 1;
  }
}
