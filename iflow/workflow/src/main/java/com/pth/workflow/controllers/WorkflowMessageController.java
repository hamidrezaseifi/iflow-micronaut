package com.pth.workflow.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pth.iflow.common.annotations.IflowGetRequestMapping;
import com.pth.iflow.common.controllers.helper.ControllerHelper;
import com.pth.iflow.common.models.edo.WorkflowMessageListEdo;
import com.pth.iflow.common.moduls.security.RestAccessRoles;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.workflow.bl.IWorkflowMessageDataService;
import com.pth.iflow.workflow.models.WorkflowMessage;
import com.pth.iflow.workflow.models.mapper.WorkflowModelEdoMapper;

@RestController
@RequestMapping
public class WorkflowMessageController {

  final IWorkflowMessageDataService workflowMessageDataService;

  public WorkflowMessageController(@Autowired final IWorkflowMessageDataService workflowMessageDataService) {

    this.workflowMessageDataService = workflowMessageDataService;

  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.General.HAS_ROLE_USER)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOWMESSAGE_READ_BY_USERIDENTITY)
  public ResponseEntity<WorkflowMessageListEdo> readUserWorkflowMessageList(@PathVariable(required = true) final String identity,
      @PathVariable(required = false) Integer status, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    status = status == null ? 0 : status;

    final List<WorkflowMessage> messageList = this.workflowMessageDataService.getListForUser(identity, status, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new WorkflowMessageListEdo(WorkflowModelEdoMapper.toWorkflowMessageEdoList(messageList)), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.General.HAS_ROLE_USER)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOWMESSAGE_READ_BY_WORKFLOWIDENTITY)
  public ResponseEntity<WorkflowMessageListEdo> readWorkfloWorkflowMessageList(@PathVariable(required = true) final String identity,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<WorkflowMessage> messageList = this.workflowMessageDataService.getListForWorkflow(identity, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new WorkflowMessageListEdo(WorkflowModelEdoMapper.toWorkflowMessageEdoList(messageList)), HttpStatus.OK);
  }

}
