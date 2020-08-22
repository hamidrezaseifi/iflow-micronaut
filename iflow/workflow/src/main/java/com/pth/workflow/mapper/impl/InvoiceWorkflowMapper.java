package com.pth.workflow.mapper.impl;

import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.mapper.IInvoiceWorkflowMapper;

import javax.inject.Singleton;

@Singleton
public class InvoiceWorkflowMapper extends ModelEdoMapperBase<InvoiceWorkflowEntity, InvoiceWorkflowEdo>
        implements IInvoiceWorkflowMapper {
    @Override
    public InvoiceWorkflowEntity fromEdo(InvoiceWorkflowEdo edo) {
        InvoiceWorkflowEntity model = MappingUtils.copyProperties(edo, new InvoiceWorkflowEntity());

        return model;
    }

    @Override
    public InvoiceWorkflowEdo toEdo(InvoiceWorkflowEntity model) {
        InvoiceWorkflowEdo edo = MappingUtils.copyProperties(model, new InvoiceWorkflowEdo());

        return edo;
    }
}
