package com.pth.gui.models.workflow;

import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.models.User;

import java.util.List;
import java.util.UUID;


public interface IWorkflow {

  EWorkflowType getWorkflowTypeEnum();

  UUID getId();

  void setId(UUID id);

  WorkflowType getWorkflowType();

  void setWorkflowType(final WorkflowType workflowType);

  WorkflowTypeStep getCurrentStep();

  void setCurrentStep(final WorkflowTypeStep currentStep);

  String getComments();

  void setComments(final String comments);

  Integer getVersion();

  void setVersion(final Integer version);

  List<WorkflowFile> getFiles();

  WorkflowFile getFileById(final UUID fileId);

  void setFiles(final List<WorkflowFile> files);

  void clearFiles();

  List<WorkflowAction> getActions();

  void setActions(final List<WorkflowAction> actions);

  UUID getCurrentStepId();

  void setCurrentStepId(final UUID currentStepId);

  UUID getControllerId();

  void setControllerId(final UUID controllerId);

  UUID getCreatedById();

  void setCreatedById(final UUID createdById);

  WorkflowAction getActiveAction();

  boolean isAssigned();

  boolean isMeAssigned();

  boolean isLoggedUserController();

  boolean isLoggedUserControllerAndDone();

  boolean isInitializing();

  void addAction(final WorkflowAction action);

  UUID getWorkflowTypeId();

  UUID getCompanyId();

  void setCompanyId(final UUID companyId);

  boolean getHasActiveAction();

  void setCreatedByUser(User createdByUser);

  void setControllerUser(User controllerUser);

  void setCurrentUserId(UUID currentUserId);

  WorkflowFile addNewFile(String generateSavingFilePathPreffix,
                          UUID userId,
                          String title,
                          String fileExtention,
                          String comments);

  String getIdentity();

  int getStatusInt();

  int getCurrentStepIndex();

  boolean getIsLastStep();

  boolean getCanSave();

  boolean getCanDone();

  boolean getCanArchive();

  boolean getIsDone();

  boolean getCanAssign();

  boolean getCanEdit();

}
