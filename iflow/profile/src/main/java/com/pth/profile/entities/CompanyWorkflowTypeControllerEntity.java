package com.pth.profile.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "company_workflowtype_controller")
public class CompanyWorkflowTypeControllerEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @Column(name = "company_id")
  private UUID companyId;

  @Column(name = "workflow_type_id")
  private UUID workflowTypeId;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "priority")
  private Integer priority;

  @CreationTimestamp
  @Column(name = "created_at", insertable = false, updatable = false)
  private Date createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "company_id", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private CompanyEntity company;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  @Fetch(FetchMode.JOIN)
  private UserEntity user;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public void setCompany(CompanyEntity company) {
    this.company = company;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public Date getCreatedAt() {

    return createdAt;
  }

  public void setCreatedAt(final Date createdAt) {

    this.createdAt = createdAt;
  }

  public CompanyEntity getCompany() {

    return company;
  }

  public UserEntity getUser() {

    return user;
  }


}
