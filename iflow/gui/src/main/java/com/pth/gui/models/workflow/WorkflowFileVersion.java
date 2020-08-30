package com.pth.gui.models.workflow;

import com.pth.gui.models.GuiBaseModel;
import com.pth.gui.models.User;

import java.util.UUID;

public class WorkflowFileVersion extends GuiBaseModel {

  private User createdBy;
  private UUID createdById;
  private String  filePath;
  private String  comments;
  private Integer fileVersion;
  private Integer status;

  public WorkflowFileVersion() {
    super();
  }

  public String getFilePath() {
    return this.filePath;
  }

  public void setFilePath(final String filePath) {
    this.filePath = filePath;
  }

  public User getCreatedBy() {
    return this.createdBy;
  }

  public void setCreatedBy(final User createdBy) {
    this.createdBy = createdBy;
  }

  public UUID getCreatedById() {
    return this.createdById;
  }

  public void setCreatedById(final UUID createdByIdentity) {
    this.createdById = createdByIdentity;
  }

  public String getComments() {
    return this.comments;
  }

  public void setComments(final String comments) {
    this.comments = comments;
  }

  public Integer getFileVersion() {
    return this.fileVersion;
  }

  public void setFileVersion(final Integer fileVersion) {
    this.fileVersion = fileVersion;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(final Integer status) {
    this.status = status;
  }

}
