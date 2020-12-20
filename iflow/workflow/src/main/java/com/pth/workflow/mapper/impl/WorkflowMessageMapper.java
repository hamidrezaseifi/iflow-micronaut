package com.pth.workflow.mapper.impl;

import com.pth.common.edo.WorkflowMessageEdo;
import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.entities.WorkflowMessageEntity;
import com.pth.workflow.mapper.IWorkflowMessageMapper;

import javax.inject.Singleton;

@Singleton
public class WorkflowMessageMapper extends ModelEdoMapperBase<WorkflowMessageEntity, WorkflowMessageEdo>
        implements IWorkflowMessageMapper {

    @Override
    public WorkflowMessageEntity fromEdo(WorkflowMessageEdo edo) {
        final WorkflowMessageEntity model = new WorkflowMessageEntity();
        model.setStatus(edo.getStatus());
        model.setUserId(edo.getUserId());
        model.setCreatedById(edo.getCreatedById());
        model.setVersion(edo.getVersion());
        model.setWorkflowId(edo.getWorkflowId());
        model.setMessageType(edo.getMessageType());
        model.setExpireDays(edo.getExpireDays());
        model.setMessage(edo.getMessage());
        //model.setCreatedAt(edo.getCreatedAt());
        model.setStepId(edo.getStepId());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public WorkflowMessageEdo toEdo(WorkflowMessageEntity model) {
        final WorkflowMessageEdo edo = new WorkflowMessageEdo();
        edo.setStatus(model.getStatus());
        edo.setUserId(model.getUserId());
        edo.setCreatedById(model.getCreatedById());
        edo.setVersion(model.getVersion());
        edo.setWorkflowId(model.getWorkflowId());
        edo.setMessageType(model.getMessageType());
        edo.setExpireDays(model.getExpireDays());
        edo.setMessage(model.getMessage());
        edo.setCreatedAt(model.getCreatedAt().toLocalDateTime());
        edo.setStepId(model.getStepId());
        edo.setId(model.getId());

        return edo;
    }

}
