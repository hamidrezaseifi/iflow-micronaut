package com.pth.workflow.mapper.impl;

import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.mapper.IWorkflowMapper;
import com.pth.workflow.models.WorkflowSearchFilter;

import javax.inject.Singleton;

@Singleton
public class WorkflowMapper extends ModelEdoMapperBase<WorkflowEntity, WorkflowEdo>
        implements IWorkflowMapper {
    @Override
    public WorkflowEntity fromEdo(WorkflowEdo edo) {
        WorkflowEntity model = MappingUtils.copyProperties(edo, new WorkflowEntity());

        return model;
    }

    @Override
    public WorkflowEdo toEdo(WorkflowEntity model) {
        WorkflowEdo edo = MappingUtils.copyProperties(model, new WorkflowEdo());

        return edo;
    }

    @Override
    public WorkflowSearchFilter fromEdo(WorkflowSearchFilterEdo edo) {
        WorkflowSearchFilter model = MappingUtils.copyProperties(edo, new WorkflowSearchFilter());

        return model;
    }
}
