package com.pth.gui.services.impl.workflow;

import com.pth.gui.models.workflow.invoice.InvoiceWorkflow;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflowSaveRequest;
import com.pth.gui.services.IBasicWorkflowHandler;

public interface IInvoiceBasicWorkflowHandler
        extends IBasicWorkflowHandler<InvoiceWorkflow, InvoiceWorkflowSaveRequest> {
}
