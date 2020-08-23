package com.pth.workflow.controllers.workflow;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowListEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowListEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowSaveRequestEdo;
import com.pth.workflow.entities.workflow.SingleTaskWorkflowEntity;
import com.pth.workflow.entities.workflow.SingleTaskWorkflowEntity;
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

  //@PreAuthorize(RestAccessRoles.SingleTaskWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Get(value = "/readbyidentity/{identity}")
  public HttpResponse<SingleTaskWorkflowEdo> readInvoice(final String identity,
                                                         final Authentication authentication,
                                                         @Header String authorization) throws Exception {

    final Optional<SingleTaskWorkflowEntity> modelOptional =
            this.singleTaskWorkflowService.getByIdentity(identity);

    if(modelOptional.isPresent()){
      return HttpResponse.ok(singleTaskWorkflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

  //@PreAuthorize(RestAccessRoles.SingleTaskWorkflowEntity.HAS_ROLE_INVOICE_CREATE)
  @Post(value = "/create")
  public HttpResponse<SingleTaskWorkflowListEdo> createInvoice(
          @Body @Valid final SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo,
          final Authentication authentication,
          @Header String authorization) throws Exception {

    final List<SingleTaskWorkflowEntity> modelList =
            this.singleTaskWorkflowService.create(
                    singleTaskWorkflowSaveRequestMapper.fromEdo(workflowCreateRequestEdo),
                    authorization);

    return HttpResponse.created(new SingleTaskWorkflowListEdo(singleTaskWorkflowMapper.toEdoList(modelList)));
  }

  //@PreAuthorize(RestAccessRoles.SingleTaskWorkflowEntity.HAS_ROLE_INVOICE_SAVE)
  @Post(value = "/save")
  public HttpResponse<SingleTaskWorkflowEdo>
  saveInvoice(@Body @Valid final SingleTaskWorkflowSaveRequestEdo requestEdo,
              final Authentication authentication,
              @Header String authorization) throws Exception {

    final Optional<SingleTaskWorkflowEntity> modelOptional =
            this.singleTaskWorkflowService.save(singleTaskWorkflowSaveRequestMapper.fromEdo(requestEdo),
                                                authorization);

    if(modelOptional.isPresent()){
      return HttpResponse.created(singleTaskWorkflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.badRequest();
  }

  //@PreAuthorize(RestAccessRoles.SingleTaskWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Post(value = "/readbyidentitylist")
  public HttpResponse<SingleTaskWorkflowListEdo> readInvoiceList(@Body @Valid final Set<String> idList,
                                                                 final Authentication authentication,
                                                                 @Header String authorization) throws Exception {

    final List<SingleTaskWorkflowEntity> modelList =
            this.singleTaskWorkflowService.getListByIdentityList(idList);

    return HttpResponse.ok(new SingleTaskWorkflowListEdo(singleTaskWorkflowMapper.toEdoList(modelList)));
  }

  //@PreAuthorize(RestAccessRoles.SingleTaskWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Get(value = "/readbyuseridentity/{id}/{status}")
  public HttpResponse<SingleTaskWorkflowListEdo> readInvoiceListForUser(final UUID id,
                                                                        @PathVariable(defaultValue = "0") final int status,
                                                                        final Authentication authentication,
                                                                        @Header String authorization) throws Exception {

    final List<SingleTaskWorkflowEntity> modelList =
            this.singleTaskWorkflowService.getListForUser(id, status);

    return HttpResponse.ok(new SingleTaskWorkflowListEdo(singleTaskWorkflowMapper.toEdoList(modelList)));
  }

  //@ResponseStatus(HttpStatus.ACCEPTED)
  //@PreAuthorize(RestAccessRoles.SingleTaskWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Status(HttpStatus.ACCEPTED)
  @Post(value = "/validate")
  public void
  validateInvoiceRequest(@Body @Valid final SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo,
                         final Authentication authentication,
                         @Header String authorization) throws Exception {

    this.singleTaskWorkflowService.validate(singleTaskWorkflowSaveRequestMapper.fromEdo(workflowCreateRequestEdo),
                                            authorization);

  }

}
