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

@XmlRootElement(name = "WorkflowFile", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "WorkflowFile" + IFlowJaxbDefinition.TYPE_PREFIX)
public class WorkflowFileEdo {

  @NotNull(message = "Identity is not allowed to be null!")
  @XmlElement(name = "Identity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String                       identity;

  @NotNull(message = "CreatedByIdentity is not allowed to be null!")
  @XmlElement(name = "CreatedByIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String                       createdByIdentity;

  @NotNull
  @XmlElement(name = "Title", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String                       title;

  @NotNull
  @XmlElement(name = "Extention", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String                       extention;

  @XmlElement(name = "ActiveFilePath", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String                       activeFilePath;

  @XmlElement(name = "ActiveFileVersion", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer                      activeFileVersion;

  @XmlElement(name = "Comments", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String                       comments;

  @NotNull
  @XmlElement(name = "Status", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer                      status;

  @NotNull
  @XmlElementWrapper(name = "FileVersionList", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @XmlElement(name = "FileVersion", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
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
