package com.pth.iflow.common.models.edo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;

@XmlRootElement(name = "ProfileResponse", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "ProfileResponse" + IFlowJaxbDefinition.TYPE_PREFIX)
public class ProfileResponseEdo {

  @NotNull
  @XmlElement(name = "User", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private UserEdo user;

  @NotNull
  @XmlElement(name = "CompanyProfile", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private CompanyProfileEdo companyProfile;

  @NotNull
  @XmlElement(name = "Sessionid", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String sessionid;

  @NotNull(message = "UserDashboardMenuList must not be null")
  @XmlElementWrapper(name = "UserDashboardMenuList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "UserDashboardMenu", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final List<UserDashboardMenuEdo> userDashboardMenus = new ArrayList<>();

  public ProfileResponseEdo() {

    this.user = null;
    this.companyProfile = null;
    this.sessionid = "";
  }

  public ProfileResponseEdo(final UserEdo user, final CompanyProfileEdo company, final String sessionid,
      final List<UserDashboardMenuEdo> userDashboardMenus) {

    this.user = user;
    this.companyProfile = company;
    this.sessionid = sessionid;
    this.setUserDashboardMenus(userDashboardMenus);
  }

  public UserEdo getUser() {

    return this.user;
  }

  public void setUser(final UserEdo user) {

    this.user = user;
  }

  public CompanyProfileEdo getCompanyProfile() {

    return this.companyProfile;
  }

  public void setCompanyProfile(final CompanyProfileEdo company) {

    this.companyProfile = company;
  }

  public String getSessionid() {

    return this.sessionid;
  }

  public void setSessionid(final String sessionid) {

    this.sessionid = sessionid;
  }

  public List<UserDashboardMenuEdo> getUserDashboardMenus() {

    return this.userDashboardMenus;
  }

  @JsonSetter
  public void setUserDashboardMenus(final List<UserDashboardMenuEdo> userDashboardMenus) {

    this.userDashboardMenus.clear();
    if (userDashboardMenus != null) {
      this.userDashboardMenus.addAll(userDashboardMenus);
    }
  }

}
