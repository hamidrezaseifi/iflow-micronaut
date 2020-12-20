package com.pth.workflow.mapper.impl;

import com.pth.common.edo.WorkflowTypeStepEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.entities.WorkflowTypeStepEntity;
import com.pth.workflow.mapper.IWorkflowTypeStepMapper;

import javax.inject.Singleton;

@Singleton
public class WorkflowTypeStepMapper extends ModelEdoMapperBase<WorkflowTypeStepEntity, WorkflowTypeStepEdo>
        implements IWorkflowTypeStepMapper {
    @Override
    public WorkflowTypeStepEntity fromEdo(WorkflowTypeStepEdo edo) {
        final WorkflowTypeStepEntity model = new WorkflowTypeStepEntity();

        model.setStepIndex(edo.getStepIndex());
        model.setViewName(edo.getViewName());
        model.setTitle(edo.getTitle());
        model.setComments(edo.getComments());
        model.setExpireDays(edo.getExpireDays());
        model.setStatus(edo.getStatus());
        model.setVersion(edo.getVersion());
        model.setIdentity(edo.getIdentity());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public WorkflowTypeStepEdo toEdo(WorkflowTypeStepEntity model) {
        final WorkflowTypeStepEdo edo = new WorkflowTypeStepEdo();
        edo.setStepIndex(model.getStepIndex());
        edo.setViewName(model.getViewName());
        edo.setTitle(model.getTitle());
        edo.setComments(model.getComments());
        edo.setExpireDays(model.getExpireDays());
        edo.setStatus(model.getStatus());
        edo.setIdentity(model.getIdentity());
        edo.setVersion(model.getVersion());
        edo.setId(model.getId());

        return edo;
    }

}
