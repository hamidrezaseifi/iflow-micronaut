package com.pth.core.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.pth.common.edo.enums.EUserStatus;
import com.pth.common.entities.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

  private static final long serialVersionUID = 5149221021424815133L;

  @Column(name = "identity")
  private String identity;

  @Column(name = "email")
  private String email;

  @Column(name = "company_id")
  private Long companyId;

  @Column(name = "birthdate")
  private Date birthDate;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "status")
  private Integer status;

  @Column(name = "permission")
  private Integer permission;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Date updatedAt;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "company_id", insertable = false, updatable = false)
  private CompanyEntity company;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(
             name = "user_usergroup", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "user_group") }
  )
  private final Set<UserGroupEntity> groups = new HashSet<>();

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(
             name = "user_deputy", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "deputy_id") }
  )
  private final Set<UserEntity> deputies = new HashSet<>();

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role") })
  private final Set<IflowRoleEntity> roles = new HashSet<>();

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user", fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  private final List<UserDepartmentEntity> userDepartments = new ArrayList<>();

  public UserEntity() {

  }

  public String getIdentity() {

    return identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public Long getCompanyId() {

    return this.companyId;
  }

  public void setCompanyId(final Long companyId) {

    this.companyId = companyId;
  }

  public String getEmail() {

    return this.email;
  }

  public void setEmail(final String email) {

    this.email = email;
  }

  public Date getBirthDate() {

    return this.birthDate;
  }

  public void setBirthDate(final Date birthDate) {

    this.birthDate = birthDate;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {

    return this.firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(final String firstName) {

    this.firstName = firstName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {

    return this.lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(final String lastName) {

    this.lastName = lastName;
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

  public boolean isActive() {

    return this.status == EUserStatus.ACTIVE.getValue().intValue();
  }

  /**
   * @return the permission
   */
  public Integer getPermission() {

    return this.permission;
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

  /**
   * @param permission the permission to set
   */
  public void setPermission(final Integer permission) {

    this.permission = permission;
  }

  public CompanyEntity getCompany() {

    return company;
  }

  public void setCompany(final CompanyEntity company) {

    this.company = company;
  }

  public String getIdentityPreffix() {

    return "u";
  }

  public Set<UserGroupEntity> getGroups() {

    return groups;
  }

  public void setGroups(final Collection<UserGroupEntity> groups) {

    this.groups.clear();
    for (final UserGroupEntity model : groups) {

      model.getUsers().add(this);

      this.groups.add(model);
    }

  }

  public List<UserDepartmentEntity> getUserDepartments() {

    return userDepartments;
  }

  public void setUserDepartments(final Collection<UserDepartmentEntity> userDepartments) {

    this.userDepartments.clear();
    for (final UserDepartmentEntity model : userDepartments) {
      model.setUser(this);
      this.userDepartments.add(model);
    }

  }

  public void addUserDepartment(final DepartmentEntity department, final int memberType) {

    final UserDepartmentEntity model = new UserDepartmentEntity();
    model.setDepartment(department);
    model.setUser(this);
    model.setMemberType(memberType);

    this.userDepartments.add(model);

  }

  public void addUserDepartment(final UserDepartmentEntity model) {

    model.setUser(this);

    this.userDepartments.add(model);

  }

  public Set<DepartmentEntity> getDepartments() {

    return this.userDepartments.stream().map(ud -> ud.getDepartment()).collect(Collectors.toSet());
  }

  public Set<UserEntity> getDeputies() {

    return deputies;
  }

  public void setDeputies(final Collection<UserEntity> deputies) {

    this.deputies.clear();
    for (final UserEntity model : deputies) {

      this.deputies.add(model);
    }
  }

  public Set<IflowRoleEntity> getRoles() {

    return roles;
  }

  public void setRoles(final Collection<IflowRoleEntity> roles) {

    this.roles.clear();
    for (final IflowRoleEntity model : roles) {

      this.roles.add(model);
    }
  }

  public String generateUserIdentity(final String lastIdentity) {

    Long nextId = 0L;
    if (StringUtils.isNotEmpty(lastIdentity) && lastIdentity.length() > 10) {
      final String lastIdString = lastIdentity.substring(lastIdentity.length() - 10, lastIdentity.length());
      nextId = Long.parseLong(lastIdString) + 1;
    }

    this.identity = this.company.getIdentity() + "-" + String.format("%010d", nextId);
    return this.identity;
  }
}
