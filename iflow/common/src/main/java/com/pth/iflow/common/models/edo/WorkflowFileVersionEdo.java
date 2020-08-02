package com.pth.iflow.common.models.edo;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.pth.iflow.common.models.base.IFlowJaxbDefinition;

@XmlRootElement(name = "WorkflowFileVersion", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "WorkflowFileVersion" + IFlowJaxbDefinition.TYPE_PREFIX)
public class WorkflowFileVersionEdo {

  @NotNull(message = "CreatedByIdentity must not be null!")
  @XmlElement(name = "CreatedByIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String  createdByIdentity;

  @NotNull(message = "FilePath must not be null!")
  @XmlElement(name = "FilePath", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String  filePath;

  @XmlElement(name = "Comments", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String  comments;

  @NotNull(message = "FileVersion must not be null!")
  @XmlElement(name = "FileVersion", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer fileVersion;

  @NotNull(message = "Status must not be null!")
  @XmlElement(name = "Status", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
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
