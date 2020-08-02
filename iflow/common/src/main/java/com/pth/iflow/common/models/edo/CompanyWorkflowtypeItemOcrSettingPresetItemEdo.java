package com.pth.iflow.common.models.edo;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.pth.iflow.common.enums.EOcrType;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.validation.AEnumValueValidator;

@XmlRootElement(name = "CompanyWorkflowtypeItemOcrSettingPresetItem", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
         namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "CompanyWorkflowtypeItemOcrSettingPresetItem"
             + IFlowJaxbDefinition.TYPE_PREFIX
)
public class CompanyWorkflowtypeItemOcrSettingPresetItemEdo {

  @NotNull(message = "PropertyName must not be null")
  @XmlElement(name = "PropertyName", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String propertyName;

  @XmlElement(name = "Value", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String value;

  @NotNull(message = "OcrType must not be null")
  @XmlElement(name = "OcrType", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @AEnumValueValidator(enumClazz = EOcrType.class)
  private Integer ocrType;

  @NotNull(message = "Status must not be null")
  @XmlElement(name = "Status", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer status;

  @NotNull(message = "Version must not be null")
  @XmlElement(name = "Version", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
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
