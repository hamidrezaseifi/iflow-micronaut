package com.pth.workflow.controllers.workflow;


import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.WorkflowListEdo;
import com.pth.workflow.entities.WorkflowEntity;
import com.pth.workflow.mapper.IWorkflowMapper;
import com.pth.workflow.mapper.IWorkflowSearchFilterMapper;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import com.pth.workflow.services.bl.IWorkflowSearchService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Named;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOW_ROOT)
public class WorkflowController {

  private final IWorkflowSearchService workflowSearchService;
  private final IWorkflowProcessService<WorkflowEntity> workflowService;
  private final IWorkflowMapper workflowMapper;
  private final IWorkflowSearchFilterMapper workflowSearchFilterMapper;

  public WorkflowController(IWorkflowSearchService workflowSearchService,
                            @Named("workflowProcessService") IWorkflowProcessService<WorkflowEntity> workflowService,
                            IWorkflowMapper workflowMapper,
                            IWorkflowSearchFilterMapper workflowSearchFilterMapper) {

    this.workflowSearchService = workflowSearchService;
    this.workflowService = workflowService;
    this.workflowMapper = workflowMapper;
    this.workflowSearchFilterMapper = workflowSearchFilterMapper;
  }

  @Post(value = "/search")
  public HttpResponse<WorkflowListEdo>
    searchWorkflow(@Body @Valid final WorkflowSearchFilterEdo workflowSearchFilterEdo,
                   @Header String authorization) throws Exception {

    final List<WorkflowEntity> modelList =
            this.workflowSearchService.search(workflowSearchFilterMapper.fromEdo(workflowSearchFilterEdo));

    return HttpResponse.ok(new WorkflowListEdo(workflowMapper.toEdoList(modelList)));
  }

  @Get(value = "/read/{id}")
  public HttpResponse<WorkflowEdo> readWorkflow(final UUID id,
                                                @Header String authorization) throws Exception {

    final Optional<WorkflowEntity> modelOptional = this.workflowService.getById(id);

    if(modelOptional.isPresent()){
      return HttpResponse.ok(workflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

}
