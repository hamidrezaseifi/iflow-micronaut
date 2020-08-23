package com.pth.workflow.mapper.impl;

import com.pth.common.edo.WorkflowTypeStepEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.entities.WorkflowTypeStepEntity;
import com.pth.workflow.mapper.IWorkflowTypeStepMapper;

import javax.inject.Singleton;

@Singleton
public class WorkflowTypeStepMapper extends ModelEdoMapperBase<WorkflowTypeStepEntity, WorkflowTypeStepEdo>
        implements IWorkflowTypeStepMapper {
    @Override
    public WorkflowTypeStepEntity fromEdo(WorkflowTypeStepEdo edo) {
        WorkflowTypeStepEntity model = MappingUtils.copyProperties(edo, new WorkflowTypeStepEntity());

        return model;
    }

    @Override
    public WorkflowTypeStepEdo toEdo(WorkflowTypeStepEntity model) {
        WorkflowTypeStepEdo edo = MappingUtils.copyProperties(model, new WorkflowTypeStepEdo());

        return edo;
    }
}
