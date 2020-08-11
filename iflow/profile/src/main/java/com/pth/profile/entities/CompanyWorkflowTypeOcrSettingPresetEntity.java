package com.pth.profile.entities;

import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "company_workflowtype_items_ocr_preset")
public class CompanyWorkflowTypeOcrSettingPresetEntity extends BaseEntity {

  private static final long serialVersionUID = 2937568589389217869L;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "company_id", nullable = false)
  private CompanyEntity company;

  @Column(name = "workflow_type_id")
  private UUID workflowTypeId;

  @Column(name = "identity")
  private String identity;

  @Column(name = "preset_name")
  private String presetName;

  @Column(name = "status")
  private Integer status;

  @Column(name = "version")
  private Integer version;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", insertable = false, updatable = false)
  private Date updatedAt;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "preset", fetch = FetchType.EAGER)
  @Fetch(value = FetchMode.SUBSELECT)
  private final List<CompanyWorkflowTypeOcrSettingPresetItemEntity> items = new ArrayList<>();

  public CompanyEntity getCompany() {

    return company;
  }

  public void setCompany(final CompanyEntity company) {

    this.company = company;
  }

  public UUID getWorkflowTypeId() {
    return workflowTypeId;
  }

  public void setWorkflowTypeId(UUID workflowTypeId) {
    this.workflowTypeId = workflowTypeId;
  }

  public String getPresetName() {

    return presetName;
  }

  public void setPresetName(final String presetName) {

    this.presetName = presetName;
  }

  public Date getUpdatedAt() {

    return updatedAt;
  }

  public void setUpdatedAt(final Date updatedAt) {

    this.updatedAt = updatedAt;
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

  /**
   * @return the version
   */

  @Override
  public Integer getVersion() {

    return this.version;
  }

  /**
   * @param version the version to set
   */

  @Override
  public void setVersion(final Integer version) {

    this.version = version;
  }

  /**
   * @return the createdAt
   */
  public Date getCreatedAt() {

    return this.createdAt;
  }

  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(final Date createdAt) {

    this.createdAt = createdAt;
  }

  public List<CompanyWorkflowTypeOcrSettingPresetItemEntity> getItems() {

    return items;
  }

  public void setItems(final List<CompanyWorkflowTypeOcrSettingPresetItemEntity> items) {

    this.items.clear();
    if (items != null) {
      for (final CompanyWorkflowTypeOcrSettingPresetItemEntity item : items) {
        item.setPreset(this);
        this.items.add(item);
      }

    }

  }

  public void clearItems() {

    this.items.clear();

  }

  public void increaseVersion() {

    version += 1;
  }

  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  public String getIdentityPreffix() {

    return "cwtosp";
  }
}
