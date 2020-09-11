package com.pth.gui.services.impl.workflow;

import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflowSaveRequest;
import com.pth.gui.services.IBasicWorkflowHandler;

public interface ISingleTaskBasicWorkflowHandler
        extends IBasicWorkflowHandler<SingleTaskWorkflow, SingleTaskWorkflowSaveRequest> {
}
