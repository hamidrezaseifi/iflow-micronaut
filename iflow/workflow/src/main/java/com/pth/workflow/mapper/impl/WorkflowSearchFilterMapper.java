package com.pth.workflow.mapper.impl;

import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.mapper.IWorkflowSearchFilterMapper;
import com.pth.workflow.models.WorkflowSearchFilter;

import javax.inject.Singleton;

@Singleton
public class WorkflowSearchFilterMapper
        extends ModelEdoMapperBase<WorkflowSearchFilter, WorkflowSearchFilterEdo>
        implements IWorkflowSearchFilterMapper {


    @Override
    public WorkflowSearchFilter fromEdo(WorkflowSearchFilterEdo edo) {
        WorkflowSearchFilter model = new WorkflowSearchFilter();
        model.setAssignedUserIdSet(edo.getAssignedUserIdSet());
        model.setStatusSet(edo.getStatusSet());
        model.setCompanyId(edo.getCompanyId());
        model.setWorkflowStepIdSet(edo.getWorkflowStepeIdSet());
        model.setWorkflowStepIdSet(edo.getWorkflowTypeIdSet());

        return model;
    }

    @Override
    public WorkflowSearchFilterEdo toEdo(WorkflowSearchFilter model) {

        WorkflowSearchFilterEdo edo = new WorkflowSearchFilterEdo();
        edo.setAssignedUserIdSet(model.getAssignedUserIdSet());
        edo.setStatusSet(model.getStatusSet());
        edo.setCompanyId(model.getCompanyId());
        edo.setWorkflowStepeIdSet(model.getWorkflowStepIdSet());
        edo.setWorkflowTypeIdSet(model.getWorkflowTypeIdSet());

        return edo;
    }
}
