package com.pth.workflow.repositories;

import com.pth.workflow.entities.InvoiceWorkflowEntity;

import java.util.Optional;

public interface IInvoiceWorkflowRepository extends IWorkflowBaseRepository<InvoiceWorkflowEntity> {

    Optional<InvoiceWorkflowEntity> getByIdentity(String identity);
}
