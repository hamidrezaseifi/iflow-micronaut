package com.pth.gui.mapper.impl;

import com.pth.common.edo.enums.EInvoiceType;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.*;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflow;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;
import com.pth.gui.models.workflow.workflow.Workflow;

import javax.inject.Singleton;

@Singleton
public class InvoiceWorkflowMapper extends ModelEdoMapperBase<InvoiceWorkflow, InvoiceWorkflowEdo>
        implements IInvoiceWorkflowMapper {

    private final IWorkflowMapper workflowMapper;

    public InvoiceWorkflowMapper(IWorkflowMapper workflowMapper) {

        this.workflowMapper = workflowMapper;
    }

    @Override
    public InvoiceWorkflow fromEdo(InvoiceWorkflowEdo edo) {
        final InvoiceWorkflow model = new InvoiceWorkflow();

        Workflow workflow = workflowMapper.fromEdo(edo.getWorkflow());

        model.setWorkflow(workflow);

        model.setDiscountDate(edo.getDiscountDate());
        model.setDiscountDeadline(edo.getDiscountDeadline());
        model.setDiscountEnterDate(edo.getDiscountEnterDate());
        model.setDiscountRate(edo.getDiscountRate());
        model.setInvoiceDate(edo.getInvoiceDate());
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
    public InvoiceWorkflowEdo toEdo(InvoiceWorkflow model) {

        final WorkflowEdo workflowEdo = workflowMapper.toEdo(model.getWorkflow());

        final InvoiceWorkflowEdo edo = new InvoiceWorkflowEdo();
        edo.setWorkflow(workflowEdo);

        edo.setDiscountDate(model.getDiscountDate());
        edo.setDiscountDeadline(model.getDiscountDeadline());
        edo.setDiscountEnterDate(model.getDiscountEnterDate());
        edo.setDiscountRate(model.getDiscountRate());
        edo.setInvoiceDate(model.getInvoiceDate());
        edo.setInvoiceType(model.getInvoiceType().getValue());
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
