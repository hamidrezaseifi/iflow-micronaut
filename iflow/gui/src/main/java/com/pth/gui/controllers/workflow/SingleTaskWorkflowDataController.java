package com.pth.gui.controllers.workflow;


import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflow;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflowSaveRequest;
import com.pth.gui.services.IBasicWorkflowHandler;
import com.pth.gui.services.ICompanyHandler;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.impl.workflow.singletask.SingleTaskBasicWorkflowHandler;
import io.micronaut.http.annotation.Controller;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;

import java.util.UUID;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/workflow/singletask/data")
public class SingleTaskWorkflowDataController extends WorkflowDataControllerBase<SingleTaskWorkflow, SingleTaskWorkflowSaveRequest> {

  public SingleTaskWorkflowDataController(SingleTaskBasicWorkflowHandler workflowHandler,
                                          IUploadFileManager uploadFileManager,
                                          ICompanyHandler companyHandler) {
    super(workflowHandler, uploadFileManager, companyHandler);
  }

  @Override
  protected SingleTaskWorkflow generateInitialWorkflow(final UUID userId, Session session) {

    final SingleTaskWorkflow workflow = SingleTaskWorkflow.generateInitial(userId);
    workflow.setWorkflowType(this.getWorkflowTypeByEnumType(EWorkflowType.SINGLE_TASK_WORKFLOW_TYPE, session));
    return workflow;

  }

  @Override
  protected SingleTaskWorkflowSaveRequest generateInitialWorkflowSaveRequest(final SingleTaskWorkflow workflow, final int expireDays) {
    return SingleTaskWorkflowSaveRequest.generateNewWihExpireDays(workflow, expireDays);
  }

}
