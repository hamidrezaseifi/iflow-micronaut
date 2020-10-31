package com.pth.gui.mapper.impl;

import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.IWorkflowActionMapper;
import com.pth.gui.mapper.IWorkflowFileMapper;
import com.pth.gui.mapper.IWorkflowMapper;
import com.pth.gui.models.workflow.workflow.Workflow;

import javax.inject.Singleton;

@Singleton
public class WorkflowMapper extends ModelEdoMapperBase<Workflow, WorkflowEdo>
        implements IWorkflowMapper {

    private final IWorkflowFileMapper workflowFileMapper;
    private final IWorkflowActionMapper workflowActionMapper;

    public WorkflowMapper(IWorkflowFileMapper workflowFileMapper,
                          IWorkflowActionMapper workflowActionMapper) {
        this.workflowFileMapper = workflowFileMapper;
        this.workflowActionMapper = workflowActionMapper;
    }

    @Override
    public Workflow fromEdo(WorkflowEdo edo) {
        final Workflow model = new Workflow();

        model.setComments(edo.getComments());
        model.setStatus(edo.getStatus());
        model.setVersion(edo.getVersion());
        model.setControllerId(edo.getControllerId());
        model.setCurrentStepId(edo.getCurrentStepId());
        model.setCreatedById(edo.getCreatedById());
        model.setId(edo.getId());
        model.setWorkflowTypeId(edo.getWorkflowTypeId());
        model.setCompanyId(edo.getCompanyId());
        model.setId(edo.getId());

        model.setFiles(workflowFileMapper.fromEdoList(edo.getFiles()));
        model.setActions(workflowActionMapper.fromEdoList(edo.getActions()));

        return model;
    }

    @Override
    public WorkflowEdo toEdo(Workflow model) {
        final WorkflowEdo edo = new WorkflowEdo();
        edo.setComments(model.getComments());
        edo.setStatus(model.getStatusInt());
        edo.setControllerId(model.getControllerId());
        edo.setCurrentStepId(model.getCurrentStepId());
        edo.setCreatedById(model.getCreatedById());
        edo.setVersion(model.getVersion());
        edo.setId(model.getId());
        edo.setWorkflowTypeId(model.getWorkflowTypeId());
        edo.setCompanyId(model.getCompanyId());
        edo.setId(model.getId());

        edo.setFiles(workflowFileMapper.toEdoList(model.getFiles()));
        edo.setActions(workflowActionMapper.toEdoList(model.getActions()));

        return edo;
    }

}
