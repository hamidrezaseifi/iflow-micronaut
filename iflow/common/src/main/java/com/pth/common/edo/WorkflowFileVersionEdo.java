package com.pth.common.edo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class WorkflowFileVersionEdo {


  @NotNull(message = "id must not be null")
  protected UUID id;

  @NotNull(message = "createdById must not be null!")
  private UUID createdById;

  @NotNull(message = "FilePath must not be null!")
  private String  filePath;

  private String  comments;

  @NotNull(message = "FileVersion must not be null!")
  private Integer fileVersion;

  @NotNull(message = "Status must not be null!")
  private Integer status;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getFilePath() {
    return this.filePath;
  }

  public void setFilePath(final String filePath) {
    this.filePath = filePath;
  }

  public UUID getCreatedById() {
    return this.createdById;
  }

  public void setCreatedById(final UUID createdById) {
    this.createdById = createdById;
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
