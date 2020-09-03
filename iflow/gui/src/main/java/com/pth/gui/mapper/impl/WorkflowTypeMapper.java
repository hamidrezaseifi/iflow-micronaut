package com.pth.gui.mapper.impl;

import com.pth.common.edo.WorkflowTypeEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.gui.mapper.IWorkflowTypeMapper;
import com.pth.gui.models.workflow.WorkflowType;

import javax.inject.Singleton;

@Singleton
public class WorkflowTypeMapper extends ModelEdoMapperBase<WorkflowType, WorkflowTypeEdo>
        implements IWorkflowTypeMapper {
    @Override
    public WorkflowType fromEdo(WorkflowTypeEdo edo) {
        WorkflowType model = MappingUtils.copyProperties(edo, new WorkflowType());

        return model;
    }

    @Override
    public WorkflowTypeEdo toEdo(WorkflowType model) {
        WorkflowTypeEdo edo = MappingUtils.copyProperties(model, new WorkflowTypeEdo());

        return edo;
    }

}
