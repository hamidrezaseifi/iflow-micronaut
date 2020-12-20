package com.pth.gui.models.workflow.singletask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.models.workflow.base.WorkflowBased;
import com.pth.gui.models.workflow.workflow.Workflow;

import java.util.UUID;

@JsonIgnoreProperties(value = { "isAssignTo" })
public class SingleTaskWorkflow extends WorkflowBased {

  public EWorkflowType getWorkflowTypeEnum() {

    return EWorkflowType.SINGLE_TASK_WORKFLOW_TYPE;
  }

  public static SingleTaskWorkflow generateInitial(final UUID creatorId,
                                                   final UUID currentUserId,
                                                   WorkflowType workflowType) {

    if(workflowType.getTypeEnum() != new SingleTaskWorkflow().getWorkflowTypeEnum()){
      throw new GuiCustomizedException("invalid-initial-workflowtype");
    }

    final SingleTaskWorkflow newWorkflow = new SingleTaskWorkflow();
    newWorkflow.setWorkflow(Workflow.generateInitial(creatorId, currentUserId, workflowType));

    return newWorkflow;
  }

}
