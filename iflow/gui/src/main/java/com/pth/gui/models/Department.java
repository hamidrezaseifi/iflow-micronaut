package com.pth.gui.models;

import java.util.UUID;

public class Department extends GuiBaseModel {

  private UUID companyId;
  private String identity;
  private String title;
  private Integer status;
  private User manager;
  private User deputy;

  public Department() {
    super();
  }

  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public UUID getCompanyId() {

    return this.companyId;
  }

  public void setCompanyId(final UUID companyId) {

    this.companyId = companyId;
  }

  public String getTitle() {

    return this.title;
  }

  public void setTitle(final String title) {

    this.title = title;
  }

  public Integer getStatus() {

    return this.status;
  }

  public void setStatus(final Integer status) {

    this.status = status;
  }

  public User getManager() {

    return this.manager;
  }

  public void setManager(final User manager) {

    this.manager = manager;
  }

  public User getDeputy() {

    return this.deputy;
  }

  public void setDeputy(final User deputy) {

    this.deputy = deputy;
  }

}
