package com.pth.profile.models;

import java.util.HashSet;
import java.util.Set;

public class UserAuthenticationResponse {

  private String username;

  private String companyIdentity;

  private String token;

  private String sessionid;

  private final Set<String> roles = new HashSet<>();

  private Long created;

  private Long lastAccess;

  public String getUsername() {

    return this.username;
  }

  public void setUsername(final String username) {

    this.username = username;
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
