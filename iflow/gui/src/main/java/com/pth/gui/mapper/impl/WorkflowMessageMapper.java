package com.pth.gui.mapper.impl;

import com.pth.common.edo.WorkflowMessageEdo;
import com.pth.common.edo.enums.EWorkflowMessageStatus;
import com.pth.common.edo.enums.EWorkflowMessageType;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.IWorkflowMessageMapper;
import com.pth.gui.models.workflow.WorkflowMessage;

import javax.inject.Singleton;

@Singleton
public class WorkflowMessageMapper extends ModelEdoMapperBase<WorkflowMessage, WorkflowMessageEdo>
        implements IWorkflowMessageMapper {

    @Override
    public WorkflowMessage fromEdo(WorkflowMessageEdo edo) {
        final WorkflowMessage model = new WorkflowMessage();
        model.setStatus(EWorkflowMessageStatus.ofValue(edo.getStatus()));
        model.setUserId(edo.getUserId());
        model.setCreatedById(edo.getCreatedById());
        model.setVersion(edo.getVersion());
        model.setWorkflowId(edo.getWorkflowId());
        model.setMessageType(EWorkflowMessageType.ofValue(edo.getMessageType()));
        model.setExpireDays(edo.getExpireDays());
        model.setMessage(edo.getMessage());
        model.setCreatedAt(edo.getCreatedAt());
        model.setStepId(edo.getStepId());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public WorkflowMessageEdo toEdo(WorkflowMessage model) {
        final WorkflowMessageEdo edo = new WorkflowMessageEdo();
        edo.setStatus(model.getStatus().getValue());
        edo.setUserId(model.getUserId());
        edo.setCreatedById(model.getCreatedById());
        edo.setVersion(model.getVersion());
        edo.setWorkflowId(model.getWorkflowId());
        edo.setMessageType(model.getMessageType().getValue());
        edo.setExpireDays(model.getExpireDays());
        edo.setMessage(model.getMessage());
        edo.setCreatedAt(model.getCreatedAt());
        edo.setStepId(model.getStepId());
        edo.setId(model.getId());

        return edo;
    }

}
