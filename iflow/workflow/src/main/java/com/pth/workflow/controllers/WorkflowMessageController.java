package com.pth.workflow.controllers;

import java.util.List;
import java.util.UUID;


import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.WorkflowMessageListEdo;
import com.pth.workflow.entities.workflow.WorkflowMessageEntity;
import com.pth.workflow.mapper.IWorkflowMessageMapper;
import com.pth.workflow.services.IWorkflowMessageService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOWMESSAGE_ROOT)
public class WorkflowMessageController {

  private final IWorkflowMessageService workflowMessageService;
  private final IWorkflowMessageMapper workflowMessageMapper;

  public WorkflowMessageController(IWorkflowMessageService workflowMessageService, IWorkflowMessageMapper workflowMessageMapper) {

    this.workflowMessageService = workflowMessageService;
    this.workflowMessageMapper = workflowMessageMapper;

  }

  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/readbyuserid/{id}/{status}")
  public HttpResponse<WorkflowMessageListEdo>
    readUserWorkflowMessageList(final UUID id,
                                @PathVariable(defaultValue = "0") Integer status,
                                final Authentication authentication,
                                @Header String authorization) throws Exception {

    status = status == null ? 0 : status;

    final List<WorkflowMessageEntity> messageList = this.workflowMessageService.getListForUser(id, status);

    return HttpResponse.ok(new WorkflowMessageListEdo(workflowMessageMapper.toEdoList(messageList)));
  }

  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/readallforworkflow/{id}")
  public HttpResponse<WorkflowMessageListEdo> readWorkfloWorkflowMessageList(final UUID id,
                                                                             final Authentication authentication,
                                                                             @Header String authorization)
          throws Exception {

    final List<WorkflowMessageEntity> messageList = this.workflowMessageService.getListForWorkflow(id);

    return HttpResponse.ok(new WorkflowMessageListEdo(workflowMessageMapper.toEdoList(messageList)));
  }

}
