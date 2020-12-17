package com.pth.gui.services;


import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowSearchFilter;
import com.pth.gui.models.workflow.workflow.Workflow;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IWorkflowSearchHandler {

  List<Workflow> searchWorkflow(final WorkflowSearchFilter workflowSearchFilter, SessionData sessionData);

}
