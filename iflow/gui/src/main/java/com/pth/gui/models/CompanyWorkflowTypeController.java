package com.pth.gui.models;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CompanyWorkflowTypeController {

  private Long id;

   private UUID workflowTypeId;

  private UUID userId;

  private Integer priority;

  public CompanyWorkflowTypeController() {

  }

  public CompanyWorkflowTypeController(Long id,
                                       UUID workflowTypeId,
                                       UUID userId,
                                       Integer priority) {
    this.id = id;
    this.workflowTypeId = workflowTypeId;
    this.userId = userId;
    this.priority = priority;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
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
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }
}
