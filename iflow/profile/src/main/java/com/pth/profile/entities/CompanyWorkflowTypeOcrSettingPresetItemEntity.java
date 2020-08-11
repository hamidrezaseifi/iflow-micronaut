package com.pth.profile.entities;

import com.pth.common.entities.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "company_workflowtype_items_ocr_preset_items")
public class CompanyWorkflowTypeOcrSettingPresetItemEntity extends BaseEntity {

  private static final long serialVersionUID = 2937568589389217869L;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "preset_id", nullable = false)
  private CompanyWorkflowTypeOcrSettingPresetEntity preset;

  @Column(name = "property_name")
  private String propertyName;

  @Column(name = "value")
  private String value;

  @Column(name = "ocr_type")
  private Integer ocrType;

  @Column(name = "status")
  private Integer status;

  @Column(name = "version")
  private Integer version;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  public CompanyWorkflowTypeOcrSettingPresetEntity getPreset() {

    return preset;
  }

  public void setPreset(final CompanyWorkflowTypeOcrSettingPresetEntity preset) {

    this.preset = preset;
  }

  public String getPropertyName() {

    return propertyName;
  }

  public void setPropertyName(final String propertyName) {

    this.propertyName = propertyName;
  }

  public String getValue() {

    return value;
  }

  public void setValue(final String value) {

    this.value = value;
  }

  public Integer getOcrType() {

    return ocrType;
  }

  public void setOcrType(final Integer ocrType) {

    this.ocrType = ocrType;
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

  public void increaseVersion() {

    version += 1;
  }
}
