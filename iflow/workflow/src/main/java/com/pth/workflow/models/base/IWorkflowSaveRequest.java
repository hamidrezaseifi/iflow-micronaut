package com.pth.workflow.models.base;

import java.util.List;
import com.pth.iflow.common.enums.EWorkflowProcessCommand;
import com.pth.iflow.workflow.models.AssignItem;

public interface IWorkflowSaveRequest<W extends IWorkflowBaseEntity> {

  /**
   * @return the workflow
   */
  public W getWorkflow();

  public void setWorkflow(W workflow);

  public Integer getExpireDays();

  public List<AssignItem> getAssigns();

  public EWorkflowProcessCommand getCommand();

  public boolean isAssignCommand();

  public boolean isArchiveCommand();

  public boolean isCreateCommand();

  public boolean isDoneCommand();

  public boolean isSaveCommand();

}
