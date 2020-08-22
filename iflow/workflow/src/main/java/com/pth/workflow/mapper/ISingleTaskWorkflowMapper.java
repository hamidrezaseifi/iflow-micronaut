package com.pth.workflow.mapper;


import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.mapping.IModelEdoMapper;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.entities.workflow.SingleTaskWorkflowEntity;

public interface ISingleTaskWorkflowMapper extends IModelEdoMapper<SingleTaskWorkflowEntity, SingleTaskWorkflowEdo> {
    
}
