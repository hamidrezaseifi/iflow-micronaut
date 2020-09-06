package com.pth.common.edo.workflow.invoice;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pth.common.edo.contants.IsoFormats;
import com.pth.common.edo.enums.EInvoiceType;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.common.edo.validation.AEnumValueValidator;
import com.pth.common.edo.workflow.WorkflowEdo;

public class InvoiceWorkflowEdo {

  @NotNull(message = "Workflow is not allowed to be null!")
  private WorkflowEdo workflow;

  @NotNull(message = "Sender is not allowed to be null!")
  private String      sender;

  @NotNull(message = "RegisterNumber is not allowed to be null!")
  private String      registerNumber;

  @NotNull(message = "InvoiceDate is not allowed to be null!")
  @JsonFormat(pattern = IsoFormats.DATE_FORMAT_ISO)
  private LocalDate   invoiceDate;

  @NotNull(message = "PartnerCode is not allowed to be null!")
  private String      partnerCode;

  @NotNull(message = "VendorNumber is not allowed to be null!")
  private String      vendorNumber;

  @NotNull(message = "vendorName is not allowed to be null!")
  private String      vendorName;

  @NotNull(message = "IsDirectDebit is not allowed to be null!")
  private Boolean     isDirectDebitPermission;

  @NotNull(message = "InvoiceType must not be null")
  @AEnumValueValidator(enumClazz = EInvoiceType.class)
  private Integer     invoiceType;

  @NotNull(message = "DiscountEnterDate is not allowed to be null!")
  @JsonFormat(pattern = IsoFormats.DATE_FORMAT_ISO)
  private LocalDate   discountEnterDate;

  @NotNull(message = "DiscountDeadline is not allowed to be null!")
  private Integer     discountDeadline;

  @NotNull(message = "DiscountRate is not allowed to be null!")
  private Double      discountRate;

  @NotNull(message = "DiscountDate is not allowed to be null!")
  @JsonFormat(pattern = IsoFormats.DATE_FORMAT_ISO)
  private LocalDate   discountDate;

  @NotNull(message = "PaymentAmount is not allowed to be null!")
  private Double      paymentAmount;

  public WorkflowEdo getWorkflow() {
    return this.workflow;
  }

  public void setWorkflow(final WorkflowEdo workflow) {
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
