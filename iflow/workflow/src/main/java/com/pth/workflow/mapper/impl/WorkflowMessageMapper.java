package com.pth.workflow.mapper.impl;

import com.pth.common.edo.WorkflowMessageEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.entities.workflow.WorkflowMessageEntity;
import com.pth.workflow.mapper.IWorkflowMessageMapper;

import javax.inject.Singleton;

@Singleton
public class WorkflowMessageMapper extends ModelEdoMapperBase<WorkflowMessageEntity, WorkflowMessageEdo>
        implements IWorkflowMessageMapper {
    @Override
    public WorkflowMessageEntity fromEdo(WorkflowMessageEdo edo) {
        WorkflowMessageEntity model = MappingUtils.copyProperties(edo, new WorkflowMessageEntity());

        return model;
    }

    @Override
    public WorkflowMessageEdo toEdo(WorkflowMessageEntity model) {
        WorkflowMessageEdo edo = MappingUtils.copyProperties(model, new WorkflowMessageEdo());

        return edo;
    }
}
