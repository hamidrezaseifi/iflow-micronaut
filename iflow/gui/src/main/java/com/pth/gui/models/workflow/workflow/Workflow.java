package com.pth.gui.models.workflow.workflow;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pth.common.edo.enums.EIdentity;
import com.pth.common.edo.enums.EWorkflowStatus;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.models.workflow.IWorkflow;
import com.pth.gui.models.workflow.base.WorkflowBase;

import java.util.UUID;

@JsonIgnoreProperties(value = { "isAssignTo" })
public class Workflow extends WorkflowBase implements IWorkflow {

  public static Workflow generateInitial(final UUID creatorId) {
    final Workflow newWorkflow = new Workflow();
    newWorkflow.setStatus(EWorkflowStatus.INITIALIZE);
    newWorkflow.setCreatedById(creatorId);
    newWorkflow.setControllerId(null);
    newWorkflow.setCurrentStepId(null);
    newWorkflow.setVersion(0);
    newWorkflow.setComments("");
    newWorkflow.setIdentity("");

    return newWorkflow;
  }

  @Override
  public EWorkflowType getWorkflowTypeEnum() {
    return EWorkflowType.NONE;
  }
}
