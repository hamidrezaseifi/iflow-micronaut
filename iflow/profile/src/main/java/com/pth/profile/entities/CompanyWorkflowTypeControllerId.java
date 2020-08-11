package com.pth.profile.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class CompanyWorkflowTypeControllerId implements Serializable {

  private static final long serialVersionUID = 3787293332454617877L;

  @Column(name = "company_id")
  private UUID companyId;

  @Column(name = "workflow_type_id")
  private UUID workflowTypeId;

  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "priority")
  private Integer priority;

  public CompanyWorkflowTypeControllerId() {

  }

  public CompanyWorkflowTypeControllerId(final UUID companyId, final UUID workflowTypeId, final UUID userId, final Integer priority) {

    this.companyId = companyId;
    this.workflowTypeId = workflowTypeId;
    this.userId = userId;
    this.priority = priority;
  }

  public UUID getCompanyId() {

    return companyId;
  }

  public void setCompanyId(final UUID companyId) {

    this.companyId = companyId;
  }

  public UUID getWorkflowTypeId() {

    return workflowTypeId;
  }

  public void setWorkflowTypeId(final UUID workflowTypeId) {

    this.workflowTypeId = workflowTypeId;
  }

  public UUID getUserId() {

    return userId;
  }

  public void setUserId(final UUID userId) {

    this.userId = userId;
  }

  public Integer getPriority() {

    return priority;
  }

  public void setPriority(final Integer priority) {

    this.priority = priority;
  }

  @Override
  public boolean equals(final Object o) {

    if (this == o) {
      return true;
    }
    if (!(o instanceof CompanyWorkflowTypeControllerId)) {
      return false;
    }
    final CompanyWorkflowTypeControllerId that = (CompanyWorkflowTypeControllerId) o;
    return Objects.equals(getCompanyId(), that.getCompanyId()) && Objects.equals(getUserId(), that.getUserId())
        && Objects.equals(getWorkflowTypeId(), that.getWorkflowTypeId()) && Objects.equals(getPriority(), that.getPriority());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getCompanyId(), getUserId(), getWorkflowTypeId(), getPriority());
  }

}
