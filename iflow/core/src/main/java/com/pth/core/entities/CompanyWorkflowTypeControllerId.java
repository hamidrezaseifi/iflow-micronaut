package com.pth.core.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompanyWorkflowTypeControllerId implements Serializable {

  private static final long serialVersionUID = 3787293332454617877L;

  @Column(name = "company_id")
  private Long companyId;

  @Column(name = "workflow_type_id")
  private Long workflowTypeId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "priority")
  private Integer priority;

  public CompanyWorkflowTypeControllerId() {

  }

  public CompanyWorkflowTypeControllerId(final Long companyId, final Long workflowTypeId, final Long userId, final Integer priority) {

    this.companyId = companyId;
    this.workflowTypeId = workflowTypeId;
    this.userId = userId;
    this.priority = priority;
  }

  public Long getCompanyId() {

    return companyId;
  }

  public void setCompanyId(final Long companyId) {

    this.companyId = companyId;
  }

  public Long getWorkflowTypeId() {

    return workflowTypeId;
  }

  public void setWorkflowTypeId(final Long workflowTypeId) {

    this.workflowTypeId = workflowTypeId;
  }

  public Long getUserId() {

    return userId;
  }

  public void setUserId(final Long userId) {

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
