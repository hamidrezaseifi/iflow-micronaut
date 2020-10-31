package com.pth.gui.mapper.impl;

import com.pth.common.edo.WorkflowTypeEdo;
import com.pth.common.edo.WorkflowTypeStepEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.gui.mapper.IWorkflowTypeMapper;
import com.pth.gui.mapper.IWorkflowTypeStepMapper;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.models.workflow.WorkflowTypeStep;

import javax.inject.Singleton;

@Singleton
public class WorkflowTypeStepMapper extends ModelEdoMapperBase<WorkflowTypeStep, WorkflowTypeStepEdo>
        implements IWorkflowTypeStepMapper {
    @Override
    public WorkflowTypeStep fromEdo(WorkflowTypeStepEdo edo) {
        final WorkflowTypeStep model = new WorkflowTypeStep();

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
    public WorkflowTypeStepEdo toEdo(WorkflowTypeStep model) {
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
