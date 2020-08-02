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

@XmlRootElement(name = "CompanyWorkflowtypeItemOcrSettingPresetList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
         namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "CompanyWorkflowtypeItemOcrSettingPresetList"
             + IFlowJaxbDefinition.TYPE_PREFIX
)
public class CompanyWorkflowtypeItemOcrSettingPresetListEdo {

  @NotNull
  @XmlElementWrapper(name = "CompanyWorkflowtypeItemOcrSettingList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "CompanyWorkflowtypeItemOcrSetting", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> companyWorkflowtypeItemOcrSettings = new ArrayList<>();

  public CompanyWorkflowtypeItemOcrSettingPresetListEdo() {

  }

  public CompanyWorkflowtypeItemOcrSettingPresetListEdo(
      final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> companyWorkflowtypeItemOcrSettings) {

    this.setCompanyWorkflowtypeItemOcrSettings(companyWorkflowtypeItemOcrSettings);
  }

  public List<CompanyWorkflowtypeItemOcrSettingPresetEdo> getCompanyWorkflowtypeItemOcrSettings() {

    return this.companyWorkflowtypeItemOcrSettings;
  }

  @JsonSetter
  public void
      setCompanyWorkflowtypeItemOcrSettings(final List<CompanyWorkflowtypeItemOcrSettingPresetEdo> companyWorkflowtypeItemOcrSettings) {

    this.companyWorkflowtypeItemOcrSettings.clear();
    if (companyWorkflowtypeItemOcrSettings != null) {
      this.companyWorkflowtypeItemOcrSettings.addAll(companyWorkflowtypeItemOcrSettings);
    }
  }

}
