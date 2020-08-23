package com.pth.workflow.services.bl.impl.workflowservice.invoice;


import com.pth.workflow.entities.InvoiceWorkflowEntity;
import com.pth.workflow.repositories.IWorkflowTypeRepository;
import com.pth.workflow.services.bl.WorkflowPrepareBase;

import javax.inject.Singleton;


@Singleton
public class InvoiceWorkflowPrepare extends WorkflowPrepareBase<InvoiceWorkflowEntity> {

  public InvoiceWorkflowPrepare(IWorkflowTypeRepository workflowTypeRepository) {
    super(workflowTypeRepository);
  }

}
