package com.pth.workflow.controllers.workflow;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowListEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowSaveRequestEdo;
import com.pth.common.enums.UserRoles;
import com.pth.workflow.entities.SingleTaskWorkflowEntity;
import com.pth.workflow.mapper.ISingleTaskWorkflowMapper;
import com.pth.workflow.mapper.ISingleTaskWorkflowSaveRequestMapper;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_SINGLETASKWORKFLOW_ROOT)
public class SingleTaskController {

  private final IWorkflowProcessService<SingleTaskWorkflowEntity> singleTaskWorkflowService;
  private final ISingleTaskWorkflowMapper singleTaskWorkflowMapper;
  private final ISingleTaskWorkflowSaveRequestMapper singleTaskWorkflowSaveRequestMapper;

  public SingleTaskController(IWorkflowProcessService<SingleTaskWorkflowEntity> singleTaskWorkflowService,
                           ISingleTaskWorkflowMapper singleTaskWorkflowMapper,
                           ISingleTaskWorkflowSaveRequestMapper singleTaskWorkflowSaveRequestMapper) {

    this.singleTaskWorkflowService = singleTaskWorkflowService;
    this.singleTaskWorkflowMapper = singleTaskWorkflowMapper;
    this.singleTaskWorkflowSaveRequestMapper = singleTaskWorkflowSaveRequestMapper;
  }

  @Get(value = "/read/{id}")
  public HttpResponse<SingleTaskWorkflowEdo> readInvoice(final UUID id,
                                                         @Header String authorization) throws Exception {

    final Optional<SingleTaskWorkflowEntity> modelOptional =
            this.singleTaskWorkflowService.getById(id);

    if(modelOptional.isPresent()){
      return HttpResponse.ok(singleTaskWorkflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

  @Secured({UserRoles.ROLE_DATAENTRY, UserRoles.ROLE_ADMIN})
  @Post(value = "/create")
  public HttpResponse<SingleTaskWorkflowListEdo> createInvoice(
          @Body @Valid final SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo,
          @Header String authorization) throws Exception {

    final List<SingleTaskWorkflowEntity> modelList =
            this.singleTaskWorkflowService.create(
                    singleTaskWorkflowSaveRequestMapper.fromEdo(workflowCreateRequestEdo),
                    authorization);

    return HttpResponse.created(new SingleTaskWorkflowListEdo(singleTaskWorkflowMapper.toEdoList(modelList)));
  }

  @Secured({UserRoles.ROLE_DATAENTRY, UserRoles.ROLE_ADMIN})
  @Post(value = "/save")
  public HttpResponse<SingleTaskWorkflowEdo>
    saveInvoice(@Body @Valid final SingleTaskWorkflowSaveRequestEdo requestEdo,
                @Header String authorization) throws Exception {

    final Optional<SingleTaskWorkflowEntity> modelOptional =
            this.singleTaskWorkflowService.save(singleTaskWorkflowSaveRequestMapper.fromEdo(requestEdo),
                                                authorization);

    if(modelOptional.isPresent()){
      return HttpResponse.created(singleTaskWorkflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.badRequest();
  }

  @Post(value = "/readbyidentitylist")
  public HttpResponse<SingleTaskWorkflowListEdo> readInvoiceList(@Body @Valid final Set<String> idList,
                                                                 @Header String authorization) throws Exception {

    final List<SingleTaskWorkflowEntity> modelList =
            this.singleTaskWorkflowService.getListByIdentityList(idList);

    return HttpResponse.ok(new SingleTaskWorkflowListEdo(singleTaskWorkflowMapper.toEdoList(modelList)));
  }

  @Get(value = "/readbyuserid/{id}/{status}")
  public HttpResponse<SingleTaskWorkflowListEdo> readInvoiceListForUser(final UUID id,
                                                                        @PathVariable(defaultValue = "0") final int status,
                                                                        @Header String authorization) throws Exception {

    final List<SingleTaskWorkflowEntity> modelList =
            this.singleTaskWorkflowService.getListForUser(id, status);

    return HttpResponse.ok(new SingleTaskWorkflowListEdo(singleTaskWorkflowMapper.toEdoList(modelList)));
  }

  @Status(HttpStatus.ACCEPTED)
  @Secured({UserRoles.ROLE_DATAENTRY, UserRoles.ROLE_ADMIN})
  @Post(value = "/validate")
  public void
  validateInvoiceRequest(@Body @Valid final SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo,
                         @Header String authorization) throws Exception {

    this.singleTaskWorkflowService.validate(singleTaskWorkflowSaveRequestMapper.fromEdo(workflowCreateRequestEdo),
                                            authorization);

  }

}
