package com.pth.gui.models;

import java.util.UUID;

public class Department {

  private UUID id;
  private String companyIdentity;
  private String identity;
  private String title;
  private Integer status;
  private Integer version;
  private User manager;
  private User deputy;

  public Department() {
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public String getCompanyIdentity() {

    return this.companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
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

  public Integer getVersion() {

    return this.version;
  }

  public void setVersion(final Integer version) {

    this.version = version;
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
