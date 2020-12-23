package com.pth.gui.models.workflow.workflow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.common.edo.enums.EWorkflowStatus;
import com.pth.gui.models.GuiBaseModel;
import com.pth.gui.models.User;
import com.pth.gui.models.workflow.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(value = { "isAssignTo" })
public class Workflow extends GuiBaseModel {

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
  private UUID currentUserId;

  private final List<WorkflowFile> files = new ArrayList<>();
  private final List<WorkflowAction> actions = new ArrayList<>();

  public UUID getWorkflowTypeId() {

    return this.workflowTypeId;
  }

  public void setWorkflowTypeId(UUID workflowTypeId) {
    this.workflowTypeId = workflowTypeId;
  }

  public UUID getCompanyId() {

    return this.companyId;
  }

  public void setCompanyId(final UUID companyId) {

    this.companyId = companyId;
  }

  public WorkflowType getWorkflowType() {

    return this.workflowType;
  }

  /**
   * @param workflowType the workflowType to set
   */

  public void setWorkflowType(final WorkflowType workflowType) {

    this.workflowType = workflowType;
  }

  public WorkflowTypeStep getCurrentStep() {

    return this.currentStep;
  }

  public void setCurrentStep(final WorkflowTypeStep currentStep) {

    this.currentStep = currentStep;
  }

  public UUID getCurrentStepId() {

    return this.currentStepId;
  }

  public void setCurrentStepId(final UUID currentStepId) {

    this.currentStepId = currentStepId;
  }

  public UUID getControllerId() {

    return this.controllerId;
  }

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

  public UUID getCreatedById() {

    return this.createdById;
  }

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

  public void setCreatedByUser(final User createdByUser) {

    this.createdByUser = createdByUser;
  }

  public String getComments() {

    return this.comments;
  }

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

  public int getStatusInt() {

    return this.status.getValue().intValue();
  }

  public List<WorkflowFile> getFiles() {

    return this.files;
  }

  public WorkflowFile getFileById(final UUID fileId) {

    for (final WorkflowFile file : this.files) {
      if (file.getId().equals(fileId)) {
        return file;
      }
    }
    return null;
  }

  public void setFiles(final List<WorkflowFile> files) {

    this.files.clear();
    if (files != null) {
      this.files.addAll(files);
    }
  }

  public void clearFiles() {

    this.files.clear();
  }

  public void addFile(final WorkflowFile file) {

    this.files.add(file);
  }

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

  public List<WorkflowAction> getActions() {

    return this.actions;
  }

  public void setActions(final List<WorkflowAction> actions) {

    this.actions.clear();
    if (actions != null) {
      this.actions.addAll(actions);
    }
  }

  public void addAction(final WorkflowAction action) {

    this.actions.add(action);
  }

  public UUID getCurrentUserId() {

    return this.currentUserId;
  }

  public void setCurrentUserId(final UUID currentUserId) {

    this.currentUserId = currentUserId;
  }

  public boolean isAfterStep(final Workflow other) {

    return this.currentStep.isAfterStep(other.getCurrentStep());
  }

  public boolean isBeforeStep(final Workflow other) {

    return this.currentStep.isBeforeStep(other.getCurrentStep());
  }

  public boolean isTheSameStep(final Workflow other) {

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

  public boolean isInitializing() {

    return (this.getStatus() == EWorkflowStatus.INITIALIZE);
  }

  public boolean isAssigned() {

    return (this.getStatus() == EWorkflowStatus.ASSIGNED);
  }

  public boolean isMeAssigned() {

    return (this.isAssigned() || this.isInitializing())
           && (this.getHasActiveAction() && this.getActiveAction().isAssignTo(this.currentUserId));
  }

  public boolean isLoggedUserController() {
    if(this.controllerId == null || this.currentUserId == null){
      return false;
    }
    return this.controllerId.equals(this.currentUserId);
  }

  public boolean isLoggedUserControllerAndDone() {

    if(this.controllerId == null || this.currentUserId == null){
      return false;
    }
    return this.controllerId.equals(this.currentUserId) && this.getIsDone();
  }

  public boolean isNotAssigned() {

    return this.isAssigned() == false;
  }

  public boolean getHasActiveAction() {

    return this.getActiveAction() != null;
  }

  public WorkflowAction getActiveAction() {

    for (final WorkflowAction action : this.getActions()) {
      if (action.getIsActive() == true) {
        return action;
      }
    }
    return null;
  }

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

  public boolean getCanSave() {

    return (this.getIsLastStep() == false || this.getIsDone() == false) && this.getIsArchived() == false;
  }

  public boolean getCanDone() {

    return this.getIsDone() == false && this.getIsArchived() == false;
  }

  public boolean getCanArchive() {

    return this.getIsLastStep() && this.getIsDone() && this.getIsArchived() == false;
  }

  public boolean getCanAssign() {

    return this.getIsLastStep() == false;
  }

  public boolean getCanEdit() {

    return this.isMeAssigned() || this.isLoggedUserControllerAndDone();
  }
  

  public static Workflow generateInitial(final UUID creatorId, final UUID currentUserId, WorkflowType workflowType) {
    final Workflow newWorkflow = new Workflow();
    newWorkflow.setStatus(EWorkflowStatus.INITIALIZE);
    newWorkflow.setWorkflowType(workflowType);
    newWorkflow.setWorkflowTypeId(workflowType.getId());
    newWorkflow.setCreatedById(creatorId);
    newWorkflow.setControllerId(null);
    newWorkflow.setCurrentStepId(null);
    newWorkflow.setVersion(0);
    newWorkflow.setComments("");
    newWorkflow.setCurrentUserId(currentUserId);

    return newWorkflow;
  }
  
}
