package com.pth.gui.models.workflow;


import com.pth.gui.models.GuiBaseModel;

public class WorkflowTypeStep extends GuiBaseModel {

  private String  identity;
  private String  title;
  private Integer stepIndex;
  private String  viewName;
  private Integer expireDays;
  private String  comments;
  private Integer status;
  private Integer version;

  public WorkflowTypeStep() {
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

  /**
   * @return the stepIndex
   */
  public Integer getStepIndex() {
    return this.stepIndex;
  }

  /**
   * @param stepIndex the stepIndex to set
   */
  public void setStepIndex(final Integer stepIndex) {
    this.stepIndex = stepIndex;
  }

  public String getViewName() {
    return this.viewName;
  }

  public void setViewName(final String viewName) {
    this.viewName = viewName;
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

  public Integer getVersion() {
    return this.version;
  }

  public void setVersion(final Integer version) {
    this.version = version;
  }

  public boolean isAfterStep(final WorkflowTypeStep other) {
    return this.stepIndex > other.getStepIndex();
  }

  public boolean isBeforeStep(final WorkflowTypeStep other) {
    return this.stepIndex < other.getStepIndex();
  }

  public boolean isTheSameStep(final WorkflowTypeStep other) {
    return this.stepIndex == other.getStepIndex();
  }

  public Integer getExpireDays() {
    return this.expireDays;
  }

  public void setExpireDays(final Integer expireDays) {
    this.expireDays = expireDays;
  }

}
