package com.pth.workflow.models;


public class Company {

  private String identity;
  private String companyName;
  private String companyType;
  private String companyTypeCustome;
  private Integer status;
  private Integer version;

  public String getIdentity() {

    return identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  /**
   * @return the companyName
   */
  public String getCompanyName() {

    return this.companyName;
  }

  /**
   * @param companyName the companyName to set
   */
  public void setCompanyName(final String companyName) {

    this.companyName = companyName;
  }

  public String getCompanyType() {

    return this.companyType;
  }

  public void setCompanyType(final String companyType) {

    this.companyType = companyType;
  }

  public String getCompanyTypeCustome() {

    return this.companyTypeCustome;
  }

  public void setCompanyTypeCustome(final String companyTypeCustome) {

    this.companyTypeCustome = companyTypeCustome;
  }

  /**
   * @return the status
   */
  public Integer getStatus() {

    return this.status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(final Integer status) {

    this.status = status;
  }

  /**
   * @return the version
   */

  public Integer getVersion() {

    return this.version;
  }

  /**
   * @param version the version to set
   */

  public void setVersion(final Integer version) {

    this.version = version;
  }

}
