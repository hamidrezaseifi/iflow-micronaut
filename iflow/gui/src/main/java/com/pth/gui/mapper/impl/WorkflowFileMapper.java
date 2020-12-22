package com.pth.gui.mapper.impl;

import com.pth.common.edo.WorkflowFileEdo;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.IWorkflowFileMapper;
import com.pth.gui.mapper.IWorkflowFileVersionMapper;
import com.pth.gui.mapper.IWorkflowMapper;
import com.pth.gui.models.workflow.WorkflowFile;
import com.pth.gui.models.workflow.workflow.Workflow;

import javax.inject.Singleton;

@Singleton
public class WorkflowFileMapper extends ModelEdoMapperBase<WorkflowFile, WorkflowFileEdo>
        implements IWorkflowFileMapper {

    private final IWorkflowFileVersionMapper workflowFileVersionMapper;

    public WorkflowFileMapper(IWorkflowFileVersionMapper workflowFileVersionMapper) {
        this.workflowFileVersionMapper = workflowFileVersionMapper;
    }

    @Override
    public WorkflowFile fromEdo(WorkflowFileEdo edo) {
        final WorkflowFile model = new WorkflowFile();

        model.setTitle(edo.getTitle());
        model.setExtention(edo.getExtention());
        model.setComments(edo.getComments());
        model.setStatus(edo.getStatus());
        model.setCreatedById(edo.getCreatedById());
        model.setActiveFilePath(edo.getActiveFilePath());
        model.setActiveFileVersion(edo.getActiveFileVersion());
        model.setId(edo.getId());

        model.setFileVersions(workflowFileVersionMapper.fromEdoList(edo.getFileVersions()));

        return model;
    }

    @Override
    public WorkflowFileEdo toEdo(WorkflowFile model) {
        final WorkflowFileEdo edo = new WorkflowFileEdo();
        edo.setTitle(model.getTitle());
        edo.setExtention(model.getExtention());
        edo.setComments(model.getComments());
        edo.setStatus(model.getStatus());
        edo.setCreatedById(model.getCreatedById());
        edo.setActiveFilePath(model.getActiveFilePath());
        edo.setActiveFileVersion(model.getActiveFileVersion());
        edo.setId(model.getId());

        edo.setFileVersions(workflowFileVersionMapper.toEdoList(model.getFileVersions()));

        return edo;
    }

}
