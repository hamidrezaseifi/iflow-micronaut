package com.pth.gui.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pth.common.edo.enums.EUserAcces;
import com.pth.common.edo.enums.EUserStatus;
import com.pth.gui.enums.EUiUserRole;

@JsonIgnoreProperties(value = { "authorities", "enabled", "roles", })
public class User extends GuiBaseModel {

  private String identity;
  private String companyIdentity;
  private String email;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate birthDate;

  private String firstName;
  private String lastName;
  private Integer status;
  private Integer version;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private final Set<String> groups = new HashSet<>();
  private final List<UserDepartment> userDepartments = new ArrayList<>();
  private final Set<String> deputies = new HashSet<>();
  private final List<EUiUserRole> roles = new ArrayList<>();

  private EUserAcces userAccess;

  private boolean isEnabled;

  private String password;

  public User() {
    super();
    this.isEnabled = true;
  }

  public String getCompanyIdentity() {

    return this.companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
  }

  public String getUsername() {

    return this.email;
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

  public String geFullName() {

    return this.lastName + ", " + this.firstName;
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
   * @return the createdAt
   */
  public LocalDateTime getCreatedAt() {

    return this.createdAt;
  }

  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(final LocalDateTime createdAt) {

    this.createdAt = createdAt;
  }

  /**
   * @return the updatedAt
   */
  public LocalDateTime getUpdatedAt() {

    return this.updatedAt;
  }

  /**
   * @param updatedAt the updatedAt to set
   */
  public void setUpdatedAt(final LocalDateTime updatedAt) {

    this.updatedAt = updatedAt;
  }

  public Integer getPermissionFromUserAccess() {

    return this.userAccess.getPermission();
  }

  public void setUserAccessFromPermission(final Integer permission) {

    this.userAccess = EUserAcces.fromPermission(permission);
  }

  public EUserAcces getUserAccess() {

    return this.userAccess;
  }

  public String getUserAccessLabel() {

    return this.userAccess.getLabelId();
  }

  public void setUserAccess(final EUserAcces userAcces) {

    this.userAccess = userAcces;
  }

  public void setUserAccess111(final String userAcces) {

    this.userAccess = EUserAcces.valueOf(userAcces);
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

  public List<EUiUserRole> getRoles() {

    return this.roles;
  }

  public Set<Integer> getRolesInt() {

    return this.roles.stream().map(r -> r.getId()).collect(Collectors.toSet());
  }

  public void setRoles(final Set<Integer> roles) {

    this.roles.clear();
    if (roles != null) {
      this.roles.addAll(roles.stream().map(r -> EUiUserRole.ofId(r)).collect(Collectors.toList()));
    }
  }

  public void addRole(final Integer role) {

    this.roles.add(EUiUserRole.ofId(role));
  }

  public String getFullName() {

    return this.lastName + ", " + this.firstName;
  }

  public boolean allowEdit() {

    return true;
  }

  public boolean isAdmin() {

    return true;
  }

  private String getRolesAuthoritiesNames() {

    String name = "";
    for (final EUiUserRole role : this.roles) {
      name += (name.isEmpty() ? "" : ", ") + role.getAuthority().toUpperCase();
    }
    return name;
  }

  public String getPassword() {

    return this.password;
  }

  public void setPassword(final String password) {

    this.password = password;
  }

  public boolean isEnabled() {

    return this.isEnabled;
  }

  @JsonSetter("isEnabled")
  public void setEnabled(final boolean isEnabled) {

    this.isEnabled = isEnabled;
  }

  public String getUserTitle() {

    return this.lastName + ", " + this.firstName;
  }

  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

}
