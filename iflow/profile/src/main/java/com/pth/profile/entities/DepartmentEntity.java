package com.pth.profile.entities;

import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "departments")
public class DepartmentEntity extends BaseEntity {

  private static final long serialVersionUID = 5330442911402791281L;

  @Column(name = "company_id")
  private UUID companyId;

  @Column(name = "identity")
  private String identity;

  @Column(name = "title")
  private String title;

  @Column(name = "status")
  private Integer status;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Date updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private CompanyEntity company;

  @ManyToMany(mappedBy = "groups")
  private Set<UserEntity> users = new HashSet<>();

  public DepartmentEntity() {

  }

  /*
   * (non-Javadoc)
   *
   * @see com.pth.iflow.core.model.ICoreIdentityModel#getIdentity()
   */

  public String getIdentity() {

    return identity;
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

  @Override
  public Integer getVersion() {

    return this.version;
  }

  @Override
  public void setVersion(final Integer version) {

    this.version = version;
  }

  public Date getCreatedAt() {

    return this.createdAt;
  }

  public void setCreatedAt(final Date createdAt) {

    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {

    return this.updatedAt;
  }

  public void setUpdatedAt(final Date updatedAt) {

    this.updatedAt = updatedAt;
  }

  public String getIdentityPreffix() {

    return "dp";
  }

  public void increaseVersion() {

    version += 1;
  }

  public Set<UserEntity> getUsers() {

    return users;
  }

  public void setUsers(final Set<UserEntity> users) {

    this.users = users;
  }

}
