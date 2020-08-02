package com.pth.iflow.common.models.edo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.helper.DateEdoAdapter;
import com.pth.iflow.common.models.helper.StringToStringCollection;

@XmlRootElement(name = "User", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "User" + IFlowJaxbDefinition.TYPE_PREFIX)
public class UserEdo {

  @NotNull(message = "CompanyIdentity must not be null")
  @XmlElement(name = "CompanyIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String companyIdentity;

  @NotNull(message = "Identity must not be null")
  @XmlElement(name = "Identity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String identity;

  @NotNull(message = "Email must not be null")
  @XmlElement(name = "Email", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String email;

  @XmlJavaTypeAdapter(DateEdoAdapter.class)
  @XmlElement(name = "BirthDate", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private LocalDate birthDate;

  @NotNull(message = "FirstName must not be null")
  @XmlElement(name = "FirstName", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String firstName;

  @NotNull(message = "LastName must not be null")
  @XmlElement(name = "LastName", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String lastName;

  @NotNull(message = "Status must not be null")
  @XmlElement(name = "Status", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer status;

  @NotNull(message = "Version must not be null")
  @XmlElement(name = "Version", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer version;

  @NotNull(message = "Permission must not be null")
  @XmlElement(name = "Permission", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer permission;

  @NotNull(message = "GroupList must not be null")
  @XmlElementWrapper(name = "GroupList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "Group")
  @JsonDeserialize(using = StringToStringCollection.class)
  private final Set<String> groups = new HashSet<>();

  @NotNull(message = "UserDepartmentList must not be null")
  @XmlElementWrapper(name = "UserDepartmentList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "UserDepartment", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final List<UserDepartmentEdo> userDepartments = new ArrayList<>();

  @NotNull(message = "DeputyList must not be null")
  @XmlElementWrapper(name = "DeputyList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "Deputy", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @JsonDeserialize(using = StringToStringCollection.class)
  private final Set<String> deputies = new HashSet<>();

  @NotNull(message = "RoleList must not be null")
  @XmlElementWrapper(name = "RoleList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "Role", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final Set<String> roles = new HashSet<>();

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

  public LocalDate getBirthDate() {

    return this.birthDate;
  }

  public void setBirthDate(final LocalDate birthDate) {

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

  public Set<String> getGroups() {

    return this.groups;
  }

  @JsonSetter
  public void setGroups(final Collection<String> groups) {

    this.groups.clear();
    if (groups != null) {
      this.groups.addAll(groups);
    }
  }

  public void addGroup(final String groupId) {

    this.groups.add(groupId);
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

  public Set<String> getDeputies() {

    return this.deputies;
  }

  @JsonSetter
  public void setDeputies(final Collection<String> deputies) {

    this.deputies.clear();
    if (deputies != null) {
      this.deputies.addAll(deputies);
    }
  }

  public void addDeputy(final String deputyId) {

    this.deputies.add(deputyId);
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
