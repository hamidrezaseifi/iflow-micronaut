package com.pth.workflow.mapper;


import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.mapping.IModelEdoMapper;
import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.models.WorkflowSearchFilter;

public interface IWorkflowMapper extends IModelEdoMapper<WorkflowEntity, WorkflowEdo> {

    WorkflowSearchFilter fromEdo(WorkflowSearchFilterEdo edo);
}
