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

@XmlRootElement(name = "CompanyWorkflowtypeItemOcrSettingPreset", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
         namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "CompanyWorkflowtypeItemOcrSettingPreset" + IFlowJaxbDefinition.TYPE_PREFIX
)
public class CompanyWorkflowtypeItemOcrSettingPresetEdo {

  @NotNull(message = "Identity must not be null")
  @XmlElement(name = "Identity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String identity;

  @NotNull(message = "CompanyIdentity must not be null")
  @XmlElement(name = "CompanyIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String companyIdentity;

  @NotNull(message = "WorkflowTypeIdentity must not be null")
  @XmlElement(name = "WorkflowTypeIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String workflowTypeIdentity;

  @NotNull(message = "PresetName must not be null")
  @XmlElement(name = "PresetName", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String presetName;

  @NotNull(message = "Status must not be null")
  @XmlElement(name = "Status", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer status;

  @NotNull(message = "Version must not be null")
  @XmlElement(name = "Version", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer version;

  @NotNull(message = "CompanyWorkflowtypeItemOcrSettingPresetItemList must not be null")
  @XmlElementWrapper(name = "CompanyWorkflowtypeItemOcrSettingPresetItemList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "CompanyWorkflowtypeItemOcrSettingPresetItem", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private List<CompanyWorkflowtypeItemOcrSettingPresetItemEdo> items = new ArrayList<>();

  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public String getCompanyIdentity() {

    return this.companyIdentity;
  }

  public void setCompanyIdentity(final String companyIdentity) {

    this.companyIdentity = companyIdentity;
  }

  public String getWorkflowTypeIdentity() {

    return this.workflowTypeIdentity;
  }

  public void setWorkflowTypeIdentity(final String workflowIdentity) {

    this.workflowTypeIdentity = workflowIdentity;
  }

  public String getPresetName() {

    return this.presetName;
  }

  public void setPresetName(final String presetName) {

    this.presetName = presetName;
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

  public List<CompanyWorkflowtypeItemOcrSettingPresetItemEdo> getItems() {

    return this.items;
  }

  @JsonSetter
  public void setItems(final List<CompanyWorkflowtypeItemOcrSettingPresetItemEdo> items) {

    this.items = new ArrayList<>();
    if (items != null) {
      this.items.addAll(items);
    }
  }

}
