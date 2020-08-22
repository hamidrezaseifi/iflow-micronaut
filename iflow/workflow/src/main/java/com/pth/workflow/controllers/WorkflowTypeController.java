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
import com.pth.iflow.common.models.edo.WorkflowTypeEdo;
import com.pth.iflow.common.models.edo.WorkflowTypeListEdo;
import com.pth.iflow.common.moduls.security.RestAccessRoles;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.workflow.bl.IWorkflowTypeProcessService;
import com.pth.iflow.workflow.models.WorkflowType;
import com.pth.iflow.workflow.models.mapper.WorkflowModelEdoMapper;

@RestController
@RequestMapping
public class WorkflowTypeController {

  final IWorkflowTypeProcessService workflowTypeProcessService;

  public WorkflowTypeController(@Autowired final IWorkflowTypeProcessService workflowTypeProcessService) {

    this.workflowTypeProcessService = workflowTypeProcessService;
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.General.HAS_ROLE_USER)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOWTYPE_READ_BY_IDENTITY)
  public ResponseEntity<WorkflowTypeEdo> readWorkflowType(@PathVariable final String identity, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final WorkflowType model = this.workflowTypeProcessService.getByIdentity(identity, authentication);

    return ControllerHelper.createResponseEntity(request, WorkflowModelEdoMapper.toEdo(model), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.General.HAS_ROLE_USER)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOWTYPE_READ_LIST)
  public ResponseEntity<WorkflowTypeListEdo> readWorkflowList(@RequestBody final Set<String> idList, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<WorkflowType> modelList = this.workflowTypeProcessService.getListByIdentityList(idList, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new WorkflowTypeListEdo(WorkflowModelEdoMapper.toWorkflowTypeEdoList(modelList)), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.General.HAS_ROLE_USER)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOWTYPE_READ_LIST_BY_COMPANYIDENTITY)
  public ResponseEntity<WorkflowTypeListEdo> readWorkflowListByCompany(@PathVariable final String identity,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<WorkflowType> modelList = this.workflowTypeProcessService.getListByCompanyIdentity(identity, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new WorkflowTypeListEdo(WorkflowModelEdoMapper.toWorkflowTypeEdoList(modelList)), HttpStatus.OK);
  }

}
