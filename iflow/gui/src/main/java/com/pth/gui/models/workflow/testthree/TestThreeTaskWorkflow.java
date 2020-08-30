package com.pth.gui.models.workflow.testthree;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pth.common.edo.enums.EWorkflowStatus;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.models.workflow.IWorkflow;
import com.pth.gui.models.workflow.base.WorkflowBase;
;import java.util.UUID;

@JsonIgnoreProperties(value = { "isAssignTo" })
public class TestThreeTaskWorkflow extends WorkflowBase implements IWorkflow {

  @Override
  public EWorkflowType getWorkflowTypeEnum() {
    return EWorkflowType.TESTTHREE_TASK_WORKFLOW_TYPE;
  }

  public static TestThreeTaskWorkflow generateInitial(final UUID creatorId) {
    final TestThreeTaskWorkflow newWorkflow = new TestThreeTaskWorkflow();
    newWorkflow.setStatus(EWorkflowStatus.INITIALIZE);
    newWorkflow.setCreatedById(creatorId);
    newWorkflow.setControllerId(null);
    newWorkflow.setCurrentStepId(null);
    newWorkflow.setVersion(0);
    newWorkflow.setComments("");
    newWorkflow.setIdentity("");

    return newWorkflow;
  }

}
