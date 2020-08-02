package com.pth.iflow.common.models.edo;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.pth.iflow.common.models.base.IFlowJaxbDefinition;

@XmlRootElement(name = "UserAuthenticationResponse", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "UserAuthenticationResponse" + IFlowJaxbDefinition.TYPE_PREFIX)
public class UserAuthenticationResponseEdo {

  @NotNull
  @XmlElement(name = "UserIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String userIdentity;

  @NotNull
  @XmlElement(name = "CompanyIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String companyIdentity;

  @NotNull
  @XmlElement(name = "Token", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String token;

  @NotNull
  @XmlElement(name = "SessionId", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String sessionid;

  @NotNull(message = "RoleList must not be null")
  @XmlElementWrapper(name = "RoleList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "Role", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final Set<String> roles = new HashSet<>();

  @NotNull
  @XmlElement(name = "Created", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Long created;

  @NotNull
  @XmlElement(name = "LastAccess", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Long lastAccess;

  public String getUserIdentity() {

    return this.userIdentity;
  }

  public void setUserIdentity(final String userIdentity) {

    this.userIdentity = userIdentity;
  }

  public String getCompanyIdentity() {

    return this.companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
  }

  /**
   * @return the token
   */
  public String getToken() {

    return this.token;
  }

  /**
   * @param token the token to set
   */
  public void setToken(final String token) {

    this.token = token;
  }

  /**
   * @return the sessionid
   */
  public String getSessionid() {

    return this.sessionid;
  }

  /**
   * @param sessionid the sessionid to set
   */
  public void setSessionid(final String sessionid) {

    this.sessionid = sessionid;
  }

  public Set<String> getRoles() {

    return this.roles;
  }

  /**
   * @return the created
   */
  public Long getCreated() {

    return this.created;
  }

  /**
   * @param created the created to set
   */
  public void setCreated(final Long created) {

    this.created = created;
  }

  /**
   * @return the lastAccess
   */
  public Long getLastAccess() {

    return this.lastAccess;
  }

  /**
   * @param lastAccess the lastAccess to set
   */
  public void setLastAccess(final Long lastAccess) {

    this.lastAccess = lastAccess;
  }

}
