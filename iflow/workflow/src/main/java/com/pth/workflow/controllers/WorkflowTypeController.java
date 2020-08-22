package com.pth.workflow.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.WorkflowTypeEdo;
import com.pth.common.edo.WorkflowTypeListEdo;
import com.pth.workflow.entities.workflow.WorkflowTypeEntity;
import com.pth.workflow.mapper.IWorkflowTypeMapper;
import com.pth.workflow.mapper.IWorkflowTypeStepMapper;
import com.pth.workflow.services.bl.IWorkflowTypeProcessService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOWTYPE_ROOT)
public class WorkflowTypeController {

  final IWorkflowTypeProcessService workflowTypeProcessService;
  final IWorkflowTypeMapper workflowTypeMapper;

  public WorkflowTypeController(final IWorkflowTypeProcessService workflowTypeProcessService, 
                                IWorkflowTypeMapper workflowTypeMapper) {

    this.workflowTypeProcessService = workflowTypeProcessService;
    this.workflowTypeMapper = workflowTypeMapper;
  }

  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/readbyidentity/{identity}")
  public HttpResponse<WorkflowTypeEdo> readWorkflowType(final String identity,
                                                        final Authentication authentication) throws Exception {

    final Optional<WorkflowTypeEntity> modelOptional = this.workflowTypeProcessService.getByIdentity(identity);

    if(modelOptional.isPresent()){
      return HttpResponse.ok(workflowTypeMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Post(value = "/readbyidentitylist")
  public HttpResponse<WorkflowTypeListEdo> readWorkflowList(@Body @Valid final Set<String> idList,
                                                            final Authentication authentication) throws Exception {

    final List<WorkflowTypeEntity> modelList = this.workflowTypeProcessService.getListByIdentityList(idList);

    return HttpResponse.ok(new WorkflowTypeListEdo(workflowTypeMapper.toEdoList(modelList)));
  }

  @Secured(SecurityRule.IS_AUTHENTICATED)
  @Get(value = "/readbycompanyidentity/{identity}")
  public HttpResponse<WorkflowTypeListEdo> readWorkflowListByCompany(final String identity,
      final Authentication authentication) throws Exception {

    final List<WorkflowTypeEntity> modelList = this.workflowTypeProcessService.getListByCompanyIdentity(identity);

    return HttpResponse.ok(new WorkflowTypeListEdo(workflowTypeMapper.toEdoList(modelList)));
  }

}
