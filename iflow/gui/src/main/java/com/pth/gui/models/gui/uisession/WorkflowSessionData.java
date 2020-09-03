package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.workflow.WorkflowType;

import java.util.ArrayList;
import java.util.List;

public class WorkflowSessionData {

    private List<WorkflowType> workflowTypes;

    public WorkflowSessionData() {
        workflowTypes = new ArrayList<>();
    }

    public WorkflowSessionData(List<WorkflowType> workflowTypes) {
        this.workflowTypes = workflowTypes;
    }

    public List<WorkflowType> getWorkflowTypes() {
        return workflowTypes;
    }

    public void setWorkflowTypes(List<WorkflowType> workflowTypes) {
        this.workflowTypes = workflowTypes;
    }
}
