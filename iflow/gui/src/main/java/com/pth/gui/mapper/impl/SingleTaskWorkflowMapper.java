package com.pth.gui.mapper.impl;

import com.pth.common.edo.WorkflowMessageEdo;
import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.*;
import com.pth.gui.models.workflow.WorkflowMessage;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;
import com.pth.gui.models.workflow.workflow.Workflow;

import javax.inject.Singleton;

@Singleton
public class SingleTaskWorkflowMapper extends ModelEdoMapperBase<SingleTaskWorkflow, SingleTaskWorkflowEdo>
        implements ISingleTaskWorkflowMapper {

    private final IWorkflowMapper workflowMapper;

    public SingleTaskWorkflowMapper(IWorkflowMapper workflowMapper) {

        this.workflowMapper = workflowMapper;
    }

    @Override
    public SingleTaskWorkflow fromEdo(SingleTaskWorkflowEdo edo) {
        final SingleTaskWorkflow model = new SingleTaskWorkflow();

        Workflow workflow = workflowMapper.fromEdo(edo.getWorkflow());

        model.setWorkflow(workflow);

        return model;
    }

    @Override
    public SingleTaskWorkflowEdo toEdo(SingleTaskWorkflow model) {

        final WorkflowEdo workflowEdo = workflowMapper.toEdo(model.getWorkflow());

        final SingleTaskWorkflowEdo edo = new SingleTaskWorkflowEdo();
        edo.setWorkflow(workflowEdo);

        return edo;
    }

}
