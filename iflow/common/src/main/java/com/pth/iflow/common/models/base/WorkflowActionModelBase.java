package com.pth.iflow.common.models.base;

import com.pth.iflow.common.enums.EWorkflowActionStatus;

public abstract class WorkflowActionModelBase {

  public abstract Integer getStatusInt();

  public boolean getIsActive() {
    return EWorkflowActionStatus.getIsActive(this.getStatusInt());
  }
}
