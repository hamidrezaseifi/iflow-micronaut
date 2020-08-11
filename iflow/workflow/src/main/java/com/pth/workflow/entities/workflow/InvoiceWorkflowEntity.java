package com.pth.core.entities.workflow;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.pth.common.edo.enums.EInvoiceType;
import com.pth.core.entities.workflow.base.IWorkflowContainerEntity;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "invoice_workflow")
public class InvoiceWorkflowEntity implements IWorkflowContainerEntity {

  @Id
  @Column(name = "workflow_id")
  private Long workflowId;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "workflow_id")
  private WorkflowEntity workflow;

  @Column(name = "sender")
  private String sender;

  @Column(name = "ext_reg_number")
  private String registerNumber;

  @Column(name = "invoce_date")
  private Date invoiceDate;

  @Column(name = "partner_code")
  private String partnerCode;

  @Column(name = "vendor_number")
  private String vendorNumber;

  @Column(name = "vendor_name")
  private String vendorName;

  @Type(type = "numeric_boolean")
  @Column(name = "direct_debit_permission")
  private Boolean isDirectDebitPermission;

  @Column(name = "invoice_type")
  private Integer invoiceType;

  @Column(name = "discount_enter")
  private Date discountEnterDate;

  @Column(name = "discount_deadline")
  private Integer discountDeadline;

  @Column(name = "discount_rate")
  private Double discountRate;

  @Column(name = "discount_date")
  private Date discountDate;

  @Column(name = "payment_amount")
  private Double paymentAmount;

  public InvoiceWorkflowEntity() {

  }

  @Override
  public Long getWorkflowId() {

    return workflowId;
  }

  @Override
  public void setWorkflowId(final Long workflowId) {

    this.workflowId = workflowId;
  }

  @Override
  public WorkflowEntity getWorkflow() {

    return workflow;
  }

  @Override
  public void setWorkflow(final WorkflowEntity workflow) {

    this.workflow = workflow;
  }

  public String getSender() {

    return sender;
  }

  public void setSender(final String sender) {

    this.sender = sender;
  }

  public String getRegisterNumber() {

    return registerNumber;
  }

  public void setRegisterNumber(final String registerNumber) {

    this.registerNumber = registerNumber;
  }

  public Date getInvoiceDate() {

    return invoiceDate;
  }

  public void setInvoiceDate(final Date invoiceDate) {

    this.invoiceDate = invoiceDate;
  }

  public String getPartnerCode() {

    return partnerCode;
  }

  public void setPartnerCode(final String partnerCode) {

    this.partnerCode = partnerCode;
  }

  public String getVendorNumber() {

    return vendorNumber;
  }

  public void setVendorNumber(final String vendorNumber) {

    this.vendorNumber = vendorNumber;
  }

  public String getVendorName() {

    return vendorName;
  }

  public void setVendorName(final String vendorName) {

    this.vendorName = vendorName;
  }

  public Boolean getIsDirectDebitPermission() {

    return isDirectDebitPermission;
  }

  public void setIsDirectDebitPermission(final Boolean isDirectDebit) {

    this.isDirectDebitPermission = isDirectDebit;
  }

  public Integer getInvoiceType() {

    return invoiceType;
  }

  public EInvoiceType getInvoiceTypeEnum() {

    return EInvoiceType.ofValue(invoiceType);
  }

  public void setInvoiceType(final EInvoiceType invoiceType) {

    this.invoiceType = invoiceType.getValue();
  }

  public void setInvoiceType(final Integer invoiceType) {

    this.invoiceType = invoiceType;
  }

  public Date getDiscountEnterDate() {

    return discountEnterDate;
  }

  public void setDiscountEnterDate(final Date discountEnterDate) {

    this.discountEnterDate = discountEnterDate;
  }

  public Integer getDiscountDeadline() {

    return discountDeadline;
  }

  public void setDiscountDeadline(final Integer discountDeadline) {

    this.discountDeadline = discountDeadline;
  }

  public Double getDiscountRate() {

    return discountRate;
  }

  public void setDiscountRate(final Double discountRate) {

    this.discountRate = discountRate;
  }

  public Date getDiscountDate() {

    return discountDate;
  }

  public void setDiscountDate(final Date discountDate) {

    this.discountDate = discountDate;
  }

  public Double getPaymentAmount() {

    return paymentAmount;
  }

  public void setPaymentAmount(final Double paymentAmount) {

    this.paymentAmount = paymentAmount;
  }


}
