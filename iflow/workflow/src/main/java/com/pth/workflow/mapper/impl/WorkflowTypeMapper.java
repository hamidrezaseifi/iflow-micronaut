package com.pth.workflow.mapper.impl;

import com.pth.common.edo.WorkflowTypeEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.mapper.IWorkflowTypeMapper;

import javax.inject.Singleton;

@Singleton
public class WorkflowTypeMapper extends ModelEdoMapperBase<WorkflowTypeEntity, WorkflowTypeEdo>
        implements IWorkflowTypeMapper {
    @Override
    public WorkflowTypeEntity fromEdo(WorkflowTypeEdo edo) {
        WorkflowTypeEntity model = MappingUtils.copyProperties(edo, new WorkflowTypeEntity());

        return model;
    }

    @Override
    public WorkflowTypeEdo toEdo(WorkflowTypeEntity model) {
        WorkflowTypeEdo edo = MappingUtils.copyProperties(model, new WorkflowTypeEdo());

        return edo;
    }
}
