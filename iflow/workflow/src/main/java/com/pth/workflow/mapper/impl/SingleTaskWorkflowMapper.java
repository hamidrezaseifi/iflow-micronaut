package com.pth.workflow.mapper.impl;

import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.entities.SingleTaskWorkflowEntity;
import com.pth.workflow.mapper.ISingleTaskWorkflowMapper;
import com.pth.workflow.mapper.IWorkflowMapper;


import javax.inject.Singleton;

@Singleton
public class SingleTaskWorkflowMapper extends ModelEdoMapperBase<SingleTaskWorkflowEntity, SingleTaskWorkflowEdo>
        implements ISingleTaskWorkflowMapper {

    private final IWorkflowMapper workflowMapper;

    public SingleTaskWorkflowMapper(IWorkflowMapper workflowMapper) {
        this.workflowMapper = workflowMapper;

    }

    @Override
    public SingleTaskWorkflowEntity fromEdo(SingleTaskWorkflowEdo edo) {
        final SingleTaskWorkflowEntity model = new SingleTaskWorkflowEntity();

        model.setWorkflow(workflowMapper.fromEdo(edo.getWorkflow()));
        model.setWorkflowId(edo.getWorkflow().getId());

        return model;
    }

    @Override
    public SingleTaskWorkflowEdo toEdo(SingleTaskWorkflowEntity model) {

        final WorkflowEdo workflowEdo = workflowMapper.toEdo(model.getWorkflow());

        final SingleTaskWorkflowEdo edo = new SingleTaskWorkflowEdo();
        edo.setWorkflow(workflowEdo);

        return edo;
    }

}
