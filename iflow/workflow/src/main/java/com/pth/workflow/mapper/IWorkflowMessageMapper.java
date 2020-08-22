package com.pth.workflow.mapper;


import com.pth.common.edo.WorkflowMessageEdo;
import com.pth.common.edo.WorkflowTypeStepEdo;
import com.pth.common.mapping.IModelEdoMapper;
import com.pth.workflow.entities.workflow.WorkflowMessageEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeStepEntity;

public interface IWorkflowMessageMapper extends IModelEdoMapper<WorkflowMessageEntity, WorkflowMessageEdo> {
    
}
