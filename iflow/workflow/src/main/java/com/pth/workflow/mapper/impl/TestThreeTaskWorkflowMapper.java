package com.pth.workflow.mapper.impl;

import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.entities.TestThreeTaskWorkflowEntity;
import com.pth.workflow.mapper.ITestThreeTaskWorkflowMapper;
import com.pth.workflow.mapper.IWorkflowActionMapper;
import com.pth.workflow.mapper.IWorkflowFileMapper;
import com.pth.workflow.mapper.IWorkflowMapper;

import javax.inject.Singleton;

@Singleton
public class TestThreeTaskWorkflowMapper extends ModelEdoMapperBase<TestThreeTaskWorkflowEntity, TestThreeTaskWorkflowEdo>
        implements ITestThreeTaskWorkflowMapper {

    private final IWorkflowMapper workflowMapper;

    public TestThreeTaskWorkflowMapper(IWorkflowMapper workflowMapper) {
        this.workflowMapper = workflowMapper;

    }

    @Override
    public TestThreeTaskWorkflowEntity fromEdo(TestThreeTaskWorkflowEdo edo) {
        final TestThreeTaskWorkflowEntity model = new TestThreeTaskWorkflowEntity();

        model.setWorkflow(workflowMapper.fromEdo(edo.getWorkflow()));
        model.setWorkflowId(edo.getWorkflow().getId());

        return model;
    }

    @Override
    public TestThreeTaskWorkflowEdo toEdo(TestThreeTaskWorkflowEntity model) {

        final WorkflowEdo workflowEdo = workflowMapper.toEdo(model.getWorkflow());

        final TestThreeTaskWorkflowEdo edo = new TestThreeTaskWorkflowEdo();
        edo.setWorkflow(workflowEdo);

        return edo;
    }

}
