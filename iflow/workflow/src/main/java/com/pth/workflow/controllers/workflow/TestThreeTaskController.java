package com.pth.workflow.controllers.workflow;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowListEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowSaveRequestEdo;
import com.pth.workflow.entities.workflow.TestThreeTaskWorkflowEntity;
import com.pth.workflow.mapper.ITestThreeTaskWorkflowMapper;
import com.pth.workflow.mapper.ITestThreeTaskWorkflowSaveRequestMapper;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT)
public class TestThreeTaskController {

  private final IWorkflowProcessService<TestThreeTaskWorkflowEntity> testThreeTaskWorkflowService;
  private final ITestThreeTaskWorkflowMapper testThreeTaskWorkflowMapper;
  private final ITestThreeTaskWorkflowSaveRequestMapper testThreeTaskWorkflowSaveRequestMapper;

  public TestThreeTaskController(IWorkflowProcessService<TestThreeTaskWorkflowEntity> testThreeTaskWorkflowService,
                                 ITestThreeTaskWorkflowMapper testThreeTaskWorkflowMapper,
                                 ITestThreeTaskWorkflowSaveRequestMapper testThreeTaskWorkflowSaveRequestMapper) {

    this.testThreeTaskWorkflowService = testThreeTaskWorkflowService;
    this.testThreeTaskWorkflowMapper = testThreeTaskWorkflowMapper;
    this.testThreeTaskWorkflowSaveRequestMapper = testThreeTaskWorkflowSaveRequestMapper;
  }

  //@PreAuthorize(RestAccessRoles.TestThreeTaskWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Get(value = "/readbyidentity/{identity}")
  public HttpResponse<TestThreeTaskWorkflowEdo> readInvoice(final String identity,
                                                            final Authentication authentication,
                                                            @Header String authorization) throws Exception {

    final Optional<TestThreeTaskWorkflowEntity> modelOptional =
            this.testThreeTaskWorkflowService.getByIdentity(identity);

    if(modelOptional.isPresent()){
      return HttpResponse.ok(testThreeTaskWorkflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

  //@PreAuthorize(RestAccessRoles.TestThreeTaskWorkflowEntity.HAS_ROLE_INVOICE_CREATE)
  @Post(value = "/create")
  public HttpResponse<TestThreeTaskWorkflowListEdo>
    createInvoice(@Body @Valid final TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo,
                  final Authentication authentication,
                  @Header String authorization) throws Exception {

    final List<TestThreeTaskWorkflowEntity> modelList =
            this.testThreeTaskWorkflowService.create(
                    testThreeTaskWorkflowSaveRequestMapper.fromEdo(workflowCreateRequestEdo),
                    authorization);

    return HttpResponse.created(new TestThreeTaskWorkflowListEdo(testThreeTaskWorkflowMapper.toEdoList(modelList)));
  }

  //@PreAuthorize(RestAccessRoles.TestThreeTaskWorkflowEntity.HAS_ROLE_INVOICE_SAVE)
  @Post(value = "/save")
  public HttpResponse<TestThreeTaskWorkflowEdo>
  saveInvoice(@Body @Valid final TestThreeTaskWorkflowSaveRequestEdo requestEdo,
              final Authentication authentication,
              @Header String authorization) throws Exception {

    final Optional<TestThreeTaskWorkflowEntity> modelOptional =
            this.testThreeTaskWorkflowService.save(testThreeTaskWorkflowSaveRequestMapper.fromEdo(requestEdo),
                                                   authorization);

    if(modelOptional.isPresent()){
      return HttpResponse.created(testThreeTaskWorkflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.badRequest();
  }

  //@PreAuthorize(RestAccessRoles.TestThreeTaskWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Post(value = "/readbyidentitylist")
  public HttpResponse<TestThreeTaskWorkflowListEdo> readInvoiceList(@Body @Valid final Set<String> idList,
                                                                    final Authentication authentication,
                                                                    @Header String authorization) throws Exception {

    final List<TestThreeTaskWorkflowEntity> modelList =
            this.testThreeTaskWorkflowService.getListByIdentityList(idList);

    return HttpResponse.ok(new TestThreeTaskWorkflowListEdo(testThreeTaskWorkflowMapper.toEdoList(modelList)));
  }

  //@PreAuthorize(RestAccessRoles.TestThreeTaskWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Get(value = "/readbyuseridentity/{id}/{status}")
  public HttpResponse<TestThreeTaskWorkflowListEdo> readInvoiceListForUser(final UUID id,
                                                                           @PathVariable() final int status,
                                                                           final Authentication authentication,
                                                                           @Header String authorization)
          throws Exception {

    final List<TestThreeTaskWorkflowEntity> modelList =
            this.testThreeTaskWorkflowService.getListForUser(id, status);

    return HttpResponse.ok(new TestThreeTaskWorkflowListEdo(testThreeTaskWorkflowMapper.toEdoList(modelList)));
  }

  //@ResponseStatus(HttpStatus.ACCEPTED)
  //@PreAuthorize(RestAccessRoles.TestThreeTaskWorkflowEntity.HAS_ROLE_INVOICE_READ)
  @Status(HttpStatus.ACCEPTED)
  @Post(value = "/validate")
  public void validateInvoiceRequest(@Body @Valid final TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo,
                                     final Authentication authentication,
                                     @Header String authorization) throws Exception {

    this.testThreeTaskWorkflowService.validate(
            testThreeTaskWorkflowSaveRequestMapper.fromEdo(workflowCreateRequestEdo),
            authorization);

  }
}
