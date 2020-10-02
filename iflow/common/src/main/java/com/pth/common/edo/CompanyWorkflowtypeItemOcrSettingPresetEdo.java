package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class CompanyWorkflowtypeItemOcrSettingPresetEdo {


  @NotNull(message = "id must not be null")
  protected UUID id;

  @NotNull(message = "Identity must not be null")
  private String identity;

  @NotNull(message = "CompanyId must not be null")
  private UUID companyId;

  @NotNull(message = "WorkflowTypeId must not be null")
  private UUID workflowTypeId;

  @NotNull(message = "PresetName must not be null")
  private String presetName;

  @NotNull(message = "Status must not be null")
  private Integer status;

  @NotNull(message = "Version must not be null")
  private Integer version;

  @NotNull(message = "CompanyWorkflowtypeItemOcrSettingPresetItemList must not be null")
  private List<CompanyWorkflowtypeItemOcrSettingPresetItemEdo> items = new ArrayList<>();

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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
