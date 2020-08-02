package com.pth.iflow.profile.model;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.pth.iflow.common.enums.EOcrType;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.validation.AEnumValueValidator;

public class CompanyWorkflowtypeItemOcrSettingPresetItem {

  private String propertyName;

  private String value;

  private Integer ocrType;

  private Integer status;

  private Integer version;

  public String getPropertyName() {

    return this.propertyName;
  }

  public void setPropertyName(final String propertyName) {

    this.propertyName = propertyName;
  }

  public String getValue() {

    return this.value;
  }

  public void setValue(final String value) {

    this.value = value;
  }

  public Integer getOcrType() {

    return this.ocrType;
  }

  public void setOcrType(final Integer ocrType) {

    this.ocrType = ocrType;
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

}
