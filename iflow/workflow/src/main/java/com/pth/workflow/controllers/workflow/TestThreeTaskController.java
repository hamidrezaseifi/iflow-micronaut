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
import com.pth.iflow.common.models.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.iflow.common.models.edo.workflow.testthreetask.TestThreeTaskWorkflowListEdo;
import com.pth.iflow.common.models.edo.workflow.testthreetask.TestThreeTaskWorkflowSaveRequestEdo;
import com.pth.iflow.common.moduls.security.RestAccessRoles;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.workflow.bl.IWorkflowProcessService;
import com.pth.iflow.workflow.models.mapper.WorkflowModelEdoMapper;
import com.pth.iflow.workflow.models.workflow.testthree.TestThreeTaskWorkflow;

@RestController
@RequestMapping
public class TestThreeTaskController {

  final IWorkflowProcessService<TestThreeTaskWorkflow> workflowService;

  public TestThreeTaskController(@Autowired final IWorkflowProcessService<TestThreeTaskWorkflow> workflowService) {

    this.workflowService = workflowService;
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.TESTTHREETASKWORKFLOW_READ_BY_IDENTITY)
  @PreAuthorize(RestAccessRoles.TestThreeTaskWorkflow.HAS_ROLE_TESTTHREETASK_READ)
  public ResponseEntity<TestThreeTaskWorkflowEdo> readWorkflow(@PathVariable final String identity, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final TestThreeTaskWorkflow model = this.workflowService.getByIdentity(identity, authentication);

    return ControllerHelper.createResponseEntity(request, WorkflowModelEdoMapper.toEdo(model), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.TESTTHREETASKWORKFLOW_CREATE)
  @PreAuthorize(RestAccessRoles.TestThreeTaskWorkflow.HAS_ROLE_TESTTHREETASK_CREATE)
  public ResponseEntity<TestThreeTaskWorkflowListEdo>
      createWorkflow(
          @RequestBody final TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo, final HttpServletRequest request,
          final Authentication authentication) throws Exception {

    final List<TestThreeTaskWorkflow> modelList = this.workflowService
        .create(WorkflowModelEdoMapper.fromEdo(workflowCreateRequestEdo),
            authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new TestThreeTaskWorkflowListEdo(WorkflowModelEdoMapper.toTestThreeTaskWorkflowEdoList(modelList)), HttpStatus.CREATED);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.TESTTHREETASKWORKFLOW_SAVE)
  @PreAuthorize(RestAccessRoles.TestThreeTaskWorkflow.HAS_ROLE_TESTTHREETASK_SAVE)
  public ResponseEntity<TestThreeTaskWorkflowEdo> saveWorkflow(
      @RequestBody final TestThreeTaskWorkflowSaveRequestEdo requestEdo,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final TestThreeTaskWorkflow model = this.workflowService.save(WorkflowModelEdoMapper.fromEdo(requestEdo), authentication);

    return ControllerHelper.createResponseEntity(request, WorkflowModelEdoMapper.toEdo(model), HttpStatus.ACCEPTED);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.TESTTHREETASKWORKFLOW_READ_LIST)
  @PreAuthorize(RestAccessRoles.TestThreeTaskWorkflow.HAS_ROLE_TESTTHREETASK_READ)
  public ResponseEntity<TestThreeTaskWorkflowListEdo>
      readWorkflowList(@RequestBody final Set<String> idList,
          final HttpServletRequest request,
          final Authentication authentication) throws Exception {

    final List<TestThreeTaskWorkflow> modelList = this.workflowService.getListByIdentityList(idList, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new TestThreeTaskWorkflowListEdo(WorkflowModelEdoMapper.toTestThreeTaskWorkflowEdoList(modelList)), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.TESTTHREETASKWORKFLOW_READ_LIST_BY_USERIDENTITY)
  @PreAuthorize(RestAccessRoles.TestThreeTaskWorkflow.HAS_ROLE_TESTTHREETASK_READ)
  public ResponseEntity<TestThreeTaskWorkflowListEdo>
      readWorkflowListForUser(@PathVariable final String Identity,
          @PathVariable(required = false) final int status, final HttpServletRequest request,
          final Authentication authentication) throws Exception {

    final List<TestThreeTaskWorkflow> modelList = this.workflowService.getListForUser(Identity, status, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new TestThreeTaskWorkflowListEdo(WorkflowModelEdoMapper.toTestThreeTaskWorkflowEdoList(modelList)), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.TESTTHREETASKWORKFLOW_VALIDATE)
  @PreAuthorize(RestAccessRoles.TestThreeTaskWorkflow.HAS_ROLE_TESTTHREETASK_READ)
  public void validateWorkflowRequest(
      @RequestBody final TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    this.workflowService.validate(WorkflowModelEdoMapper.fromEdo(workflowCreateRequestEdo), authentication);

  }

}
