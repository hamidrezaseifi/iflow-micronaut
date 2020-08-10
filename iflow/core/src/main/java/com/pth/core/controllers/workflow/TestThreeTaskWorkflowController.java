package com.pth.core.controllers.workflow;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pth.iflow.common.annotations.IflowGetRequestMapping;
import com.pth.iflow.common.annotations.IflowPostRequestMapping;
import com.pth.iflow.common.controllers.helper.ControllerHelper;
import com.pth.iflow.common.models.edo.IdentityListEdo;
import com.pth.iflow.common.models.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.iflow.common.models.edo.workflow.testthreetask.TestThreeTaskWorkflowListEdo;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.core.model.entity.workflow.TestThreeTaskWorkflowEntity;
import com.pth.iflow.core.service.interfaces.workflow.ITestThreeTaskWorkflowService;

@RestController
@RequestMapping
public class TestThreeTaskWorkflowController {

  final ITestThreeTaskWorkflowService workflowService;

  public TestThreeTaskWorkflowController(@Autowired final ITestThreeTaskWorkflowService workflowService) {

    this.workflowService = workflowService;

  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.TESTTHREETASKWORKFLOW_READ_BY_IDENTITY)
  public ResponseEntity<TestThreeTaskWorkflowEdo> readWorkflow(@PathVariable(name = "identity") final String identity,
      final HttpServletRequest request) throws Exception {

    final TestThreeTaskWorkflowEntity model = this.workflowService.getByIdentity(identity);

    return ControllerHelper.createResponseEntity(request, this.workflowService.toEdo(model), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @IflowPostRequestMapping(path = IflowRestPaths.CoreModule.TESTTHREETASKWORKFLOW_SAVE)
  public ResponseEntity<TestThreeTaskWorkflowEdo> saveWorkflow(@RequestBody final TestThreeTaskWorkflowEdo invoiceWorkflowEdo,
      final HttpServletRequest request) throws Exception {

    final TestThreeTaskWorkflowEntity model = this.workflowService.save(this.workflowService.fromEdo(invoiceWorkflowEdo));
    return ControllerHelper.createResponseEntity(request, this.workflowService.toEdo(model), HttpStatus.ACCEPTED);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowPostRequestMapping(path = IflowRestPaths.CoreModule.TESTTHREETASKWORKFLOW_READ_LIST)
  public ResponseEntity<TestThreeTaskWorkflowListEdo> readWorkflowList(@RequestBody final IdentityListEdo idList,
      final HttpServletRequest request) throws Exception {

    final List<TestThreeTaskWorkflowEntity> modelList = this.workflowService.getListByIdentityList(idList.getIdentityList());

    return ControllerHelper
        .createResponseEntity(request, new TestThreeTaskWorkflowListEdo(this.workflowService.toEdoList(modelList)),
            HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.TESTTHREETASKWORKFLOW_READ_LIST_BY_USERIDENTITY)
  public ResponseEntity<TestThreeTaskWorkflowListEdo> readWorkflowListForUser(@PathVariable(name = "identity") final String identity,
      @PathVariable(required = false) final int status, final HttpServletRequest request) throws Exception {

    final List<TestThreeTaskWorkflowEntity> modelList = this.workflowService.getListForUser(identity, status);

    return ControllerHelper
        .createResponseEntity(request, new TestThreeTaskWorkflowListEdo(this.workflowService.toEdoList(modelList)),
            HttpStatus.OK);
  }

}
