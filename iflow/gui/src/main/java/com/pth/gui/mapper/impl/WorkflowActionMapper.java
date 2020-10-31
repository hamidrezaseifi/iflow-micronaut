package com.pth.gui.mapper.impl;

import com.pth.common.edo.WorkflowActionEdo;
import com.pth.common.edo.WorkflowFileVersionEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.IWorkflowActionMapper;
import com.pth.gui.mapper.IWorkflowFileVersionMapper;
import com.pth.gui.models.workflow.WorkflowAction;
import com.pth.gui.models.workflow.WorkflowFileVersion;

import javax.inject.Singleton;

@Singleton
public class WorkflowActionMapper extends ModelEdoMapperBase<WorkflowAction, WorkflowActionEdo>
        implements IWorkflowActionMapper {

    @Override
    public WorkflowAction fromEdo(WorkflowActionEdo edo) {
        final WorkflowAction model = new WorkflowAction();

        model.setComments(edo.getComments());
        model.setStatus(edo.getStatus());
        model.setCurrentStepId(edo.getCurrentStepId());
        model.setAssignToId(edo.getAssignToId());
        model.setId(edo.getId());

        return model;
    }

    @Override
    public WorkflowActionEdo toEdo(WorkflowAction model) {
        final WorkflowActionEdo edo = new WorkflowActionEdo();
        edo.setComments(model.getComments());
        edo.setStatus(model.getStatus().getValue().intValue());
        edo.setCurrentStepId(model.getCurrentStepId());
        edo.setAssignToId(model.getAssignToId());
        edo.setId(model.getId());

        return edo;
    }

}
