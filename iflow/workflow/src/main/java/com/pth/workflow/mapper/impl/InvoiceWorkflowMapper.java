package com.pth.workflow.mapper.impl;

import com.pth.common.edo.enums.EInvoiceType;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.entities.InvoiceWorkflowEntity;
import com.pth.workflow.mapper.IInvoiceWorkflowMapper;
import com.pth.workflow.mapper.IWorkflowActionMapper;
import com.pth.workflow.mapper.IWorkflowFileMapper;
import com.pth.workflow.mapper.IWorkflowMapper;

import javax.inject.Singleton;
import java.sql.Date;

@Singleton
public class InvoiceWorkflowMapper extends ModelEdoMapperBase<InvoiceWorkflowEntity, InvoiceWorkflowEdo>
        implements IInvoiceWorkflowMapper {

    private final IWorkflowMapper workflowMapper;

    public InvoiceWorkflowMapper(IWorkflowMapper workflowMapper) {
        this.workflowMapper = workflowMapper;

    }

    @Override
    public InvoiceWorkflowEntity fromEdo(InvoiceWorkflowEdo edo) {
        final InvoiceWorkflowEntity model = new InvoiceWorkflowEntity();

        model.setWorkflow(workflowMapper.fromEdo(edo.getWorkflow()));
        model.setWorkflowId(edo.getWorkflow().getId());

        model.setDiscountDate(Date.valueOf(edo.getDiscountDate()));
        model.setDiscountDeadline(edo.getDiscountDeadline());
        model.setDiscountEnterDate(Date.valueOf(edo.getDiscountEnterDate()));
        model.setDiscountRate(edo.getDiscountRate());
        model.setInvoiceDate(Date.valueOf(edo.getInvoiceDate()));
        model.setInvoiceType(EInvoiceType.ofValue(edo.getInvoiceType()));
        model.setIsDirectDebitPermission(edo.getIsDirectDebitPermission());
        model.setPartnerCode(edo.getPartnerCode());
        model.setPaymentAmount(edo.getPaymentAmount());
        model.setRegisterNumber(edo.getRegisterNumber());
        model.setSender(edo.getSender());
        model.setVendorName(edo.getVendorName());
        model.setVendorNumber(edo.getVendorNumber());

        return model;
    }

    @Override
    public InvoiceWorkflowEdo toEdo(InvoiceWorkflowEntity model) {

        final WorkflowEdo workflowEdo = workflowMapper.toEdo(model.getWorkflow());

        final InvoiceWorkflowEdo edo = new InvoiceWorkflowEdo();
        edo.setWorkflow(workflowEdo);

        edo.setDiscountDate(model.getDiscountDate().toLocalDate());
        edo.setDiscountDeadline(model.getDiscountDeadline());
        edo.setDiscountEnterDate(model.getDiscountEnterDate().toLocalDate());
        edo.setDiscountRate(model.getDiscountRate());
        edo.setInvoiceDate(model.getInvoiceDate().toLocalDate());
        edo.setInvoiceType(model.getInvoiceType());
        edo.setIsDirectDebitPermission(model.getIsDirectDebitPermission());
        edo.setPartnerCode(model.getPartnerCode());
        edo.setPaymentAmount(model.getPaymentAmount());
        edo.setRegisterNumber(model.getRegisterNumber());
        edo.setSender(model.getSender());
        edo.setVendorName(model.getVendorName());
        edo.setVendorNumber(model.getVendorNumber());

        return edo;
    }

}
