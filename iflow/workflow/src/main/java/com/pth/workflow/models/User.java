package com.pth.workflow.models;

import com.pth.common.edo.enums.EUserStatus;

import java.time.LocalDate;
import java.util.*;

public class User {

  private UUID id;
  private String username;
  private UUID companyId;
  private String email;
  private LocalDate birthDate;
  private String firstName;
  private String lastName;
  private Integer status;
  private Integer permission;
  private Integer version;
  private final Set<String> groups = new HashSet<>();
  private final List<UserDepartment> userDepartments = new ArrayList<>();
  private final Set<String> deputies = new HashSet<>();
  private final Set<Integer> roles = new HashSet<>();

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UUID getCompanyId() {
    return companyId;
  }

  public void setCompanyId(UUID companyId) {
    this.companyId = companyId;
  }

  public String getEmail() {

    return this.email;
  }

  public void setEmail(final String email) {

    this.email = email;
  }

  public LocalDate getBirthDate() {

    return this.birthDate;
  }

  public void setBirthDate(final LocalDate birthDate) {

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

  public Integer getVersion() {

    return this.version;
  }

  /**
   * @param version the version to set
   */

  public void setVersion(final Integer version) {

    this.version = version;
  }

  /**
   * @param permission the permission to set
   */
  public void setPermission(final Integer permission) {

    this.permission = permission;
  }

  public Set<String> getGroups() {

    return this.groups;
  }

  public void setGroups(final Set<String> groups) {

    this.groups.clear();
    if (groups != null) {
      this.groups.addAll(groups);
    }
  }

  public void addGroup(final String groupId) {

    this.groups.add(groupId);
  }

  public List<UserDepartment> getUserDepartments() {

    return this.userDepartments;
  }

  public void setUserDepartments(final List<UserDepartment> departments) {

    this.userDepartments.clear();
    if (departments != null) {
      this.userDepartments.addAll(departments);
    }
  }

  public Set<String> getDeputies() {

    return this.deputies;
  }

  public void setDeputies(final Set<String> deputies) {

    this.deputies.clear();
    if (deputies != null) {
      this.deputies.addAll(deputies);
    }
  }

  public void addDeputy(final String deputyId) {

    this.deputies.add(deputyId);
  }

  public Set<Integer> getRoles() {

    return this.roles;
  }

  public void setRoles(final Set<Integer> roles) {

    this.roles.clear();
    if (roles != null) {
      this.roles.addAll(roles);
    }
  }

  public void addRole(final Integer role) {

    this.roles.add(role);
  }

}
