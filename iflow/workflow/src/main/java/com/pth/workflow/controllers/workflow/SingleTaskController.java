package com.pth.workflow.controllers.workflow;

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
import com.pth.iflow.common.models.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.iflow.common.models.edo.workflow.singletask.SingleTaskWorkflowListEdo;
import com.pth.iflow.common.models.edo.workflow.singletask.SingleTaskWorkflowSaveRequestEdo;
import com.pth.iflow.common.moduls.security.RestAccessRoles;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.workflow.bl.IWorkflowProcessService;
import com.pth.iflow.workflow.models.mapper.WorkflowModelEdoMapper;
import com.pth.iflow.workflow.models.workflow.singletask.SingleTaskWorkflow;

@RestController
@RequestMapping
public class SingleTaskController {

  final IWorkflowProcessService<SingleTaskWorkflow> workflowService;

  public SingleTaskController(@Autowired final IWorkflowProcessService<SingleTaskWorkflow> workflowService) {

    this.workflowService = workflowService;
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.SingleTaskWorkflow.HAS_ROLE_SINGLETASK_READ)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.SINGLETASKWORKFLOW_READ_BY_IDENTITY)
  public ResponseEntity<SingleTaskWorkflowEdo> readWorkflow(@PathVariable final String identity, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final SingleTaskWorkflow model = this.workflowService.getByIdentity(identity, authentication);

    return ControllerHelper.createResponseEntity(request, WorkflowModelEdoMapper.toEdo(model), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize(RestAccessRoles.SingleTaskWorkflow.HAS_ROLE_SINGLETASK_CREATE)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.SINGLETASKWORKFLOW_CREATE)
  public ResponseEntity<SingleTaskWorkflowListEdo> createWorkflow(
      @RequestBody final SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<SingleTaskWorkflow> modelList = this.workflowService
        .create(WorkflowModelEdoMapper.fromEdo(workflowCreateRequestEdo),
            authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new SingleTaskWorkflowListEdo(WorkflowModelEdoMapper.toSingleTaskWorkflowEdoList(modelList)), HttpStatus.CREATED);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PreAuthorize(RestAccessRoles.SingleTaskWorkflow.HAS_ROLE_SINGLETASK_SAVE)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.SINGLETASKWORKFLOW_SAVE)
  public ResponseEntity<SingleTaskWorkflowEdo> saveWorkflow(@RequestBody final SingleTaskWorkflowSaveRequestEdo requestEdo,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final SingleTaskWorkflow model = this.workflowService.save(WorkflowModelEdoMapper.fromEdo(requestEdo), authentication);

    return ControllerHelper.createResponseEntity(request, WorkflowModelEdoMapper.toEdo(model), HttpStatus.ACCEPTED);
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.SingleTaskWorkflow.HAS_ROLE_SINGLETASK_READ)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.SINGLETASKWORKFLOW_READ_LIST)
  public ResponseEntity<SingleTaskWorkflowListEdo> readWorkflowList(@RequestBody final Set<String> idList,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<SingleTaskWorkflow> modelList = this.workflowService.getListByIdentityList(idList, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new SingleTaskWorkflowListEdo(WorkflowModelEdoMapper.toSingleTaskWorkflowEdoList(modelList)), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.SINGLETASKWORKFLOW_READ_LIST_BY_USERIDENTITY)
  @PreAuthorize(RestAccessRoles.SingleTaskWorkflow.HAS_ROLE_SINGLETASK_READ)
  public ResponseEntity<SingleTaskWorkflowListEdo> readWorkflowListForUser(@PathVariable final String Identity,
      @PathVariable(required = false) final int status, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<SingleTaskWorkflow> modelList = this.workflowService.getListForUser(Identity, status, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new SingleTaskWorkflowListEdo(WorkflowModelEdoMapper.toSingleTaskWorkflowEdoList(modelList)), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.SINGLETASKWORKFLOW_VALIDATE)
  @PreAuthorize(RestAccessRoles.SingleTaskWorkflow.HAS_ROLE_SINGLETASK_READ)
  public void validateWorkflowRequest(@RequestBody final SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    this.workflowService.validate(WorkflowModelEdoMapper.fromEdo(workflowCreateRequestEdo), authentication);

  }

}
