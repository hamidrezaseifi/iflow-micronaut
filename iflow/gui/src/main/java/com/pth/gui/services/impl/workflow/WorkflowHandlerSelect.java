package com.pth.gui.services.impl.workflow;


import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflow;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflowSaveRequest;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflowSaveRequest;
import com.pth.gui.models.workflow.testthree.TestThreeTaskWorkflow;
import com.pth.gui.models.workflow.testthree.TestThreeTaskWorkflowSaveRequest;
import com.pth.gui.models.workflow.workflow.Workflow;
import com.pth.gui.models.workflow.workflow.WorkflowSaveRequest;
import com.pth.gui.services.IBasicWorkflowHandler;

import javax.inject.Singleton;

@Singleton
public class WorkflowHandlerSelect {

  private final IBasicWorkflowHandler<Workflow, WorkflowSaveRequest> workflowHandlerBase;

  private final IBasicWorkflowHandler<InvoiceWorkflow, InvoiceWorkflowSaveRequest> workflowHandlerInvoice;

  private final IBasicWorkflowHandler<SingleTaskWorkflow, SingleTaskWorkflowSaveRequest> workflowHandlerSingleTask;

  private final IBasicWorkflowHandler<TestThreeTaskWorkflow, TestThreeTaskWorkflowSaveRequest>
          workflowHandlerTestThreeTask;

  public WorkflowHandlerSelect(IBasicWorkflowHandler<Workflow, WorkflowSaveRequest> workflowHandlerBase,
                               IBasicWorkflowHandler<InvoiceWorkflow, InvoiceWorkflowSaveRequest> workflowHandlerInvoice,
                               IBasicWorkflowHandler<SingleTaskWorkflow, SingleTaskWorkflowSaveRequest> workflowHandlerSingleTask,
                               IBasicWorkflowHandler<TestThreeTaskWorkflow, TestThreeTaskWorkflowSaveRequest> workflowHandlerTestThreeTask) {
    this.workflowHandlerBase = workflowHandlerBase;
    this.workflowHandlerInvoice = workflowHandlerInvoice;
    this.workflowHandlerSingleTask = workflowHandlerSingleTask;
    this.workflowHandlerTestThreeTask = workflowHandlerTestThreeTask;
  }

  public IBasicWorkflowHandler getHandlerByType(final EWorkflowType typeEnum) {
    if (typeEnum == EWorkflowType.NONE) {
      return this.workflowHandlerBase;
    }
    if (typeEnum == EWorkflowType.INVOICE_WORKFLOW_TYPE) {
      return this.workflowHandlerInvoice;
    }
    if (typeEnum == EWorkflowType.SINGLE_TASK_WORKFLOW_TYPE) {
      return this.workflowHandlerSingleTask;
    }
    if (typeEnum == EWorkflowType.TESTTHREE_TASK_WORKFLOW_TYPE) {
      return this.workflowHandlerTestThreeTask;
    }

    return null;
  }
}
