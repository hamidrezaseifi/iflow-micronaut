package com.pth.iflow.common.models.edo.workflow.invoice;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pth.iflow.common.enums.EInvoiceType;
import com.pth.iflow.common.enums.EWorkflowType;
import com.pth.iflow.common.models.base.IFlowJaxbDefinition;
import com.pth.iflow.common.models.edo.workflow.WorkflowEdo;
import com.pth.iflow.common.models.helper.IsoFormats;
import com.pth.iflow.common.models.helper.LocalDateEdoAdapter;
import com.pth.iflow.common.models.validation.AEnumValueValidator;

@XmlRootElement(name = "InvoiceWorkflow", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = IFlowJaxbDefinition.IFlow.NAMESPACE, name = "InvoiceWorkflow" + IFlowJaxbDefinition.TYPE_PREFIX)
public class InvoiceWorkflowEdo {

  @NotNull(message = "Workflow is not allowed to be null!")
  @XmlElement(name = "Workflow", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private WorkflowEdo workflow;

  @NotNull(message = "Sender is not allowed to be null!")
  @XmlElement(name = "Sender", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String      sender;

  @NotNull(message = "RegisterNumber is not allowed to be null!")
  @XmlElement(name = "RegisterNumber", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String      registerNumber;

  @NotNull(message = "InvoiceDate is not allowed to be null!")
  @XmlJavaTypeAdapter(LocalDateEdoAdapter.class)
  @JsonFormat(pattern = IsoFormats.DATE_FORMAT_ISO)
  @XmlSchemaType(name = "dateTime")
  @XmlElement(name = "invocieDate", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private LocalDate   invoiceDate;

  @NotNull(message = "PartnerCode is not allowed to be null!")
  @XmlElement(name = "PartnerCode", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String      partnerCode;

  @NotNull(message = "VendorNumber is not allowed to be null!")
  @XmlElement(name = "VendorNumber", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String      vendorNumber;

  @NotNull(message = "vendorName is not allowed to be null!")
  @XmlElement(name = "VendorName", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private String      vendorName;

  @NotNull(message = "IsDirectDebit is not allowed to be null!")
  @XmlElement(name = "IsDirectDebit", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Boolean     isDirectDebitPermission;

  @NotNull(message = "InvoiceType must not be null")
  @AEnumValueValidator(enumClazz = EInvoiceType.class)
  @XmlElement(name = "InvoiceType", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer     invoiceType;

  @NotNull(message = "DiscountEnterDate is not allowed to be null!")
  @XmlJavaTypeAdapter(LocalDateEdoAdapter.class)
  @JsonFormat(pattern = IsoFormats.DATE_FORMAT_ISO)
  @XmlSchemaType(name = "dateTime")
  @XmlElement(name = "DiscountEnterDate", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private LocalDate   discountEnterDate;

  @NotNull(message = "DiscountDeadline is not allowed to be null!")
  @XmlElement(name = "DiscountDeadline", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Integer     discountDeadline;

  @NotNull(message = "DiscountRate is not allowed to be null!")
  @XmlElement(name = "DiscountRate", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Double      discountRate;

  @NotNull(message = "DiscountDate is not allowed to be null!")
  @XmlJavaTypeAdapter(LocalDateEdoAdapter.class)
  @JsonFormat(pattern = IsoFormats.DATE_FORMAT_ISO)
  @XmlSchemaType(name = "dateTime")
  @XmlElement(name = "DiscountDate", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private LocalDate   discountDate;

  @NotNull(message = "PaymentAmount is not allowed to be null!")
  @XmlElement(name = "PaymentAmount", namespace = IFlowJaxbDefinition.IFlow.NAMESPACE)
  private Double      paymentAmount;

  public WorkflowEdo getWorkflow() {
    return this.workflow;
  }

  public void setWorkflow(final WorkflowEdo workflow) {
    workflow.setWorkflowTypeIdentity(EWorkflowType.INVOICE_WORKFLOW_TYPE.getIdentity());
    this.workflow = workflow;
  }

  public String getSender() {
    return this.sender;
  }

  public void setSender(final String sender) {
    this.sender = sender;
  }

  public String getRegisterNumber() {
    return this.registerNumber;
  }

  public void setRegisterNumber(final String registerNumber) {
    this.registerNumber = registerNumber;
  }

  public LocalDate getInvoiceDate() {
    return this.invoiceDate;
  }

  public void setInvoiceDate(final LocalDate invocieDate) {
    this.invoiceDate = invocieDate;
  }

  public String getPartnerCode() {
    return this.partnerCode;
  }

  public void setPartnerCode(final String partnerCode) {
    this.partnerCode = partnerCode;
  }

  public String getVendorNumber() {
    return this.vendorNumber;
  }

  public void setVendorNumber(final String vendorNumber) {
    this.vendorNumber = vendorNumber;
  }

  public String getVendorName() {
    return this.vendorName;
  }

  public void setVendorName(final String vendorName) {
    this.vendorName = vendorName;
  }

  public Boolean getIsDirectDebitPermission() {
    return this.isDirectDebitPermission;
  }

  public void setIsDirectDebitPermission(final Boolean isDirectDebit) {
    this.isDirectDebitPermission = isDirectDebit;
  }

  public Integer getInvoiceType() {
    return this.invoiceType;
  }

  public void setInvoiceType(final Integer invoiceType) {
    this.invoiceType = invoiceType;
  }

  public LocalDate getDiscountEnterDate() {
    return this.discountEnterDate;
  }

  public void setDiscountEnterDate(final LocalDate discountEnterDate) {
    this.discountEnterDate = discountEnterDate;
  }

  public Integer getDiscountDeadline() {
    return this.discountDeadline;
  }

  public void setDiscountDeadline(final Integer discountDeadline) {
    this.discountDeadline = discountDeadline;
  }

  public Double getDiscountRate() {
    return this.discountRate;
  }

  public void setDiscountRate(final Double discountRate) {
    this.discountRate = discountRate;
  }

  public LocalDate getDiscountDate() {
    return this.discountDate;
  }

  public void setDiscountDate(final LocalDate discountDate) {
    this.discountDate = discountDate;
  }

  public Double getPaymentAmount() {
    return this.paymentAmount;
  }

  public void setPaymentAmount(final Double paymentAmount) {
    this.paymentAmount = paymentAmount;
  }

}
