package com.pth.common.edo;

import javax.validation.constraints.NotNull;

public class UserGroupEdo {

  @NotNull(message = "Identity must not be null")
  private String identity;

  @NotNull(message = "CompanyIdentity must not be null")
  private String companyIdentity;

  @NotNull(message = "Title must not be null")
  private String title;

  @NotNull(message = "Status must not be null")
  private Integer status;

  @NotNull(message = "Version must not be null")
  private Integer version;

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

}
