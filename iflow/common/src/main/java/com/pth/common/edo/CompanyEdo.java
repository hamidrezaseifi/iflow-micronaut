package com.pth.common.edo;

import com.pth.common.edo.enums.ECompanyType;
import com.pth.common.edo.validation.AEnumValidator;

import javax.validation.constraints.NotNull;

public class CompanyEdo {

  @NotNull(message = "Identity must not be null")
  private String identity;

  @NotNull
  private String companyName;

  @AEnumValidator(enumClazz = ECompanyType.class)
  private String companyType;

  private String companyTypeCustome;

  @NotNull
  private Integer status;

  @NotNull
  private Integer version;

  public CompanyEdo() {

  }

  public String getIdentity() {

    return this.identity;
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

  public Integer getVersion() {

    return this.version;
  }

  public void setVersion(final Integer version) {

    this.version = version;
  }

}
