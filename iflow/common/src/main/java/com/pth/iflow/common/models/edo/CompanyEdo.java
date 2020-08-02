package com.pth.iflow.common.models.edo;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.pth.iflow.common.enums.ECompanyType;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.validation.AEnumValidator;

@XmlRootElement(name = "Company", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "Company" + IFlowJaxbDefinition.TYPE_PREFIX)
public class CompanyEdo {

  @NotNull(message = "Identity must not be null")
  @XmlElement(name = "Identity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String identity;

  @NotNull
  @XmlElement(name = "CompanyName", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String companyName;

  @AEnumValidator(enumClazz = ECompanyType.class)
  @XmlElement(name = "CompanyType", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String companyType;

  @XmlElement(name = "CompanyTypeCustome", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String companyTypeCustome;

  @NotNull
  @XmlElement(name = "Status", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer status;

  @NotNull
  @XmlElement(name = "Version", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer version;

  public CompanyEdo() {

  }

  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  /**
   * @return the companyName
   */
  public String getCompanyName() {

    return this.companyName;
  }

  /**
   * @param companyName the companyName to set
   */
  public void setCompanyName(final String companyName) {

    this.companyName = companyName;
  }

  public String getCompanyType() {

    return this.companyType;
  }

  public void setCompanyType(final String companyType) {

    this.companyType = companyType;
  }

  public String getCompanyTypeCustome() {

    return this.companyTypeCustome;
  }

  public void setCompanyTypeCustome(final String companyTypeCustome) {

    this.companyTypeCustome = companyTypeCustome;
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

  public Integer getVersion() {

    return this.version;
  }

  public void setVersion(final Integer version) {

    this.version = version;
  }

}
