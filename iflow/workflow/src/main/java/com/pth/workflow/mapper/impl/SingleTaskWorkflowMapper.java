package com.pth.workflow.mapper.impl;

import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.entities.SingleTaskWorkflowEntity;
import com.pth.workflow.mapper.ISingleTaskWorkflowMapper;

import javax.inject.Singleton;

@Singleton
public class SingleTaskWorkflowMapper extends ModelEdoMapperBase<SingleTaskWorkflowEntity, SingleTaskWorkflowEdo>
        implements ISingleTaskWorkflowMapper {
    @Override
    public SingleTaskWorkflowEntity fromEdo(SingleTaskWorkflowEdo edo) {
        SingleTaskWorkflowEntity model = MappingUtils.copyProperties(edo, new SingleTaskWorkflowEntity());

        return model;
    }

    @Override
    public SingleTaskWorkflowEdo toEdo(SingleTaskWorkflowEntity model) {
        SingleTaskWorkflowEdo edo = MappingUtils.copyProperties(model, new SingleTaskWorkflowEdo());

        return edo;
    }
}
