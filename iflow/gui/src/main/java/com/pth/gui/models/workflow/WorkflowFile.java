package com.pth.gui.models.workflow;

import com.pth.gui.models.GuiBaseModel;
import com.pth.gui.models.gui.FileSavingData;
import com.pth.gui.models.gui.GuiSocketMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class WorkflowFile extends GuiBaseModel {

  private UUID workflowId;
  private UUID createdById;
  private String title;
  private String extention;
  private String activeFilePath;
  private String comments;
  private Integer activeFileVersion;
  private Integer status;
  private final List<WorkflowFileVersion> fileVersions = new ArrayList<>();

  public WorkflowFile() {
    super();
  }

  public UUID getWorkflowId() {

    return this.workflowId;
  }

  public void setWorkflowId(final UUID workflowId) {

    this.workflowId = workflowId;
  }

  public UUID getCreatedById() {

    return this.createdById;
  }

  public void setCreatedById(final UUID createdById) {

    this.createdById = createdById;
  }

  public String getActiveFilePath() {

    return this.activeFilePath;
  }

  public void setActiveFilePath(final String filePath) {

    this.activeFilePath = filePath;

  }

  public String getTitle() {

    return this.title;
  }

  public void setTitle(final String title) {

    this.title = title;
  }

  public String getExtention() {

    return this.extention;
  }

  public void setExtention(final String extention) {

    this.extention = extention;
  }

  public String getComments() {

    return this.comments;
  }

  public void setComments(final String comments) {

    this.comments = comments;
  }

  public Integer getActiveFileVersion() {

    return this.activeFileVersion;
  }

  public void setActiveFileVersion(final Integer fileVersion) {

    this.activeFileVersion = fileVersion;
  }

  public Integer getStatus() {

    return this.status;
  }

  public void setStatus(final Integer status) {

    this.status = status;
  }

  public List<WorkflowFileVersion> getFileVersions() {

    return this.fileVersions;
  }

  public void setFileVersions(final List<WorkflowFileVersion> fileVersions) {

    this.fileVersions.clear();
    if (fileVersions != null) {
      this.fileVersions.addAll(fileVersions);
    }
  }

  public void addFileVersion(final WorkflowFileVersion fileVersion) {

    this.fileVersions.add(fileVersion);

  }

  public boolean getFileIsPdf() {

    return FileSavingData.isFilePdf(this.activeFilePath);
  }

  public boolean getFileIsImage() {

    return FileSavingData.isFileImage(this.activeFilePath);
  }

  public String getActiveFilePathHash() {

    return GuiSocketMessage.encodeHashPath(this.activeFilePath);
  }

  public WorkflowFileVersion addNewFileVersion(final String filePath,
                                               final int version,
                                               final UUID userId,
                                               final String comments) {

    final WorkflowFileVersion fileVersion = new WorkflowFileVersion();
    fileVersion.setComments(comments);
    fileVersion.setCreatedById(userId);
    fileVersion.setFilePath(filePath);
    fileVersion.setFileVersion(version);
    fileVersion.setStatus(1);

    this.fileVersions.add(fileVersion);

    return fileVersion;
  }

}
