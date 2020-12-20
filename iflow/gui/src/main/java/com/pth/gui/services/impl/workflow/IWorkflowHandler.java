package com.pth.gui.services.impl.workflow;

import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.workflow.Workflow;

import java.util.Optional;
import java.util.UUID;

public interface IWorkflowHandler {

    Optional<Workflow> readWorkflow(final UUID workflowId, SessionData sessionData);

}
