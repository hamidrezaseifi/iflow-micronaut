package com.pth.profile.entities;

import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "companies")
public class CompanyEntity extends BaseEntity {

  @Column(name = "identity")
  private String identity;

  @Column(name = "company_name")
  private String companyName;

  @Column(name = "company_type")
  private String companyType;

  @Column(name = "company_type_custome")
  private String companyTypeCustome;

  @Column(name = "status")
  private Integer status;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Date updatedAt;

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

    return companyType;
  }

  public void setCompanyType(final String companyType) {

    this.companyType = companyType;
  }

  public String getCompanyTypeCustome() {

    return companyTypeCustome;
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

  @Override
  public Integer getVersion() {

    return this.version;
  }

  /**
   * @param version the version to set
   */

  @Override
  public void setVersion(final Integer version) {

    this.version = version;
  }

  /**
   * @return the createdAt
   */
  public Date getCreatedAt() {

    return this.createdAt;
  }

  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(final Date createdAt) {

    this.createdAt = createdAt;
  }

  /**
   * @return the updatedAt
   */
  public Date getUpdatedAt() {

    return this.updatedAt;
  }

  /**
   * @param updatedAt the updatedAt to set
   */
  public void setUpdatedAt(final Date updatedAt) {

    this.updatedAt = updatedAt;
  }

  public String getIdentityPreffix() {

    return "c";
  }

  public void increaseVersion() {

    version += 1;
  }
}
