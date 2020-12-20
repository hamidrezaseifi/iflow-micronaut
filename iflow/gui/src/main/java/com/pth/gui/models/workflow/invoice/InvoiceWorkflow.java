package com.pth.gui.models.workflow.invoice;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pth.common.edo.enums.EInvoiceType;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.models.workflow.WorkflowType;
import com.pth.gui.models.workflow.base.WorkflowBased;
import com.pth.gui.models.workflow.workflow.Workflow;

@JsonIgnoreProperties(value = { "isAssignTo" })
public class InvoiceWorkflow extends WorkflowBased {


  private String sender;

  private String registerNumber;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate invoiceDate;

  private String partnerCode;

  private String vendorNumber;

  private String vendorName;

  private Boolean isDirectDebitPermission;

  private EInvoiceType invoiceType;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate discountEnterDate;

  private Integer discountDeadline;

  private Double discountRate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate discountDate;

  private Double paymentAmount;

  public EWorkflowType getWorkflowTypeEnum() {

    return EWorkflowType.INVOICE_WORKFLOW_TYPE;
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

  public void setIsDirectDebitPermission(final Boolean isDirectDebitPermission) {

    this.isDirectDebitPermission = isDirectDebitPermission;
  }

  public EInvoiceType getInvoiceType() {

    return this.invoiceType;
  }

  public void setInvoiceType(final EInvoiceType invoiceType) {

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

  public static InvoiceWorkflow generateInitial(final UUID creatorId,
                                                final UUID currentUserId,
                                                WorkflowType workflowType) {

    if(workflowType.getTypeEnum() != new InvoiceWorkflow().getWorkflowTypeEnum()){
      throw new GuiCustomizedException("invalid-initial-workflowtype");
    }

    final InvoiceWorkflow newWorkflow = new InvoiceWorkflow();
    newWorkflow.setWorkflow(Workflow.generateInitial(creatorId, currentUserId, workflowType));
    newWorkflow.setInvoiceDate(LocalDate.now());


    return newWorkflow;
  }



}
