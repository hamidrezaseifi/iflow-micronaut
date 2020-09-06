package com.pth.gui.mapper.impl;

import com.pth.common.edo.WorkflowFileVersionEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.IWorkflowFileVersionMapper;
import com.pth.gui.models.workflow.WorkflowFileVersion;

import javax.inject.Singleton;

@Singleton
public class WorkflowFileVersionMapper extends ModelEdoMapperBase<WorkflowFileVersion, WorkflowFileVersionEdo>
        implements IWorkflowFileVersionMapper {

    @Override
    public WorkflowFileVersion fromEdo(WorkflowFileVersionEdo edo) {
        final WorkflowFileVersion model = new WorkflowFileVersion();

        model.setComments(edo.getComments());
        model.setStatus(edo.getStatus());
        model.setCreatedById(edo.getCreatedById());
        model.setFilePath(edo.getFilePath());
        model.setFileVersion(edo.getFileVersion());

        return model;
    }

    @Override
    public WorkflowFileVersionEdo toEdo(WorkflowFileVersion model) {
        final WorkflowFileVersionEdo edo = new WorkflowFileVersionEdo();
        edo.setComments(model.getComments());
        edo.setStatus(model.getStatus());
        edo.setCreatedById(model.getCreatedById());
        edo.setFilePath(model.getFilePath());
        edo.setFileVersion(model.getFileVersion());

        return edo;
    }

}
