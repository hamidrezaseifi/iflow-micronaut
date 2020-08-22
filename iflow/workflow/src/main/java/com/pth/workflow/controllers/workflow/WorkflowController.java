package com.pth.workflow.controllers.workflow;

import java.util.List;

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
import com.pth.iflow.common.models.edo.IdentityListEdo;
import com.pth.iflow.common.models.edo.WorkflowSearchFilterEdo;
import com.pth.iflow.common.models.edo.workflow.WorkflowEdo;
import com.pth.iflow.common.models.edo.workflow.WorkflowListEdo;
import com.pth.iflow.common.moduls.security.RestAccessRoles;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.workflow.bl.IWorkflowProcessService;
import com.pth.iflow.workflow.bl.IWorkflowSearchService;
import com.pth.iflow.workflow.models.mapper.WorkflowModelEdoMapper;
import com.pth.iflow.workflow.models.workflow.Workflow;

@RestController
@RequestMapping
public class WorkflowController {

  final IWorkflowSearchService workflowSearchService;
  final IWorkflowProcessService<Workflow> workflowService;

  public WorkflowController(@Autowired final IWorkflowSearchService workflowSearchService,
      @Autowired final IWorkflowProcessService<Workflow> workflowService) {

    this.workflowSearchService = workflowSearchService;
    this.workflowService = workflowService;
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOW_SEARCH)
  @PreAuthorize(RestAccessRoles.Workflow.HAS_ROLE_WORKFLOW_READ)
  public ResponseEntity<WorkflowListEdo> searchWorkflow(@RequestBody final WorkflowSearchFilterEdo workflowSearchFilterEdo,
      final HttpServletRequest request, final Authentication authentication) throws Exception {

    final List<
        Workflow> modelList = this.workflowSearchService.search(WorkflowModelEdoMapper.fromEdo(workflowSearchFilterEdo), authentication);

    return ControllerHelper
        .createResponseEntity(request, new WorkflowListEdo(WorkflowModelEdoMapper.toWorkflowEdoList(modelList)),
            HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOW_READLISTBY_IDENTITYLIST)
  @PreAuthorize(RestAccessRoles.Workflow.HAS_ROLE_WORKFLOW_READ)
  public ResponseEntity<WorkflowListEdo> readWorkflowList(@RequestBody final IdentityListEdo identityList, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<
        Workflow> modelList = this.workflowSearchService.readWorkflowListByIdentityList(identityList.getIdentityList(), authentication);

    return ControllerHelper
        .createResponseEntity(request, new WorkflowListEdo(WorkflowModelEdoMapper.toWorkflowEdoList(modelList)),
            HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.WORKFLOW_READ_BY_IDENTITY)
  @PreAuthorize(RestAccessRoles.Workflow.HAS_ROLE_WORKFLOW_READ)
  public ResponseEntity<WorkflowEdo> readWorkflow(@PathVariable final String identity, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final Workflow model = this.workflowService.getByIdentity(identity, authentication);

    return ControllerHelper.createResponseEntity(request, WorkflowModelEdoMapper.toEdo(model), HttpStatus.OK);
  }

}
