package com.pth.iflow.common.enums;

public enum ECompanyType implements IEnumValidator {
  CUSTOME,
  EINZELUNTERNEHMEN,
  GBR,
  OHG,
  KG,
  GMBH,
  UG;

  @Override
  public String getEnumValue() {

    return this.name();
  }

}
