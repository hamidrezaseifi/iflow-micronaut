package com.pth.gui.mapper.impl;

import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.*;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;
import com.pth.gui.models.workflow.testthree.TestThreeTaskWorkflow;
import com.pth.gui.models.workflow.workflow.Workflow;

import javax.inject.Singleton;

@Singleton
public class TestThreeTaskWorkflowMapper extends ModelEdoMapperBase<TestThreeTaskWorkflow, TestThreeTaskWorkflowEdo>
        implements ITestThreeTaskWorkflowMapper {

    private final IWorkflowMapper workflowMapper;

    public TestThreeTaskWorkflowMapper(IWorkflowMapper workflowMapper) {

        this.workflowMapper = workflowMapper;
    }

    @Override
    public TestThreeTaskWorkflow fromEdo(TestThreeTaskWorkflowEdo edo) {
        final TestThreeTaskWorkflow model = new TestThreeTaskWorkflow();

        Workflow workflow = workflowMapper.fromEdo(edo.getWorkflow());

        model.setWorkflow(workflow);

        return model;
    }

    @Override
    public TestThreeTaskWorkflowEdo toEdo(TestThreeTaskWorkflow model) {

        final WorkflowEdo workflowEdo = workflowMapper.toEdo(model.getWorkflow());

        final TestThreeTaskWorkflowEdo edo = new TestThreeTaskWorkflowEdo();
        edo.setWorkflow(workflowEdo);

        return edo;
    }

}
