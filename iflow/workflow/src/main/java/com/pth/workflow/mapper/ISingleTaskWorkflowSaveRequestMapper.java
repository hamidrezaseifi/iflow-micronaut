package com.pth.workflow.mapper;


import com.pth.common.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowSaveRequestEdo;
import com.pth.common.mapping.IModelEdoMapper;
import com.pth.workflow.models.workflow.InvoiceWorkflowSaveRequest;
import com.pth.workflow.models.workflow.SingleTaskWorkflowSaveRequest;

public interface ISingleTaskWorkflowSaveRequestMapper extends IModelEdoMapper<SingleTaskWorkflowSaveRequest, SingleTaskWorkflowSaveRequestEdo> {
    
}
