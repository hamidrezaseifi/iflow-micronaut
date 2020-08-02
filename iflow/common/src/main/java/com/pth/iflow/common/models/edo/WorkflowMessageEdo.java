package com.pth.iflow.common.models.edo;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pth.iflow.common.enums.EWorkflowMessageStatus;
import com.pth.iflow.common.enums.EWorkflowMessageType;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.helper.IsoFormats;
import com.pth.iflow.common.models.helper.LocalDateTimeEdoAdapter;
import com.pth.iflow.common.models.validation.AEnumValueValidator;

@XmlRootElement(name = "WorkflowMessage", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "WorkflowMessage" + IFlowJaxbDefinition.TYPE_PREFIX)
public class WorkflowMessageEdo {

  @NotNull(message = "WorkflowIdentity must not be null")
  @XmlElement(name = "WorkflowIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String        workflowIdentity;

  @NotNull(message = "StepIdentity must not be null")
  @XmlElement(name = "StepIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String        stepIdentity;

  @NotNull(message = "UserId must not be null")
  @XmlElement(name = "UserId", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String        userIdentity;

  @XmlElement(name = "Message", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String        message;

  @NotNull(message = "CreatedByIdentity must not be null")
  @XmlElement(name = "CreatedByIdentity", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String        createdByIdentity;

  @NotNull(message = "MessageType must not be null")
  @AEnumValueValidator(enumClazz = EWorkflowMessageType.class)
  @XmlElement(name = "MessageType", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer       messageType;

  @NotNull(message = "status must not be null")
  @AEnumValueValidator(enumClazz = EWorkflowMessageStatus.class)
  @XmlElement(name = "Status", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer       status;

  @NotNull(message = "version must not be null")
  @XmlElement(name = "Version", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer       version;

  @XmlElement(name = "ExpireDays", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  @NotNull(message = "expired must not be null")
  private Integer       expireDays;

  @XmlJavaTypeAdapter(LocalDateTimeEdoAdapter.class)
  @JsonFormat(pattern = IsoFormats.DATETIME_FORMAT_ISO)
  @XmlSchemaType(name = "dateTime")
  @XmlElement(name = "CreatedAt", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private LocalDateTime createdAt;

  public String getWorkflowIdentity() {
    return this.workflowIdentity;
  }

  public void setWorkflowIdentity(final String workflowIdentity) {
    this.workflowIdentity = workflowIdentity;
  }

  public String getStepIdentity() {
    return this.stepIdentity;
  }

  public void setStepIdentity(final String stepIdentity) {
    this.stepIdentity = stepIdentity;
  }

  public String getUserIdentity() {
    return this.userIdentity;
  }

  public void setUserIdentity(final String userIdentity) {
    this.userIdentity = userIdentity;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public String getCreatedByIdentity() {
    return this.createdByIdentity;
  }

  public void setCreatedByIdentity(final String createdByIdentity) {
    this.createdByIdentity = createdByIdentity;
  }

  public Integer getMessageType() {
    return this.messageType;
  }

  public void setMessageType(final Integer messageType) {
    this.messageType = messageType;
  }

  public Integer getStatus() {
    return this.status;
  }

  public void setStatus(final Integer status) {
    this.status = status;
  }

  public Integer getVersion() {
    return this.version;
  }

  public void setVersion(final Integer version) {
    this.version = version;
  }

  public Integer getExpireDays() {
    return this.expireDays;
  }

  public void setExpireDays(final Integer expireDays) {
    this.expireDays = expireDays;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(final LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

}
