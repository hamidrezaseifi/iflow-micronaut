package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class WorkflowFileEdo {


  @NotNull(message = "id must not be null")
  protected UUID id;

  @NotNull(message = "CreatedById is not allowed to be null!")
  private UUID                       createdById;

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

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getActiveFilePath() {
    return this.activeFilePath;
  }

  public void setActiveFilePath(final String filePath) {
    this.activeFilePath = filePath;
  }

  public UUID getCreatedById() {
    return this.createdById;
  }

  public void setCreatedById(final UUID createdById) {
    this.createdById = createdById;
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
