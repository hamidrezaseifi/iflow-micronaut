package com.pth.workflow.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pth.iflow.common.annotations.IflowGetRequestMapping;
import com.pth.iflow.common.annotations.IflowPostRequestMapping;
import com.pth.iflow.common.controllers.helper.ControllerHelper;
import com.pth.iflow.common.models.edo.WorkflowTypeStepEdo;
import com.pth.iflow.common.models.edo.WorkflowTypeStepListEdo;
import com.pth.iflow.common.moduls.security.RestAccessRoles;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.workflow.bl.IWorkflowTypeStepProcessService;
import com.pth.iflow.workflow.models.WorkflowTypeStep;
import com.pth.iflow.workflow.models.mapper.WorkflowModelEdoMapper;

@RestController
@RequestMapping
public class WorkflowTypeStepController {

  final IWorkflowTypeStepProcessService workflowStepProcessService;

  public WorkflowTypeStepController(@Autowired final IWorkflowTypeStepProcessService workflowStepProcessService) {

    this.workflowStepProcessService = workflowStepProcessService;
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.General.HAS_ROLE_USER)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOWTYPESTEP_READ_BY_IDENTITY)
  public ResponseEntity<WorkflowTypeStepEdo> readDepartmentGroup(@PathVariable final String identity, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final WorkflowTypeStep model = this.workflowStepProcessService.getByIdentity(identity, authentication);

    return ControllerHelper.createResponseEntity(request, WorkflowModelEdoMapper.toEdo(model), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.General.HAS_ROLE_USER)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOWTYPESTEP_READ_LIST)
  public ResponseEntity<WorkflowTypeStepListEdo> readByList(@RequestBody final Set<String> idList, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<WorkflowTypeStep> modelList = this.workflowStepProcessService.getListByIdentityList(idList, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new WorkflowTypeStepListEdo(WorkflowModelEdoMapper.toWorkflowTypeStepEdoList(modelList)), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.General.HAS_ROLE_USER)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOWTYPESTEP_READ_LIST_BY_WORKFLOWIDENTITY)
  public ResponseEntity<WorkflowTypeStepListEdo> readListByWorkflowId(@PathVariable final String identity,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<WorkflowTypeStep> modelList = this.workflowStepProcessService.getListByWorkflowIdentity(identity, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new WorkflowTypeStepListEdo(WorkflowModelEdoMapper.toWorkflowTypeStepEdoList(modelList)), HttpStatus.OK);
  }

}
