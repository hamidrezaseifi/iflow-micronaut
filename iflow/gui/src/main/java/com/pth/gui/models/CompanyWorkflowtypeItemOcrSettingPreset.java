package com.pth.gui.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyWorkflowtypeItemOcrSettingPreset {

  private String identity;

  private String companyIdentity;

  private String workflowTypeIdentity;

  private String presetName;

  private Integer status;

  private Integer version;

  private List<CompanyWorkflowtypeItemOcrSettingPresetItem> items = new ArrayList<>();

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

  public List<CompanyWorkflowtypeItemOcrSettingPresetItem> getItems() {

    return this.items;
  }

  public void setItems(final List<CompanyWorkflowtypeItemOcrSettingPresetItem> items) {

    this.items = new ArrayList<>();
    if (items != null) {
      this.items.addAll(items);
    }
  }

  public void prepareItems() {

    this.removeEmptyItems();
    this.items.forEach(i -> i.prepare());

  }

  public void removeEmptyItems() {

    final List<CompanyWorkflowtypeItemOcrSettingPresetItem> valuedItems = this.items
        .stream()
        .filter(i -> i.hasValue())
        .collect(Collectors.toList());
    this.setItems(valuedItems);
  }

}
