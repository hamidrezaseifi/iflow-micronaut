package com.pth.workflow.mapper.impl;

import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowSaveRequestEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.mapper.ISingleTaskWorkflowSaveRequestMapper;
import com.pth.workflow.models.workflow.SingleTaskWorkflowSaveRequest;

import javax.inject.Singleton;

@Singleton
public class SingleTaskWorkflowSaveRequestMapper
        extends ModelEdoMapperBase<SingleTaskWorkflowSaveRequest, SingleTaskWorkflowSaveRequestEdo>
        implements ISingleTaskWorkflowSaveRequestMapper {
    @Override
    public SingleTaskWorkflowSaveRequest fromEdo(SingleTaskWorkflowSaveRequestEdo edo) {
        SingleTaskWorkflowSaveRequest model = MappingUtils.copyProperties(edo, new SingleTaskWorkflowSaveRequest());

        return model;
    }

    @Override
    public SingleTaskWorkflowSaveRequestEdo toEdo(SingleTaskWorkflowSaveRequest model) {
        SingleTaskWorkflowSaveRequestEdo edo = MappingUtils.copyProperties(model, new SingleTaskWorkflowSaveRequestEdo());

        return edo;
    }
}
