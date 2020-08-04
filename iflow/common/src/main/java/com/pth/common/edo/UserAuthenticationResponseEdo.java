package com.pth.common.edo;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

public class UserAuthenticationResponseEdo {

  @NotNull
  private String userIdentity;

  @NotNull
  private String companyIdentity;

  @NotNull
  private String token;

  @NotNull
  private String sessionid;

  @NotNull(message = "RoleList must not be null")
  private final Set<String> roles = new HashSet<>();

  @NotNull
  private Long created;

  @NotNull
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
