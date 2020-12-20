package com.pth.workflow.mapper.impl;

import com.pth.common.edo.WorkflowActionEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.entities.WorkflowActionEntity;
import com.pth.workflow.mapper.IWorkflowActionMapper;

import javax.inject.Singleton;

@Singleton
public class WorkflowActionMapper extends ModelEdoMapperBase<WorkflowActionEntity, WorkflowActionEdo>
        implements IWorkflowActionMapper {

    @Override
    public WorkflowActionEntity fromEdo(WorkflowActionEdo edo) {
        final WorkflowActionEntity model = new WorkflowActionEntity();

        model.setComments(edo.getComments());
        model.setStatus(edo.getStatus());
        model.setCurrentStepId(edo.getCurrentStepId());
        model.setAssignToId(edo.getAssignToId());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public WorkflowActionEdo toEdo(WorkflowActionEntity model) {
        final WorkflowActionEdo edo = new WorkflowActionEdo();
        edo.setComments(model.getComments());
        edo.setStatus(model.getStatus());
        edo.setCurrentStepId(model.getCurrentStepId());
        edo.setAssignToId(model.getAssignToId());
        edo.setId(model.getId());

        return edo;
    }

}
