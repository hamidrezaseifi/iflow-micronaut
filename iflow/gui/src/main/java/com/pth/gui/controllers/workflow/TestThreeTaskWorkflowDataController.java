package com.pth.gui.controllers.workflow;


import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.models.workflow.testthree.TestThreeTaskWorkflow;
import com.pth.gui.models.workflow.testthree.TestThreeTaskWorkflowSaveRequest;
import com.pth.gui.services.IBasicWorkflowHandler;
import com.pth.gui.services.ICompanyHandler;
import com.pth.gui.services.IUploadFileManager;
import com.pth.gui.services.impl.workflow.testthree.TestThreeTaskBasicWorkflowHandler;
import io.micronaut.http.annotation.Controller;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;

import java.util.UUID;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/workflow/testthreetask/data")
public class TestThreeTaskWorkflowDataController
        extends WorkflowDataControllerBase<TestThreeTaskWorkflow, TestThreeTaskWorkflowSaveRequest> {

  public TestThreeTaskWorkflowDataController(TestThreeTaskBasicWorkflowHandler workflowHandler,
                                             IUploadFileManager uploadFileManager,
                                             ICompanyHandler companyHandler) {
    super(workflowHandler, uploadFileManager, companyHandler);
  }

  @Override
  protected TestThreeTaskWorkflow generateInitialWorkflow(final UUID userId, Session session) {

    final TestThreeTaskWorkflow workflow = TestThreeTaskWorkflow.generateInitial(userId);
    workflow.setWorkflowType(this.getWorkflowTypeByEnumType(EWorkflowType.TESTTHREE_TASK_WORKFLOW_TYPE, session));
    return workflow;
  }

  @Override
  protected TestThreeTaskWorkflowSaveRequest generateInitialWorkflowSaveRequest(final TestThreeTaskWorkflow workflow,
      final int expireDays) {
    return TestThreeTaskWorkflowSaveRequest.generateNewWihExpireDays(workflow, expireDays);
  }

}
