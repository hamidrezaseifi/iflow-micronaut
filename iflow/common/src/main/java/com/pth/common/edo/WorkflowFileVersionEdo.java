package com.pth.common.edo;

import javax.validation.constraints.NotNull;

public class WorkflowFileVersionEdo {

  @NotNull(message = "CreatedByIdentity must not be null!")
  private String  createdByIdentity;

  @NotNull(message = "FilePath must not be null!")
  private String  filePath;

  private String  comments;

  @NotNull(message = "FileVersion must not be null!")
  private Integer fileVersion;

  @NotNull(message = "Status must not be null!")
  private Integer status;

  public String getFilePath() {
    return this.filePath;
  }

  public void setFilePath(final String filePath) {
    this.filePath = filePath;
  }

  public String getCreatedByIdentity() {
    return this.createdByIdentity;
  }

  public void setCreatedByIdentity(final String createdByIdentity) {
    this.createdByIdentity = createdByIdentity;
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
