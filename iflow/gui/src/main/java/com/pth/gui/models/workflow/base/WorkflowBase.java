package com.pth.gui.models.workflow.base;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.common.edo.enums.EWorkflowStatus;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.models.User;
import com.pth.gui.models.workflow.*;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflow;

public abstract class WorkflowBase implements IWorkflow {

  private UUID id;
  private String identity;
  private UUID companyId;

  private UUID workflowTypeId;
  private WorkflowType workflowType;
  private WorkflowTypeStep currentStep;
  private UUID currentStepId;
  private UUID controllerId;
  private User controllerUser;
  private UUID createdById;
  private User createdByUser;
  private String comments;
  private EWorkflowStatus status;
  private int version;
  private UUID currentUserId;

  private final List<WorkflowFile> files = new ArrayList<>();
  private final List<WorkflowAction> actions = new ArrayList<>();

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public String getIdentity() {

    return this.identity;
  }

  public void setIdentity(final String identity) {

    this.identity = identity;
  }

  @Override
  public UUID getWorkflowTypeId() {

    return this.workflowTypeId;
  }

  public void setWorkflowTypeId(UUID workflowTypeId) {
    this.workflowTypeId = workflowTypeId;
  }

  @Override
  public UUID getCompanyId() {

    return this.companyId;
  }

  @Override
  public void setCompanyId(final UUID companyId) {

    this.companyId = companyId;
  }

  @Override
  public WorkflowType getWorkflowType() {

    return this.workflowType;
  }

  /**
   * @param workflowType the workflowType to set
   */

  @Override
  public void setWorkflowType(final WorkflowType workflowType) {

    this.workflowType = workflowType;
  }

  @Override
  public WorkflowTypeStep getCurrentStep() {

    return this.currentStep;
  }

  @Override
  public void setCurrentStep(final WorkflowTypeStep currentStep) {

    this.currentStep = currentStep;
  }

  @Override
  public UUID getCurrentStepId() {

    return this.currentStepId;
  }

  @Override
  public void setCurrentStepId(final UUID currentStepId) {

    this.currentStepId = currentStepId;
  }

  @Override
  public UUID getControllerId() {

    return this.controllerId;
  }

  @Override
  public void setControllerId(final UUID controllerId) {

    this.controllerId = controllerId;
  }

  /**
   * @return the controllerUser
   */
  public User getControllerUser() {

    return this.controllerUser;
  }

  /**
   * @param controllerUser the controllerUser to set
   */

  public void setControllerUser(final User controllerUser) {

    this.controllerUser = controllerUser;
  }

  @Override
  public UUID getCreatedById() {

    return this.createdById;
  }

  @Override
  public void setCreatedById(final UUID createdById) {

    this.createdById = createdById;
  }

  /**
   * @return the createdByUser
   */
  public User getCreatedByUser() {

    return this.createdByUser;
  }

  /**
   * @param createdByUser the createdByUser to set
   */

  @Override
  public void setCreatedByUser(final User createdByUser) {

    this.createdByUser = createdByUser;
  }

  @Override
  public String getComments() {

    return this.comments;
  }

  @Override
  public void setComments(final String comments) {

    this.comments = comments;
  }

  public EWorkflowStatus getStatus() {

    return this.status;
  }

  @JsonSetter
  public void setStatus(final EWorkflowStatus status) {

    this.status = status;
  }

  public void setStatus(final int status) {

    this.status = EWorkflowStatus.ofValue(status);
  }

  public void setStatusInt(final int status) {

    this.status = EWorkflowStatus.ofValue(status);
  }

  @Override
  public int getStatusInt() {

    return this.status.getValue().intValue();
  }

  @Override
  public int getVersion() {

    return this.version;
  }

  @Override
  public void setVersion(final int version) {

    this.version = version;
  }

  @Override
  public List<WorkflowFile> getFiles() {

    return this.files;
  }

  @Override
  public WorkflowFile getFileById(final UUID fileId) {

    for (final WorkflowFile file : this.files) {
      if (file.getId().equals(fileId)) {
        return file;
      }
    }
    return null;
  }

  @Override
  public void setFiles(final List<WorkflowFile> files) {

    this.files.clear();
    if (files != null) {
      this.files.addAll(files);
    }
  }

  @Override
  public void clearFiles() {

    this.files.clear();
  }

  public void addFile(final WorkflowFile file) {

    this.files.add(file);
  }

  @Override
  public WorkflowFile addNewFile(final String path, final UUID userId, final String title, final String extention,
      final String comments) {

    final WorkflowFile wfile = new WorkflowFile();
    wfile.setActiveFilePath(path);
    wfile.setActiveFileVersion(1);
    wfile.setComments(comments);
    wfile.setCreatedById(userId);
    wfile.setExtention(extention);

    wfile.setStatus(1);
    wfile.setTitle(title);

    wfile.addNewFileVersion(path, 1, userId, comments);

    this.addFile(wfile);

    return wfile;
  }

  @Override
  public List<WorkflowAction> getActions() {

    return this.actions;
  }

  @Override
  public void setActions(final List<WorkflowAction> actions) {

    this.actions.clear();
    if (actions != null) {
      this.actions.addAll(actions);
    }
  }

  @Override
  public void addAction(final WorkflowAction action) {

    this.actions.add(action);
  }

  public UUID getCurrentUserId() {

    return this.currentUserId;
  }

  @Override
  public void setCurrentUserId(final UUID currentUserId) {

    this.currentUserId = currentUserId;
  }

  public boolean isAfterStep(final InvoiceWorkflow other) {

    return this.currentStep.isAfterStep(other.getCurrentStep());
  }

  public boolean isBeforeStep(final InvoiceWorkflow other) {

    return this.currentStep.isBeforeStep(other.getCurrentStep());
  }

  public boolean isTheSameStep(final InvoiceWorkflow other) {

    return this.currentStep.isTheSameStep(other.getCurrentStep());
  }

  public boolean isAfter(final WorkflowTypeStep step) {

    return this.currentStep.isAfterStep(step);
  }

  public boolean isBefore(final WorkflowTypeStep step) {

    return this.currentStep.isBeforeStep(step);
  }

  public boolean isTheSame(final WorkflowTypeStep step) {

    return this.currentStep.isTheSameStep(step);
  }

  @Override
  public boolean isInitializing() {

    return (this.getStatus() == EWorkflowStatus.INITIALIZE);
  }

  @Override
  public boolean isAssigned() {

    return (this.getStatus() == EWorkflowStatus.ASSIGNED);
  }

  @Override
  public boolean isMeAssigned() {

    return (this.isAssigned() || this.isInitializing())
        && (this.getHasActiveAction() && this.getActiveAction().isAssignTo(this.currentUserId));
  }

  @Override
  public boolean isLoggedUserController() {
    if(this.controllerId == null || this.currentUserId == null){
      return false;
    }
    return this.controllerId.equals(this.currentUserId);
  }

  @Override
  public boolean isLoggedUserControllerAndDone() {

    if(this.controllerId == null || this.currentUserId == null){
      return false;
    }
    return this.controllerId.equals(this.currentUserId) && this.getIsDone();
  }

  public boolean isNotAssigned() {

    return this.isAssigned() == false;
  }

  @Override
  public boolean getHasActiveAction() {

    return this.getActiveAction() != null;
  }

  @Override
  public WorkflowAction getActiveAction() {

    for (final WorkflowAction action : this.getActions()) {
      if (action.getIsActive() == true) {
        return action;
      }
    }
    return null;
  }

  @Override
  public boolean getIsDone() {

    return this.status == EWorkflowStatus.DONE;
  }

  public boolean getIsArchived() {

    return this.status == EWorkflowStatus.ARCHIVED;
  }

  public boolean getIsError() {

    return this.status == EWorkflowStatus.ERROR;
  }

  public boolean getIsOpen() {

    return (this.status == EWorkflowStatus.ASSIGNED);
  }

  public String getAssignToUserFullName() {

    if (this.getHasActiveAction()) {
      return this.getActiveAction().getAssignToUserName();
    }
    return "";
  }

  @Override
  public int getCurrentStepIndex() {

    if (this.currentStep != null) {
      return this.currentStep.getStepIndex();
    }

    if (this.getHasActiveAction()) {
      return this.getActiveAction().getCurrentStep().getStepIndex();
    }

    if (this.workflowType != null) {
      return this.workflowType.getSteps().get(0).getStepIndex();
    }

    return 0;
  }

  @Override
  public boolean getIsLastStep() {

    if (this.workflowType != null) {
      return this.getLastStep().getStepIndex() == this.getCurrentStepIndex();
    }
    return false;
  }

  private WorkflowTypeStep getLastStep() {

    if (this.workflowType != null) {
      return this.workflowType.getSteps().get(this.workflowType.getSteps().size() - 1);
    }
    return null;
  }

  @Override
  public boolean getCanSave() {

    return (this.getIsLastStep() == false || this.getIsDone() == false) && this.getIsArchived() == false;
  }

  @Override
  public boolean getCanDone() {

    return this.getIsDone() == false && this.getIsArchived() == false;
  }

  @Override
  public boolean getCanArchive() {

    return this.getIsLastStep() && this.getIsDone() && this.getIsArchived() == false;
  }

  @Override
  public boolean getCanAssign() {

    return this.getIsLastStep() == false;
  }

  @Override
  public boolean getCanEdit() {

    return this.isMeAssigned() || this.isLoggedUserControllerAndDone();
  }

}
