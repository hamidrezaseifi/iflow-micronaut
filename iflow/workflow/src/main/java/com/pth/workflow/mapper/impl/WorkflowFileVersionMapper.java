package com.pth.workflow.mapper.impl;

import com.pth.common.edo.WorkflowFileVersionEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.entities.WorkflowFileVersionEntity;
import com.pth.workflow.mapper.IWorkflowFileVersionMapper;

import javax.inject.Singleton;

@Singleton
public class WorkflowFileVersionMapper extends ModelEdoMapperBase<WorkflowFileVersionEntity, WorkflowFileVersionEdo>
        implements IWorkflowFileVersionMapper {

    @Override
    public WorkflowFileVersionEntity fromEdo(WorkflowFileVersionEdo edo) {
        final WorkflowFileVersionEntity model = new WorkflowFileVersionEntity();

        model.setComments(edo.getComments());
        model.setStatus(edo.getStatus());
        model.setCreatedByUserId(edo.getCreatedById());
        model.setFilePath(edo.getFilePath());
        model.setFileVersion(edo.getFileVersion());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public WorkflowFileVersionEdo toEdo(WorkflowFileVersionEntity model) {
        final WorkflowFileVersionEdo edo = new WorkflowFileVersionEdo();
        edo.setComments(model.getComments());
        edo.setStatus(model.getStatus());
        edo.setCreatedById(model.getCreatedByUserId());
        edo.setFilePath(model.getFilePath());
        edo.setFileVersion(model.getFileVersion());
        edo.setId(model.getId());

        return edo;
    }

}
