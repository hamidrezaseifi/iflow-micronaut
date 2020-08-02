package com.pth.iflow.common.models.edo;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;

@XmlRootElement(name = "WorkflowFileList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "WorkflowFileList" + IFlowJaxbDefinition.TYPE_PREFIX)
public class WorkflowFileListEdo {

  @NotNull
  @XmlElementWrapper(name = "WorkflowFileList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "WorkflowFile", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
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
