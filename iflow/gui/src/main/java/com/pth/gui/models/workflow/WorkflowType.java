package com.pth.gui.models.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.common.edo.enums.EWorkflowTypeAssignType;
import com.pth.gui.models.GuiBaseModel;

public class WorkflowType extends GuiBaseModel {

  private String identity;
  private String title;
  private String comments;
  private Integer status;
  private Boolean sendToController;
  private EWorkflowTypeAssignType assignType;
  private Boolean increaseStepAutomatic;
  private Boolean allowAssign;
  private Integer version;
  private final List<WorkflowTypeStep> steps = new ArrayList<>();

  public WorkflowType() {
    super();
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
    return assignType;
  }

  public void setAssignType(EWorkflowTypeAssignType assignType) {
    this.assignType = assignType;
  }

  @JsonProperty(value = "isAssignTypeManual")
  public boolean geIsAssignTypeManual() {

    return this.assignType == EWorkflowTypeAssignType.MANUAL;
  }

  @JsonProperty(value = "isAssignTypeOffering")
  public boolean geIsAssignTypeOffering() {

    return this.assignType == EWorkflowTypeAssignType.OFFER;
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

  public Integer getVersion() {

    return this.version;
  }

  public void setVersion(final Integer version) {

    this.version = version;
  }

  public List<WorkflowTypeStep> getSteps() {

    return this.steps;
  }

  public WorkflowTypeStep getStepById(final UUID stepId) {

    for (final WorkflowTypeStep step : this.steps) {
      if (step.getId() == stepId) {
        return step;
      }
    }
    return null;
  }

  public void setSteps(final List<WorkflowTypeStep> steps) {

    this.steps.clear();
    if (steps != null) {
      this.steps.addAll(steps);
    }
  }

  public void addStep(final WorkflowTypeStep stepId) {

    this.steps.add(stepId);
  }

  public EWorkflowType getTypeEnum() {

    return EWorkflowType.valueFromIdentity(this.identity);
  }

  public String getControllerPreffix() {

    return this.getTypeEnum().getPagePreffix();

  }

}
