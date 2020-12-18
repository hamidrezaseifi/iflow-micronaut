package com.pth.gui.controllers.workflow;


import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflow;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflowSaveRequest;
import com.pth.gui.services.IBasicWorkflowHandler;
import com.pth.gui.services.ICompanyHandler;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.impl.workflow.invoice.InvoiceBasicWorkflowHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/workflow/invoice/data")
public class InvoiceWorkflowDataController extends WorkflowDataControllerBase<InvoiceWorkflow, InvoiceWorkflowSaveRequest> {

  public InvoiceWorkflowDataController(InvoiceBasicWorkflowHandler workflowHandler,
                                       IUploadFileManager uploadFileManager,
                                       ICompanyHandler companyHandler) {
    super(workflowHandler, uploadFileManager, companyHandler);
  }

  @Post("/initcreate" )
  public HttpResponse<Map<String, Object>> loadWorkflowCreateData(Session session) {

    final Map<String, Object> map = new HashMap<>();

    final InvoiceWorkflow newWorkflow = this.generateInitialWorkflow(this.getCurrentUserId(session), session);

    this.setWorkflowController(newWorkflow, session);

    final InvoiceWorkflowSaveRequest workflowReq =
            this.generateInitialWorkflowSaveRequest(newWorkflow,
                                                    newWorkflow.getHasActiveAction() ?
                                                    newWorkflow.getActiveAction().getCurrentStep().getExpireDays() :
                                                    15);

    map.put("workflowSaveRequest", workflowReq);
    map.put("ocrPresetList",
            this.companyHandler.readCompanyWorkflowtypeItemOcrSettings(this.getCompanyId(session),
                                                                       this.getLoggedToken(session)));

    return HttpResponse.ok(map);
  }


  @Override
  protected InvoiceWorkflow generateInitialWorkflow(final UUID userId, Session session) {

    final InvoiceWorkflow workflow = InvoiceWorkflow.generateInitial(userId);
    workflow.setWorkflowType(this.getWorkflowTypeByEnumType(EWorkflowType.INVOICE_WORKFLOW_TYPE, session));
    workflow.setIsDirectDebitPermission(false);
    return workflow;
  }

  @Override
  protected InvoiceWorkflowSaveRequest generateInitialWorkflowSaveRequest(final InvoiceWorkflow workflow, final int expireDays) {

    return InvoiceWorkflowSaveRequest.generateNewWihExpireDays(workflow, expireDays);
  }

}
