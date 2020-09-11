package com.pth.clients.clients.workflow;

import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowListEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;

import java.util.Optional;
import java.util.UUID;

public interface IInvoiceWorkflowClient {

    Optional<InvoiceWorkflowEdo> read(String authorization,
                                             final UUID id);

    Optional<InvoiceWorkflowListEdo> create(String authorization,
                                        InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo);

    Optional<InvoiceWorkflowEdo> save(String authorization,
                                      InvoiceWorkflowSaveRequestEdo requestEdo);

    Optional<InvoiceWorkflowListEdo> readListForUser(String authorization,
                                                     final UUID id,
                                                     final int status);

    void validate(String authorization,
                         InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo);
    
}
