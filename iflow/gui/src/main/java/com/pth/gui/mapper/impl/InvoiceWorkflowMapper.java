package com.pth.gui.mapper.impl;

import com.pth.common.edo.enums.EInvoiceType;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.IInvoiceWorkflowMapper;
import com.pth.gui.mapper.ISingleTaskWorkflowMapper;
import com.pth.gui.mapper.IWorkflowActionMapper;
import com.pth.gui.mapper.IWorkflowFileMapper;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflow;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;

import javax.inject.Singleton;

@Singleton
public class InvoiceWorkflowMapper extends ModelEdoMapperBase<InvoiceWorkflow, InvoiceWorkflowEdo>
        implements IInvoiceWorkflowMapper {

    private final IWorkflowFileMapper workflowFileMapper;
    private final IWorkflowActionMapper workflowActionMapper;

    public InvoiceWorkflowMapper(IWorkflowFileMapper workflowFileMapper,
                                 IWorkflowActionMapper workflowActionMapper) {
        this.workflowFileMapper = workflowFileMapper;
        this.workflowActionMapper = workflowActionMapper;
    }

    @Override
    public InvoiceWorkflow fromEdo(InvoiceWorkflowEdo edo) {
        final InvoiceWorkflow model = new InvoiceWorkflow();

        model.setComments(edo.getWorkflow().getComments());
        model.setStatus(edo.getWorkflow().getStatus());
        model.setVersion(edo.getWorkflow().getVersion());
        model.setControllerId(edo.getWorkflow().getControllerId());
        model.setCurrentStepId(edo.getWorkflow().getCurrentStepId());
        model.setCreatedById(edo.getWorkflow().getCreatedById());
        model.setIdentity(edo.getWorkflow().getIdentity());
        model.setCompanyId(edo.getWorkflow().getCompanyId());

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

        model.setFiles(workflowFileMapper.fromEdoList(edo.getWorkflow().getFiles()));
        model.setActions(workflowActionMapper.fromEdoList(edo.getWorkflow().getActions()));

        return model;
    }

    @Override
    public InvoiceWorkflowEdo toEdo(InvoiceWorkflow model) {

        final WorkflowEdo workflowEdo = new WorkflowEdo();
        workflowEdo.setComments(model.getComments());
        workflowEdo.setStatus(model.getStatusInt());
        workflowEdo.setControllerId(model.getControllerId());
        workflowEdo.setCurrentStepId(model.getCurrentStepId());
        workflowEdo.setCreatedById(model.getCreatedById());
        workflowEdo.setVersion(model.getVersion());
        workflowEdo.setId(model.getId());
        workflowEdo.setWorkflowTypeId(model.getWorkflowTypeId());
        workflowEdo.setCompanyId(model.getCompanyId());

        workflowEdo.setFiles(workflowFileMapper.toEdoList(model.getFiles()));
        workflowEdo.setActions(workflowActionMapper.toEdoList(model.getActions()));

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
