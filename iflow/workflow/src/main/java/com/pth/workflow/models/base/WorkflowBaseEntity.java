package com.pth.workflow.models.base;

import com.pth.common.edo.enums.EWorkflowActionStatus;
import com.pth.common.edo.enums.EWorkflowStatus;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.workflow.entities.workflow.*;

import java.util.List;
import java.util.UUID;

public abstract class WorkflowBaseEntity implements IWorkflowBaseEntity {


    @Override
    public UUID getWorkflowId() {
        return getWorkflow().getId();
    }

    public abstract WorkflowEntity getWorkflow();

    public abstract void setWorkflow(WorkflowEntity workflow);

    @Override
    public String getIdentity() {
        return getWorkflow().getIdentity();
    }

    @Override
    public void setIdentity(String identity) {
        getWorkflow().setIdentity(identity);
    }

    @Override
    public WorkflowTypeEntity getWorkflowType() {
        return getWorkflow().getWorkflowType();
    }

    @Override
    public void setWorkflowType(WorkflowTypeEntity workflowType) {
        getWorkflow().setWorkflowType(workflowType);
    }

    @Override
    public WorkflowTypeStepEntity getCurrentStep() {
        return getWorkflow().getCurrentStep();
    }

    @Override
    public void setCurrentStep(WorkflowTypeStepEntity currentStep) {
        getWorkflow().setCurrentStep(currentStep);
    }

    @Override
    public String getComments() {
        return getWorkflow().getComments();
    }

    @Override
    public void setComments(String comments) {
        getWorkflow().setComments(comments);
    }

    @Override
    public EWorkflowStatus getStatus() {
        return EWorkflowStatus.ofValue(getWorkflow().getStatus());
    }

    @Override
    public Integer getStatusInt() {
        return getWorkflow().getStatus();
    }

    @Override
    public void setStatus(Integer status) {
        getWorkflow().setStatus(status);
    }

    @Override
    public void setStatus(EWorkflowStatus status) {
        getWorkflow().setStatus(status.getValue());
    }

    @Override
    public Integer getVersion() {
        return getWorkflow().getVersion();
    }

    @Override
    public void setVersion(Integer version) {
        getWorkflow().setVersion(version);
    }

    @Override
    public List<WorkflowFileEntity> getFiles() {
        return getWorkflow().getFiles();
    }

    @Override
    public void setFiles(List<WorkflowFileEntity> files) {
        getWorkflow().setFiles(files);
    }

    @Override
    public List<WorkflowActionEntity> getActions() {
        return getWorkflow().getActions();
    }

    @Override
    public boolean hasAction() {
        return getWorkflow().hasAction();
    }

    @Override
    public void setActions(List<WorkflowActionEntity> actions) {
        getWorkflow().setActions(actions);
    }

    @Override
    public EWorkflowType getWorkflowTypeEnum() {
        return EWorkflowType.valueFromIdentity(getWorkflow().getWorkflowType().getIdentity()) ;
    }

    @Override
    public UUID getCurrentStepId() {
        return getWorkflow().getCurrentStepId();
    }

    @Override
    public void setCurrentStepId(UUID id) {
        getWorkflow().setCurrentStepId(id);
    }

    @Override
    public UUID getControllerId() {
        return getWorkflow().getControllerId();
    }

    @Override
    public void setControllerId(UUID id) {
        getWorkflow().setControllerId(id);
    }

    @Override
    public UUID getCreatedById() {
        return getWorkflow().getCreatedById();
    }

    @Override
    public void setCreatedById(UUID id) {
        getWorkflow().setCreatedById(id);
    }


    @Override
    public boolean hasActiveAction() {
        return getWorkflow().hasAction();
    }

    @Override
    public WorkflowActionEntity getActiveAction() {
        return getWorkflow().getActiveAction();
    }

    @Override
    public WorkflowActionEntity getLastAction() {
        return getWorkflow().getLastAction();
    }

    @Override
    public boolean isAssigned() {
        return getWorkflow().isAssigned();
    }

    @Override
    public void setActiveActionAssignTo(UUID userId) {
        getWorkflow().setActiveActionAssignTo(userId);
    }

    @Override
    public void setActiveActionStatus(EWorkflowActionStatus status) {
        getWorkflow().setActiveActionStatus(status);
    }

    @Override
    public boolean isInitializing() {

        return EWorkflowStatus.INITIALIZE.equals(getWorkflow().getStatusAsEnum());
    }

    @Override
    public boolean isOffering() {

        return EWorkflowStatus.OFFERING.equals(getWorkflow().getStatusAsEnum());
    }

    @Override
    public boolean isArchived() {

        return EWorkflowStatus.ARCHIVED.equals(getWorkflow().getStatusAsEnum());
    }

    @Override
    public boolean isAssignedStatus() {

        return EWorkflowStatus.ASSIGNED.equals(getWorkflow().getStatusAsEnum());
    }

    @Override
    public boolean isDone() {

        return EWorkflowStatus.DONE.equals(getWorkflow().getStatusAsEnum());
    }


    @Override
    public boolean hasController() {
        return getWorkflow().hasController();
    }

    @Override
    public boolean hasCreatedBy() {
        return getWorkflow().hasCreatedBy();
    }

    @Override
    public boolean hasWorkflowType() {
        return getWorkflow().hasWorkflowType();
    }

    @Override
    public void addAction(WorkflowActionEntity action) {
        getWorkflow().addAction(action);
    }

    @Override
    public String getWorkflowTypeIdentity() {
        return getWorkflow().getWorkflowType().getIdentity();
    }

}
