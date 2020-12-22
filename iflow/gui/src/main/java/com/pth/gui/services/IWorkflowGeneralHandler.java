package com.pth.gui.services;


import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowSearchFilter;
import com.pth.gui.models.workflow.workflow.Workflow;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface IWorkflowGeneralHandler {

  List<Workflow> searchWorkflow(final WorkflowSearchFilter workflowSearchFilter, SessionData sessionData);
  Optional<Workflow> readById(UUID id, SessionData sessionData);
  IBasicWorkflowHandler getHandlerByWorkflowType(final EWorkflowType workflowType);

}
