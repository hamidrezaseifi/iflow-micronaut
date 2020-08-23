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

  public UUID getWorkflowId();

  public void setWorkflowId(final UUID workflowId);

  public WorkflowEntity getWorkflow();

  public void setWorkflow(final WorkflowEntity workflow);

  public String getIdentity();

  public void setIdentity(final String identity);

  public WorkflowTypeEntity getWorkflowType();

  public void setWorkflowType(final WorkflowTypeEntity workflowType);

  public WorkflowTypeStepEntity getCurrentStep();

  public void setCurrentStep(final WorkflowTypeStepEntity currentStep);

  public String getComments();

  public void setComments(final String comments);

  public EWorkflowStatus getStatusEnum();

  public Integer getStatusInt();

  public void setStatus(final Integer status);

  public void setStatus(final EWorkflowStatus status);

  public Integer getVersion();

  public void setVersion(final Integer version);

  public List<WorkflowFileEntity> getFiles();

  public void setFiles(final List<WorkflowFileEntity> files);

  public List<WorkflowActionEntity> getActions();

  public boolean hasAction();

  public void setActions(final List<WorkflowActionEntity> actions);

  public abstract EWorkflowType getWorkflowTypeEnum();

  public UUID getCurrentStepId();

  public void setCurrentStepId(final UUID id);

  public UUID getControllerId();

  public void setControllerId(final UUID id);

  public UUID getCreatedById();

  public void setCreatedById(final UUID id);

  public boolean hasActiveAction();

  public WorkflowActionEntity getActiveAction();

  public WorkflowActionEntity getLastAction();

  public boolean isAssigned();

  public void setActiveActionAssignTo(final UUID userId);

  public void setActiveActionStatus(final EWorkflowActionStatus status);

  public boolean isInitializing();

  public boolean isOffering();

  public boolean isArchived();

  public boolean isAssignedStatus();

  public boolean isDone();

  public boolean hasController();

  public boolean hasCreatedBy();

  public boolean hasWorkflowType();

  public void addAction(final WorkflowActionEntity action);

  public String getWorkflowTypeIdentity();

  boolean isCurrentStepId(UUID currentStepId);

  UUID getCompanyId();
}
