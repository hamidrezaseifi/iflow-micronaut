package com.pth.gui.mapper.impl;

import com.pth.common.edo.UserEdo;
import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.IWorkflowSearchFilterMapper;
import com.pth.gui.models.User;
import com.pth.gui.models.workflow.WorkflowSearchFilter;

public class WorkflowSearchFilterMapper
        extends ModelEdoMapperBase<WorkflowSearchFilter, WorkflowSearchFilterEdo>
        implements IWorkflowSearchFilterMapper {


    @Override
    public WorkflowSearchFilter fromEdo(WorkflowSearchFilterEdo edo) {
        return null;
    }

    @Override
    public WorkflowSearchFilterEdo toEdo(WorkflowSearchFilter model) {

        WorkflowSearchFilterEdo edo = new WorkflowSearchFilterEdo();
        edo.setAssignedUserIdSet(model.getAssignedUserIdSet());
        edo.setStatusSet(model.getStatusSet());
        edo.setCompanyId(model.getCompanyId());
        edo.setWorkflowStepeIdSet(model.getWorkflowStepIdSet());
        edo.setWorkflowTypeIdSet(model.getWorkflowTypeIdSet());

        return null;
    }
}
