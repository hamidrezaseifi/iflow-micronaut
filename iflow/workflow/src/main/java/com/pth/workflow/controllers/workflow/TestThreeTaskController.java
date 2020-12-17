package com.pth.workflow.controllers.workflow;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowListEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowSaveRequestEdo;
import com.pth.common.enums.UserRoles;
import com.pth.workflow.entities.TestThreeTaskWorkflowEntity;
import com.pth.workflow.mapper.ITestThreeTaskWorkflowMapper;
import com.pth.workflow.mapper.ITestThreeTaskWorkflowSaveRequestMapper;
import com.pth.workflow.services.bl.IWorkflowProcessService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Named;
import javax.validation.Valid;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT)
public class TestThreeTaskController {

  private final IWorkflowProcessService<TestThreeTaskWorkflowEntity> testThreeTaskWorkflowService;
  private final ITestThreeTaskWorkflowMapper testThreeTaskWorkflowMapper;
  private final ITestThreeTaskWorkflowSaveRequestMapper testThreeTaskWorkflowSaveRequestMapper;

  public TestThreeTaskController(@Named("testThreeTaskWorkProcessService") IWorkflowProcessService<TestThreeTaskWorkflowEntity> testThreeTaskWorkflowService,
                                 ITestThreeTaskWorkflowMapper testThreeTaskWorkflowMapper,
                                 ITestThreeTaskWorkflowSaveRequestMapper testThreeTaskWorkflowSaveRequestMapper) {

    this.testThreeTaskWorkflowService = testThreeTaskWorkflowService;
    this.testThreeTaskWorkflowMapper = testThreeTaskWorkflowMapper;
    this.testThreeTaskWorkflowSaveRequestMapper = testThreeTaskWorkflowSaveRequestMapper;
  }

  @Get(value = "/read/{id}")
  public HttpResponse<TestThreeTaskWorkflowEdo> readInvoice(final UUID id,
                                                            @Header String authorization) throws Exception {

    final Optional<TestThreeTaskWorkflowEntity> modelOptional =
            this.testThreeTaskWorkflowService.getById(id);

    if(modelOptional.isPresent()){
      return HttpResponse.ok(testThreeTaskWorkflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.notFound();
  }

  @Secured({UserRoles.ROLE_DATAENTRY, UserRoles.ROLE_ADMIN})
  @Post(value = "/create")
  public HttpResponse<TestThreeTaskWorkflowListEdo>
    createInvoice(@Body @Valid final TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo,
                  @Header String authorization) throws Exception {

    final List<TestThreeTaskWorkflowEntity> modelList =
            this.testThreeTaskWorkflowService.create(
                    testThreeTaskWorkflowSaveRequestMapper.fromEdo(workflowCreateRequestEdo),
                    authorization);

    return HttpResponse.created(new TestThreeTaskWorkflowListEdo(testThreeTaskWorkflowMapper.toEdoList(modelList)));
  }

  @Secured({UserRoles.ROLE_DATAENTRY, UserRoles.ROLE_ADMIN})
  @Post(value = "/save")
  public HttpResponse<TestThreeTaskWorkflowEdo>
  saveInvoice(@Body @Valid final TestThreeTaskWorkflowSaveRequestEdo requestEdo,
              @Header String authorization) throws Exception {

    final Optional<TestThreeTaskWorkflowEntity> modelOptional =
            this.testThreeTaskWorkflowService.save(testThreeTaskWorkflowSaveRequestMapper.fromEdo(requestEdo),
                                                   authorization);

    if(modelOptional.isPresent()){
      return HttpResponse.created(testThreeTaskWorkflowMapper.toEdo(modelOptional.get()));
    }
    return HttpResponse.badRequest();
  }

  @Post(value = "/readbyidentitylist")
  public HttpResponse<TestThreeTaskWorkflowListEdo> readInvoiceList(@Body @Valid final Set<String> idList,
                                                                    @Header String authorization) throws Exception {

    final List<TestThreeTaskWorkflowEntity> modelList =
            this.testThreeTaskWorkflowService.getListByIdentityList(idList);

    return HttpResponse.ok(new TestThreeTaskWorkflowListEdo(testThreeTaskWorkflowMapper.toEdoList(modelList)));
  }

  @Get(value = "/readbyuserid/{id}/{status}")
  public HttpResponse<TestThreeTaskWorkflowListEdo> readInvoiceListForUser(final UUID id,
                                                                           @PathVariable() final int status,
                                                                           @Header String authorization)
          throws Exception {

    final List<TestThreeTaskWorkflowEntity> modelList =
            this.testThreeTaskWorkflowService.getListForUser(id, status);

    return HttpResponse.ok(new TestThreeTaskWorkflowListEdo(testThreeTaskWorkflowMapper.toEdoList(modelList)));
  }

  @Status(HttpStatus.ACCEPTED)
  @Secured({UserRoles.ROLE_DATAENTRY, UserRoles.ROLE_ADMIN})
  @Post(value = "/validate")
  public void validateInvoiceRequest(@Body @Valid final TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo,
                                     @Header String authorization) throws Exception {

    this.testThreeTaskWorkflowService.validate(
            testThreeTaskWorkflowSaveRequestMapper.fromEdo(workflowCreateRequestEdo),
            authorization);

  }
}
