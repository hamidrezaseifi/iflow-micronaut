package com.pth.workflow.mapper;


import com.pth.common.edo.WorkflowTypeStepEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.mapping.IModelEdoMapper;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeStepEntity;

public interface IInvoiceWorkflowMapper extends IModelEdoMapper<InvoiceWorkflowEntity, InvoiceWorkflowEdo> {
    
}
