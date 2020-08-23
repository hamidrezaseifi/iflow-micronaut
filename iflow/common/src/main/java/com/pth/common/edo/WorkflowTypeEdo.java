package com.pth.common.edo;

import java.util.*;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.common.edo.enums.EWorkflowTypeAssignType;

public class WorkflowTypeEdo {

  protected UUID id;

  private String identity;

  @NotNull(message = "Title must not be null")
  private String title;

  private String comments;

  @NotNull(message = "Status must not be null")
  private Integer status;

  @NotNull(message = "Version must not be null")
  private Integer version;

  @NotNull(message = "SendToController must not be null")
  private Boolean sendToController;

  @NotNull(message = "AssignType must not be null")
  private EWorkflowTypeAssignType assignType;

  @NotNull(message = "IncreaseStepAutomatic must not be null")
  private Boolean increaseStepAutomatic;

  @NotNull(message = "AllowAssign must not be null")
  private Boolean allowAssign;

  @NotNull(message = "WorkflowTypeStepList must not be null")
  private final Set<WorkflowTypeStepEdo> steps = new HashSet<>();

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

  public String getTitle() {

    return this.title;
  }

  public void setTitle(final String title) {

    this.title = title;
  }

  public String getComments() {

    return this.comments;
  }

  public void setComments(final String comments) {

    this.comments = comments;
  }

  public Integer getStatus() {

    return this.status;
  }

  public Integer getVersion() {

    return this.version;
  }

  public void setVersion(final Integer version) {

    this.version = version;
  }

  public void setStatus(final Integer status) {

    this.status = status;
  }

  public Boolean getSendToController() {

    return this.sendToController;
  }

  public void setSendToController(final Boolean sendToController) {

    this.sendToController = sendToController;
  }

  public EWorkflowTypeAssignType getAssignType() {

    return this.assignType;
  }

  public void setAssignType(final EWorkflowTypeAssignType assignType) {

    this.assignType = assignType;
  }

  public Boolean getIncreaseStepAutomatic() {

    return this.increaseStepAutomatic;
  }

  public void setIncreaseStepAutomatic(final Boolean increaseStepAutomatic) {

    this.increaseStepAutomatic = increaseStepAutomatic;
  }

  public Boolean getAllowAssign() {

    return this.allowAssign;
  }

  public void setAllowAssign(final Boolean allowAssign) {

    this.allowAssign = allowAssign;
  }

  public Set<WorkflowTypeStepEdo> getSteps() {

    return this.steps;
  }

  @JsonSetter
  public void setSteps(final Set<WorkflowTypeStepEdo> steps) {

    this.steps.clear();
    if (steps != null) {
      this.steps.addAll(steps);
    }
  }

  public void addStep(final WorkflowTypeStepEdo stepId) {

    this.steps.add(stepId);
  }
}
