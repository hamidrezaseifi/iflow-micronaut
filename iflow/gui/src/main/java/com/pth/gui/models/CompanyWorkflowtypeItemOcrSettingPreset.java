package com.pth.gui.models;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CompanyWorkflowtypeItemOcrSettingPreset extends GuiBaseModel {

  private String identity;

  private UUID companyId;

  private UUID workflowTypeId;

  private String presetName;

  private Integer status;

  private List<CompanyWorkflowtypeItemOcrSettingPresetItem> items = new ArrayList<>();

  public CompanyWorkflowtypeItemOcrSettingPreset() {
    super();
  }

  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public UUID getCompanyId() {
    return companyId;
  }

  public void setCompanyId(UUID companyId) {
    this.companyId = companyId;
  }

  public UUID getWorkflowTypeId() {
    return workflowTypeId;
  }

  public void setWorkflowTypeId(UUID workflowTypeId) {
    this.workflowTypeId = workflowTypeId;
  }

  public String getPresetName() {

    return this.presetName;
  }

  public boolean hasPresetName(String presetName) {

    return StringUtils.equals(this.presetName, presetName);
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
