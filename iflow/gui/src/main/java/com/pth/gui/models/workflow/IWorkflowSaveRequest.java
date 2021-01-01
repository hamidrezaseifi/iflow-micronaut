package com.pth.gui.models.workflow;

import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.gui.models.ArchiveFileData;
import com.pth.gui.models.workflow.base.WorkflowBased;

import java.util.List;
import java.util.UUID;


public interface IWorkflowSaveRequest<W extends WorkflowBased> {

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

  void setAssignUser(final UUID userId);

  public List<ArchiveFileData> getUploadedFiles();

  public void setUploadedFiles(final List<ArchiveFileData> uploadedFiles);

  public String getComments();

  public void setComments(String comments);

}
