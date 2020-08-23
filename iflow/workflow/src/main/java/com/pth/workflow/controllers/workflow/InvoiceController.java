package com.pth.workflow.controllers.workflow;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowListEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;
import com.pth.workflow.entities.workflow.InvoiceWorkflowEntity;
import com.pth.workflow.mapper.IInvoiceWorkflowMapper;
import com.pth.workflow.mapper.IInvoiceWorkflowSaveRequestMapper;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_INVOICEWORKFLOW_ROOT)
public class InvoiceController {

  private final IWorkflowProcessService<InvoiceWorkflowEntity> invoiceWorkflowService;
  private final IInvoiceWorkflowMapper invoiceWorkflowMapper;
  private final IInvoiceWorkflowSaveRequestMapper invoiceWorkflowSaveRequestMapper;
  
  public InvoiceController(IWorkflowProcessService<InvoiceWorkflowEntity> invoiceWorkflowService, 
                           IInvoiceWorkflowMapper invoiceWorkflowMapper,
                           IInvoiceWorkflowSaveRequestMapper invoiceWorkflowSaveRequestMapper) {

    this.invoiceWorkflowService = invoiceWorkflowService;
    this.invoiceWorkflowMapper = invoiceWorkflowMapper;
    this.invoiceWorkflowSaveRequestMapper = invoiceWorkflowSaveRequestMapper;
  }

  //@PreAuthorize(RestAccessRoles.InvoiceWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Get(value = "/readbyidentity/{identity}")
  public HttpResponse<InvoiceWorkflowEdo> readInvoice(final String identity,
                                                      final Authentication authentication,
                                                      @Header String authorization) throws Exception {

    final Optional<InvoiceWorkflowEntity> modelOptional =
            this.invoiceWorkflowService.getByIdentity(identity);

    if(modelOptional.isPresent()){
      return HttpResponse.ok(invoiceWorkflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

  //@PreAuthorize(RestAccessRoles.InvoiceWorkflowEntity.HAS_ROLE_INVOICE_CREATE)
  @Post(value = "/create")
  public HttpResponse<InvoiceWorkflowListEdo> createInvoice(
                                              @Body @Valid final InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo,
                                              final Authentication authentication,
                                              @Header String authorization) throws Exception {

    final List<InvoiceWorkflowEntity> modelList =
            this.invoiceWorkflowService.create(invoiceWorkflowSaveRequestMapper.fromEdo(workflowCreateRequestEdo),
                                               authorization);

    return HttpResponse.created(new InvoiceWorkflowListEdo(invoiceWorkflowMapper.toEdoList(modelList)));
  }

  //@PreAuthorize(RestAccessRoles.InvoiceWorkflowEntity.HAS_ROLE_INVOICE_SAVE)
  @Post(value = "/save")
  public HttpResponse<InvoiceWorkflowEdo>
    saveInvoice(@Body @Valid final InvoiceWorkflowSaveRequestEdo requestEdo,
                final Authentication authentication,
                @Header String authorization) throws Exception {

    final Optional<InvoiceWorkflowEntity> modelOptional =
            this.invoiceWorkflowService.save(invoiceWorkflowSaveRequestMapper.fromEdo(requestEdo), authorization);

    if(modelOptional.isPresent()){
      return HttpResponse.created(invoiceWorkflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.badRequest();
  }

  //@PreAuthorize(RestAccessRoles.InvoiceWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Post(value = "/readbyidentitylist")
  public HttpResponse<InvoiceWorkflowListEdo> readInvoiceList(@Body @Valid final Set<String> idList,
                                                              Authentication authentication,
                                                              @Header String authorization) throws Exception {

    final List<InvoiceWorkflowEntity> modelList = this.invoiceWorkflowService.getListByIdentityList(idList);

    return HttpResponse.ok(new InvoiceWorkflowListEdo(invoiceWorkflowMapper.toEdoList(modelList)));
  }

  //@PreAuthorize(RestAccessRoles.InvoiceWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Get(value = "/readbyuseridentity/{id}/{status}")
  public HttpResponse<InvoiceWorkflowListEdo> readInvoiceListForUser(final UUID id,
                                                                     @PathVariable(defaultValue = "0") final int status,
                                                                     Authentication authentication,
                                                                     @Header String authorization) throws Exception {

    final List<InvoiceWorkflowEntity> modelList =
            this.invoiceWorkflowService.getListForUser(id, status);

    return HttpResponse.ok(new InvoiceWorkflowListEdo(invoiceWorkflowMapper.toEdoList(modelList)));
  }

  //@ResponseStatus(HttpStatus.ACCEPTED)
  //@PreAuthorize(RestAccessRoles.InvoiceWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Status(HttpStatus.ACCEPTED)
  @Post(value = "/validate")
  public void
    validateInvoiceRequest(@Body @Valid final InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo,
                           final Authentication authentication,
                           @Header String authorization) throws Exception {

    this.invoiceWorkflowService.validate(invoiceWorkflowSaveRequestMapper.fromEdo(workflowCreateRequestEdo),
                                         authorization);

  }

}
