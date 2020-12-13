package com.pth.common.edo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class CompanyWorkflowTypeControllerEdo {

  @NotNull(message = "Id must not be null")
  private UUID id;

  @NotNull(message = "WorkflowTypeId must not be null")
  private UUID workflowTypeId;

  @NotNull(message = "UserId must not be null")
  private UUID userId;

  @NotNull(message = "Priority must not be null")
  private Integer priority;

  public CompanyWorkflowTypeControllerEdo() {

  }

  public CompanyWorkflowTypeControllerEdo(UUID id, UUID workflowTypeId, UUID userId, Integer priority) {
    this.id = id;
    this.workflowTypeId = workflowTypeId;
    this.userId = userId;
    this.priority = priority;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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

    return this.priority;
  }

  public void setPriority(final Integer priority) {

    this.priority = priority;
  }

}
