package com.pth.workflow.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.WorkflowTypeStepEdo;
import com.pth.common.edo.WorkflowTypeStepListEdo;
import com.pth.workflow.entities.workflow.WorkflowTypeStepEntity;
import com.pth.workflow.mapper.IWorkflowTypeStepMapper;
import com.pth.workflow.services.bl.IWorkflowTypeStepProcessService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOWTYPESTEP_ROOT)
public class WorkflowTypeStepController {

  final IWorkflowTypeStepProcessService workflowStepProcessService;
  final IWorkflowTypeStepMapper workflowTypeStepMapper;

  public WorkflowTypeStepController(IWorkflowTypeStepProcessService workflowStepProcessService,
                                    IWorkflowTypeStepMapper workflowTypeStepMapper) {

    this.workflowStepProcessService = workflowStepProcessService;
    this.workflowTypeStepMapper = workflowTypeStepMapper;
  }

  @Produces(MediaType.APPLICATION_JSON)
  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/readbyidentity/{identity}")
  public HttpResponse<WorkflowTypeStepEdo> readByIdentity(final String identity, 
                                                          final Authentication authentication) throws Exception {

    final WorkflowTypeStepEntity model = this.workflowStepProcessService.getByIdentity(identity, authentication);

    return HttpResponse.ok(workflowTypeStepMapper.toEdo(model));
  }


  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Post(value = "/readbyidentitylist")
  public HttpResponse<WorkflowTypeStepListEdo> readByList(@Body @Valid final Set<String> idList,
                                                          final Authentication authentication) throws Exception {

    final List<WorkflowTypeStepEntity>
            modelList = this.workflowStepProcessService.getListByIdentityList(idList, authentication);

    return HttpResponse.ok(new WorkflowTypeStepListEdo(workflowTypeStepMapper.toEdoList(modelList)));
  }


  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/readbyworkflowidentity/{identity}")
  public HttpResponse<WorkflowTypeStepListEdo> readListByWorkflowId(final String identity,
      
      final Authentication authentication) throws Exception {

    final List<WorkflowTypeStepEntity> modelList = this.workflowStepProcessService.getListByWorkflowIdentity(identity, authentication);

    return HttpResponse.ok(new WorkflowTypeStepListEdo(workflowTypeStepMapper.toEdoList(modelList)));
  }

}
