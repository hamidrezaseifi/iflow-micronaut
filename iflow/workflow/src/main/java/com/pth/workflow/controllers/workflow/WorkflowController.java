package com.pth.workflow.controllers.workflow;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

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
