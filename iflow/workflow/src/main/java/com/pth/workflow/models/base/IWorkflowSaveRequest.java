package com.pth.workflow.models.base;

import java.util.List;

import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.workflow.models.AssignItem;

public interface IWorkflowSaveRequest<W extends IWorkflowBaseEntity> {

  /**
   * @return the workflow
   */
  W getWorkflow();

  void setWorkflow(W workflow);

  Integer getExpireDays();

  List<AssignItem> getAssigns();

  EWorkflowProcessCommand getCommand();

  boolean isAssignCommand();

  boolean isArchiveCommand();

  boolean isCreateCommand();

  boolean isDoneCommand();

  boolean isSaveCommand();

}
