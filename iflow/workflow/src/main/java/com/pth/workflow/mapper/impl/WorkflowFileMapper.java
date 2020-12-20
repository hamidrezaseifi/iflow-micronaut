package com.pth.workflow.mapper.impl;

import com.pth.common.edo.WorkflowFileEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.entities.WorkflowFileEntity;
import com.pth.workflow.mapper.IWorkflowFileMapper;
import com.pth.workflow.mapper.IWorkflowFileVersionMapper;

import javax.inject.Singleton;

@Singleton
public class WorkflowFileMapper extends ModelEdoMapperBase<WorkflowFileEntity, WorkflowFileEdo>
        implements IWorkflowFileMapper {

    private final IWorkflowFileVersionMapper workflowFileVersionMapper;

    public WorkflowFileMapper(IWorkflowFileVersionMapper workflowFileVersionMapper) {
        this.workflowFileVersionMapper = workflowFileVersionMapper;
    }

    @Override
    public WorkflowFileEntity fromEdo(WorkflowFileEdo edo) {
        final WorkflowFileEntity model = new WorkflowFileEntity();

        model.setTitle(edo.getTitle());
        model.setExtention(edo.getExtention());
        model.setComments(edo.getComments());
        model.setStatus(edo.getStatus());
        model.setCreatedByUserId(edo.getCreatedById());
        model.setActiveFilePath(edo.getActiveFilePath());
        model.setActiveFileVersion(edo.getActiveFileVersion());
        model.setIdentity(edo.getIdentity());
        model.setId(edo.getId());

        model.setFileVersions(workflowFileVersionMapper.fromEdoList(edo.getFileVersions()));

        return model;
    }

    @Override
    public WorkflowFileEdo toEdo(WorkflowFileEntity model) {
        final WorkflowFileEdo edo = new WorkflowFileEdo();
        edo.setTitle(model.getTitle());
        edo.setExtention(model.getExtention());
        edo.setComments(model.getComments());
        edo.setStatus(model.getStatus());
        edo.setCreatedById(model.getCreatedByUserId());
        edo.setActiveFilePath(model.getActiveFilePath());
        edo.setActiveFileVersion(model.getActiveFileVersion());
        edo.setIdentity(model.getIdentity());
        edo.setId(model.getId());

        edo.setFileVersions(workflowFileVersionMapper.toEdoList(model.getFileVersions()));

        return edo;
    }

}
