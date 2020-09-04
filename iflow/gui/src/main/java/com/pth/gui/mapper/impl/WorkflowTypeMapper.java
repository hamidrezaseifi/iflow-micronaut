package com.pth.gui.mapper.impl;

import com.pth.common.edo.UserEdo;
import com.pth.common.edo.WorkflowTypeEdo;
import com.pth.common.edo.WorkflowTypeStepEdo;
import com.pth.common.edo.enums.EWorkflowTypeAssignType;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.gui.mapper.IWorkflowTypeMapper;
import com.pth.gui.mapper.IWorkflowTypeStepMapper;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.models.workflow.WorkflowTypeStep;

import javax.inject.Singleton;
import java.util.stream.Collectors;

@Singleton
public class WorkflowTypeMapper extends ModelEdoMapperBase<WorkflowType, WorkflowTypeEdo>
        implements IWorkflowTypeMapper {

    private final IWorkflowTypeStepMapper workflowTypeStepMapper;

    public WorkflowTypeMapper(IWorkflowTypeStepMapper workflowTypeStepMapper) {
        this.workflowTypeStepMapper = workflowTypeStepMapper;
    }

    @Override
    public WorkflowType fromEdo(WorkflowTypeEdo edo) {
        final WorkflowType model = new WorkflowType();

        model.setTitle(edo.getTitle());
        model.setComments(edo.getComments());
        model.setStatus(edo.getStatus());
        model.setIdentity(edo.getIdentity());
        model.setSendToController(edo.getSendToController());
        model.setAssignType(edo.getAssignType());
        model.setAllowAssign(edo.getAllowAssign());
        model.setIncreaseStepAutomatic(edo.getIncreaseStepAutomatic());
        model.setVersion(edo.getVersion());
        model.setSteps(workflowTypeStepMapper.fromEdoList(edo.getSteps().stream().collect(Collectors.toList())));

        return model;
    }

    @Override
    public WorkflowTypeEdo toEdo(WorkflowType model) {
        final WorkflowTypeEdo edo = new WorkflowTypeEdo();
        edo.setTitle(model.getTitle());
        edo.setComments(model.getComments());
        edo.setStatus(model.getStatus());
        edo.setIdentity(model.getIdentity());
        edo.setSendToController(model.getSendToController());
        edo.setAssignType(model.geAssignType());
        edo.setIncreaseStepAutomatic(model.getIncreaseStepAutomatic());
        edo.setAllowAssign(model.getAllowAssign());
        edo.setSteps(workflowTypeStepMapper.toEdoList(model.getSteps()).stream().collect(Collectors.toSet()));
        edo.setVersion(model.getVersion());

        return edo;
    }

}
