package com.pth.gui.services;

import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.IWorkflow;
import com.pth.gui.models.workflow.IWorkflowSaveRequest;
import com.pth.gui.models.workflow.WorkflowFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface IBasicWorkflowHandler<W extends IWorkflow, WS extends IWorkflowSaveRequest<W>> {

  Optional<W> readWorkflow(final UUID workflowId, SessionData sessionData);

  Optional<WorkflowFile> readWorkflowFile(final UUID workflowId,
                                          final UUID fileId,
                                          SessionData sessionData);

  List<W> createWorkflow(final WS createRequest, SessionData sessionData);

  Optional<W> saveWorkflow(final WS saveRequest, SessionData sessionData);

  Optional<W> doneWorkflow(final WS saveRequest, SessionData sessionData);

  Optional<W> archiveWorkflow(final W workflow, SessionData sessionData);

  Optional<W> assignWorkflow(final UUID workflowId, SessionData sessionData);

}
