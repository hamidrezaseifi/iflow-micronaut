package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.entities.workflow.WorkflowEntity;

import java.util.Optional;

public interface IInvoiceWorkflowRepository extends IWorkflowBaseRepository<InvoiceWorkflowEntity> {

    Optional<InvoiceWorkflowEntity> getByIdentity(String identity);
}
