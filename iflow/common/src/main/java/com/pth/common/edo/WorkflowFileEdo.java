package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonSetter;

public class WorkflowFileEdo {

  @NotNull(message = "Identity is not allowed to be null!")
  private String                       identity;

  @NotNull(message = "CreatedByIdentity is not allowed to be null!")
  private String                       createdByIdentity;

  @NotNull
  private String                       title;

  @NotNull
  private String                       extention;

  private String                       activeFilePath;

  private Integer                      activeFileVersion;

  private String                       comments;

  @NotNull
  private Integer                      status;

  @NotNull
  private List<WorkflowFileVersionEdo> fileVersions = new ArrayList<>();

  public String getIdentity() {
    return this.identity;
  }

  public void setIdentity(final String identity) {
    this.identity = identity;
  }

  public String getActiveFilePath() {
    return this.activeFilePath;
  }

  public void setActiveFilePath(final String filePath) {
    this.activeFilePath = filePath;
  }

  public String getCreatedByIdentity() {
    return this.createdByIdentity;
  }

  public void setCreatedByIdentity(final String createdByIdentity) {
    this.createdByIdentity = createdByIdentity;
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

  public List<WorkflowFileVersionEdo> getFileVersions() {
    return this.fileVersions;
  }

  @JsonSetter
  public void setFileVersions(final List<WorkflowFileVersionEdo> fileVersions) {
    this.fileVersions = new ArrayList<>();
    if (fileVersions != null) {
      this.fileVersions.addAll(fileVersions);
    }
  }

}
