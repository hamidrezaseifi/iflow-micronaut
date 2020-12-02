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

@JsonIgnoreProperties(value = { "authorities", "enabled", })
public class User extends GuiBaseModel {

  private UUID companyId;
  private String username;
  private String email;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private Date birthDate;

  private String firstName;
  private String lastName;
  private Integer status;
  private Integer version;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private final Set<UserGroup> groups = new HashSet<>();
  private final List<UserDepartment> userDepartments = new ArrayList<>();
  private final Set<User> deputies = new HashSet<>();
  private final List<EUiUserRole> roles = new ArrayList<>();

  private EUserAcces permission;

  private boolean isEnabled;

  private String password;

  public User() {
    super();
    this.isEnabled = true;
    this.status = 0;
    this.permission = EUserAcces.NONE;
  }

  public UUID getCompanyId() {
    return companyId;
  }

  public void setCompanyId(UUID companyId) {
    this.companyId = companyId;
  }

  public String getUsername() {

    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public EUserAcces getPermission() {
    return permission;
  }

  public void setPermission(EUserAcces permission) {
    this.permission = permission;
  }

  @JsonSetter
  public void setPermission(String permission) {
    this.permission = EUserAcces.fromPermissionString(permission);
  }

  public void setUserAccess(String permission){
    this.permission = EUserAcces.fromPermissionString(permission);
  }

  public String getUserAccess(){
    return this.permission.toString();
  }

  public void setPermission(Integer permission) {
    this.permission = EUserAcces.fromPermission(permission);
  }

  public Set<UserGroup> getGroups() {

    return this.groups;
  }

  public void setGroups(final Set<UserGroup> groups) {

    this.groups.clear();
    if (groups != null) {
      this.groups.addAll(groups);
    }
  }

  public void addGroup(final UserGroup group) {

    this.groups.add(group);
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

  public void addUserDepartment(final UserDepartment department) {

    this.userDepartments.add(department);
  }

  public Set<User> getDeputies() {

    return this.deputies;
  }

  public void setDeputies(final Set<User> deputies) {

    this.deputies.clear();
    if (deputies != null) {
      this.deputies.addAll(deputies);
    }
  }

  public void addDeputy(final User deputy) {

    this.deputies.add(deputy);
  }

  public List<String> getRoles() {

    return this.roles.stream().map(r -> r.getName()).collect(Collectors.toList());
  }

  public Set<Integer> getRolesInt() {

    return this.roles.stream().map(r -> r.getId()).collect(Collectors.toSet());
  }

  public void setRoles(final Collection<String> roles) {

    this.roles.clear();
    if (roles != null) {
      this.roles.addAll(roles.stream().map(r -> EUiUserRole.ofValue(r)).collect(Collectors.toList()));
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

}
