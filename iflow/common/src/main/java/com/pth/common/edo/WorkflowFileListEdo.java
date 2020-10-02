package com.pth.common.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.micronaut.core.annotation.Introspected;

@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class WorkflowFileListEdo {

  @NotNull
  private final List<WorkflowFileEdo> workflowFiles = new ArrayList<>();

  public WorkflowFileListEdo() {

  }

  public WorkflowFileListEdo(final List<WorkflowFileEdo> workflowFiles) {
    this.setWorkflowFiles(workflowFiles);
  }

  public List<WorkflowFileEdo> getWorkflowFiles() {
    return this.workflowFiles;
  }

  @JsonSetter
  public void setWorkflowFiles(final List<WorkflowFileEdo> workflowFiles) {
    this.workflowFiles.clear();
    if (workflowFiles != null) {
      this.workflowFiles.addAll(workflowFiles);
    }
  }

}
