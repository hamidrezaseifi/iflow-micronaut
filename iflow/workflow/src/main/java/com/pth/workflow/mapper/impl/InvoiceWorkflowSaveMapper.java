package com.pth.workflow.mapper.impl;

import com.pth.common.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.mapper.IInvoiceWorkflowSaveRequestMapper;
import com.pth.workflow.models.workflow.InvoiceWorkflowSaveRequest;

import javax.inject.Singleton;

@Singleton
public class InvoiceWorkflowSaveMapper
        extends ModelEdoMapperBase<InvoiceWorkflowSaveRequest, InvoiceWorkflowSaveRequestEdo>
        implements IInvoiceWorkflowSaveRequestMapper {
    @Override
    public InvoiceWorkflowSaveRequest fromEdo(InvoiceWorkflowSaveRequestEdo edo) {
        InvoiceWorkflowSaveRequest model = MappingUtils.copyProperties(edo, new InvoiceWorkflowSaveRequest());

        return model;
    }

    @Override
    public InvoiceWorkflowSaveRequestEdo toEdo(InvoiceWorkflowSaveRequest model) {
        InvoiceWorkflowSaveRequestEdo edo = MappingUtils.copyProperties(model, new InvoiceWorkflowSaveRequestEdo());

        return edo;
    }
}
