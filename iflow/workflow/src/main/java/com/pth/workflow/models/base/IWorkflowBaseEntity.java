package com.pth.workflow.models.base;

import java.util.List;
import java.util.UUID;

import com.pth.common.edo.enums.EWorkflowActionStatus;
import com.pth.common.edo.enums.EWorkflowStatus;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.workflow.entities.*;

import javax.persistence.Entity;

@Entity
public interface IWorkflowBaseEntity {

  UUID getWorkflowId();

  void setWorkflowId(final UUID workflowId);

  WorkflowEntity getWorkflow();

  void setWorkflow(final WorkflowEntity workflow);

  String getIdentity();

  void setIdentity(final String identity);

  WorkflowTypeEntity getWorkflowType();

  void setWorkflowType(final WorkflowTypeEntity workflowType);

  WorkflowTypeStepEntity getCurrentStep();

  void setCurrentStep(final WorkflowTypeStepEntity currentStep);

  String getComments();

  void setComments(final String comments);

  EWorkflowStatus getStatusEnum();

  Integer getStatusInt();

  void setStatus(final Integer status);

  void setStatus(final EWorkflowStatus status);

  Integer getVersion();

  void setVersion(final Integer version);

  List<WorkflowFileEntity> getFiles();

  void setFiles(final List<WorkflowFileEntity> files);

  List<WorkflowActionEntity> getActions();

  boolean hasAction();

  void setActions(final List<WorkflowActionEntity> actions);

  abstract EWorkflowType getWorkflowTypeEnum();

  UUID getCurrentStepId();

  void setCurrentStepId(final UUID id);

  UUID getControllerId();

  void setControllerId(final UUID id);

  UUID getCreatedById();

  void setCreatedById(final UUID id);

  boolean hasActiveAction();

  WorkflowActionEntity getActiveAction();

  WorkflowActionEntity getLastAction();

  boolean isAssigned();

  void setActiveActionAssignTo(final UUID userId);

  void setActiveActionStatus(final EWorkflowActionStatus status);

  boolean isInitializing();

  boolean isOffering();

  boolean isArchived();

  boolean isAssignedStatus();

  boolean isDone();

  boolean hasController();

  boolean hasCreatedBy();

  boolean hasWorkflowType();

  void addAction(final WorkflowActionEntity action);

  String getWorkflowTypeIdentity();

  boolean isCurrentStepId(UUID currentStepId);

  UUID getCompanyId();

  void setCompanyId(final UUID companyId);
}
