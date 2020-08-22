package com.pth.workflow.services.bl.impl.workflowservice.invoice;


import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.entities.workflow.WorkflowActionEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.entities.workflow.WorkflowTypeStepEntity;
import com.pth.workflow.repositories.IInvoiceWorkflowRepository;
import com.pth.workflow.repositories.IWorkflowTypeRepository;
import com.pth.workflow.services.bl.IWorkflowPrepare;
import com.pth.workflow.services.bl.WorkflowPrepareBase;
import io.micronaut.security.authentication.Authentication;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;


@Singleton
public class InvoiceWorkflowPrepare extends WorkflowPrepareBase<InvoiceWorkflowEntity> {

  public InvoiceWorkflowPrepare(IWorkflowTypeRepository workflowTypeRepository) {
    super(workflowTypeRepository);
  }

}
