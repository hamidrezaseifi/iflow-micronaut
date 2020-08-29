package com.pth.common.edo;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import java.util.UUID;

public class CompanyWorkflowtypeItemOcrSettingPresetItemEdo {


    @NotNull(message = "id must not be null")
    protected UUID id;

    @NotNull(message = "PropertyName must not be null")
    private String propertyName;

  private String value;

  @NotNull(message = "OcrType must not be null")
  private Integer ocrType;

  @NotNull(message = "Status must not be null")
  private Integer status;

  @NotNull(message = "Version must not be null")
  private Integer version;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
