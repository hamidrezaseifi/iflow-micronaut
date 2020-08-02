package com.pth.iflow.common.models.edo;

import com.pth.iflow.common.models.base.IFlowJaxbDefinition;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

public class UserPasswordGenerationResponseEdo {

  @NotNull
  @XmlElement(name = "UserIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String userIdentity;

  @NotNull
  @XmlElement(name = "CompanyIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String companyIdentity;

  @NotNull
  @XmlElement(name = "PasswordHash", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String passwordHash;

  @NotNull
  @XmlElement(name = "PasswordSalt", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String passwordSalt;

  public String getUserIdentity() {
    return userIdentity;
  }

  public void setUserIdentity(String userIdentity) {
    this.userIdentity = userIdentity;
  }

  public String getCompanyIdentity() {
    return companyIdentity;
  }

  public void setCompanyIdentity(String companyIdentity) {
    this.companyIdentity = companyIdentity;
  }

  public String getPasswordHash() {
    return passwordHash;
  }

  public void setPasswordHash(String passwordHash) {
    this.passwordHash = passwordHash;
  }

  public String getPasswordSalt() {
    return passwordSalt;
  }

  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt;
  }
}
