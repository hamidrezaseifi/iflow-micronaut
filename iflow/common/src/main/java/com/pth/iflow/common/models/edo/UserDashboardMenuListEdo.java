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

@XmlRootElement(name = "UserDashboardMenuList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "UserDashboardMenuList" + IFlowJaxbDefinition.TYPE_PREFIX)
public class UserDashboardMenuListEdo {

  @NotNull
  @XmlElementWrapper(name = "UserDashboardMenuList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "UserDashboardMenu", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final List<UserDashboardMenuEdo> userDashboardMenus = new ArrayList<>();

  public UserDashboardMenuListEdo() {

  }

  public UserDashboardMenuListEdo(final List<UserDashboardMenuEdo> userDashboardMenus) {

    this.setUserDashboardMenus(userDashboardMenus);
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
