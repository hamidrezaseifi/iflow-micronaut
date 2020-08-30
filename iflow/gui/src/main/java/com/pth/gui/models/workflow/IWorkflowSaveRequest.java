package com.pth.gui.models.workflow;

import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.gui.models.UploadededFile;

import java.util.List;
import java.util.UUID;


public interface IWorkflowSaveRequest<W extends IWorkflow> {

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

  public List<UploadededFile> getUploadedFiles();

  public void setUploadedFiles(final List<UploadededFile> uploadedFiles);

  public String getComments();

  public void setComments(String comments);

}
