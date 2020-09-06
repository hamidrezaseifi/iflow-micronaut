package com.pth.clients.workflow;

import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowListEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;

import java.util.Optional;
import java.util.UUID;

public interface IInvoiceWorkflowClient {

    Optional<InvoiceWorkflowEdo> readInvoice(String authorization,
                                             final UUID id);

    Optional<InvoiceWorkflowEdo> create(String authorization,
                                        InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo);

    Optional<InvoiceWorkflowEdo> save(String authorization,
                                      InvoiceWorkflowSaveRequestEdo requestEdo);

    Optional<InvoiceWorkflowListEdo> readListForUser(String authorization,
                                                     final UUID id,
                                                     final int status);

    Optional<?> validate(String authorization,
                         InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo);
    
}
