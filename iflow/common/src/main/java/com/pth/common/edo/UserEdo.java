package com.pth.common.edo;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.micronaut.core.annotation.Introspected;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Introspected
public class UserEdo {

  @NotNull(message = "CompanyIdentity must not be null")
  private String companyIdentity;

  @NotNull(message = "CompanyId must not be null")
  private UUID companyId;

  @NotNull(message = "Identity must not be null")
  private String identity;

  @NotNull(message = "Email must not be null")
  private String email;

  private Date birthDate;

  @NotNull(message = "FirstName must not be null")
  private String firstName;

  @NotNull(message = "LastName must not be null")
  private String lastName;

  @NotNull(message = "Status must not be null")
  private Integer status;

  @NotNull(message = "Version must not be null")
  private Integer version;

  @NotNull(message = "Permission must not be null")
  private Integer permission;

  @NotNull(message = "GroupList must not be null")
  private final Set<UserGroupEdo> groups = new HashSet<>();

  @NotNull(message = "UserDepartmentList must not be null")
  private final List<UserDepartmentEdo> userDepartments = new ArrayList<>();

  @NotNull(message = "DeputyList must not be null")
  private final Set<UserEdo> deputies = new HashSet<>();

  @NotNull(message = "RoleList must not be null")
  private final Set<String> roles = new HashSet<>();

  public UUID getCompanyId() {
    return companyId;
  }

  public void setCompanyId(UUID companyId) {
    this.companyId = companyId;
  }

  public String getCompanyIdentity() {

    return this.companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
  }

  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
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

  public String getFirstName() {

    return this.firstName;
  }

  public void setFirstName(final String firstName) {

    this.firstName = firstName;
  }

  public String getLastName() {

    return this.lastName;
  }

  public void setLastName(final String lastName) {

    this.lastName = lastName;
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

  public Integer getPermission() {

    return this.permission;
  }

  public void setPermission(final Integer permission) {

    this.permission = permission;
  }

  public Set<UserGroupEdo> getGroups() {

    return this.groups;
  }

  @JsonSetter
  public void setGroups(final Collection<UserGroupEdo> groups) {

    this.groups.clear();
    if (groups != null) {
      this.groups.addAll(groups);
    }
  }

  public void addGroup(final UserGroupEdo group) {

    this.groups.add(group);
  }

  public List<UserDepartmentEdo> getUserDepartments() {

    return this.userDepartments;
  }

  @JsonSetter
  public void setUserDepartments(final Collection<UserDepartmentEdo> departments) {

    this.userDepartments.clear();
    if (departments != null) {
      this.userDepartments.addAll(departments);
    }
  }

  public void addUserDepartment(final UserDepartmentEdo userDepartmentEdo) {

    this.userDepartments.add(userDepartmentEdo);
  }

  public Set<UserEdo> getDeputies() {

    return this.deputies;
  }

  @JsonSetter
  public void setDeputies(final Collection<UserEdo> deputies) {

    this.deputies.clear();
    if (deputies != null) {
      this.deputies.addAll(deputies);
    }
  }

  public void addDeputy(final UserEdo deputy) {

    this.deputies.add(deputy);
  }

  public Set<String> getRoles() {

    return this.roles;
  }

  @JsonSetter
  public void setRoles(final Set<String> roles) {

    this.roles.clear();
    if (roles != null) {
      this.roles.addAll(roles);
    }
  }

  public void addRole(final String role) {

    this.roles.add(role);
  }

}
