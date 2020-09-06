package com.pth.gui.mapper.impl;

import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.ISingleTaskWorkflowMapper;
import com.pth.gui.mapper.ITestThreeTaskWorkflowMapper;
import com.pth.gui.mapper.IWorkflowActionMapper;
import com.pth.gui.mapper.IWorkflowFileMapper;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;
import com.pth.gui.models.workflow.testthree.TestThreeTaskWorkflow;

import javax.inject.Singleton;

@Singleton
public class TestThreeTaskWorkflowMapper extends ModelEdoMapperBase<TestThreeTaskWorkflow, TestThreeTaskWorkflowEdo>
        implements ITestThreeTaskWorkflowMapper {

    private final IWorkflowFileMapper workflowFileMapper;
    private final IWorkflowActionMapper workflowActionMapper;

    public TestThreeTaskWorkflowMapper(IWorkflowFileMapper workflowFileMapper,
                                       IWorkflowActionMapper workflowActionMapper) {
        this.workflowFileMapper = workflowFileMapper;
        this.workflowActionMapper = workflowActionMapper;
    }

    @Override
    public TestThreeTaskWorkflow fromEdo(TestThreeTaskWorkflowEdo edo) {
        final TestThreeTaskWorkflow model = new TestThreeTaskWorkflow();

        model.setComments(edo.getWorkflow().getComments());
        model.setStatus(edo.getWorkflow().getStatus());
        model.setVersion(edo.getWorkflow().getVersion());
        model.setControllerId(edo.getWorkflow().getControllerId());
        model.setCurrentStepId(edo.getWorkflow().getCurrentStepId());
        model.setCreatedById(edo.getWorkflow().getCreatedById());
        model.setIdentity(edo.getWorkflow().getIdentity());
        model.setCompanyId(edo.getWorkflow().getCompanyId());

        model.setFiles(workflowFileMapper.fromEdoList(edo.getWorkflow().getFiles()));
        model.setActions(workflowActionMapper.fromEdoList(edo.getWorkflow().getActions()));

        return model;
    }

    @Override
    public TestThreeTaskWorkflowEdo toEdo(TestThreeTaskWorkflow model) {

        final WorkflowEdo workflowEdo = new WorkflowEdo();
        workflowEdo.setComments(model.getComments());
        workflowEdo.setStatus(model.getStatusInt());
        workflowEdo.setControllerId(model.getControllerId());
        workflowEdo.setCurrentStepId(model.getCurrentStepId());
        workflowEdo.setCreatedById(model.getCreatedById());
        workflowEdo.setVersion(model.getVersion());
        workflowEdo.setId(model.getId());
        workflowEdo.setWorkflowTypeId(model.getWorkflowTypeId());
        workflowEdo.setCompanyId(model.getCompanyId());

        workflowEdo.setFiles(workflowFileMapper.toEdoList(model.getFiles()));
        workflowEdo.setActions(workflowActionMapper.toEdoList(model.getActions()));

        final TestThreeTaskWorkflowEdo edo = new TestThreeTaskWorkflowEdo();
        edo.setWorkflow(workflowEdo);

        return edo;
    }

}
