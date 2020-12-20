package com.pth.workflow.mapper.impl;

import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.mapper.IWorkflowActionMapper;
import com.pth.workflow.mapper.IWorkflowFileMapper;
import com.pth.workflow.mapper.IWorkflowMapper;

import javax.inject.Singleton;

@Singleton
public class WorkflowMapper extends ModelEdoMapperBase<WorkflowEntity, WorkflowEdo>
        implements IWorkflowMapper {

    private final IWorkflowFileMapper workflowFileMapper;
    private final IWorkflowActionMapper workflowActionMapper;

    public WorkflowMapper(IWorkflowFileMapper workflowFileMapper,
                          IWorkflowActionMapper workflowActionMapper) {
        this.workflowFileMapper = workflowFileMapper;
        this.workflowActionMapper = workflowActionMapper;
    }

    @Override
    public WorkflowEntity fromEdo(WorkflowEdo edo) {
        final WorkflowEntity model = new WorkflowEntity();

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
    public WorkflowEdo toEdo(WorkflowEntity model) {
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
