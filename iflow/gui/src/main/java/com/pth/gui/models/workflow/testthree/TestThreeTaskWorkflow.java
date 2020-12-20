package com.pth.gui.models.workflow.testthree;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.models.workflow.base.WorkflowBased;
import com.pth.gui.models.workflow.workflow.Workflow;
;import java.util.UUID;

@JsonIgnoreProperties(value = { "isAssignTo" })
public class TestThreeTaskWorkflow extends WorkflowBased {


  public EWorkflowType getWorkflowTypeEnum() {
    return EWorkflowType.TESTTHREE_TASK_WORKFLOW_TYPE;
  }

  public static TestThreeTaskWorkflow generateInitial(final UUID creatorId,
                                                      final UUID currentUserId,
                                                      WorkflowType workflowType) {

    if(workflowType.getTypeEnum() != new TestThreeTaskWorkflow().getWorkflowTypeEnum()){
      throw new GuiCustomizedException("invalid-initial-workflowtype");
    }

    final TestThreeTaskWorkflow newWorkflow = new TestThreeTaskWorkflow();
    newWorkflow.setWorkflow(Workflow.generateInitial(creatorId, currentUserId, workflowType));


    return newWorkflow;
  }

}
