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
import com.pth.iflow.common.models.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.iflow.common.models.edo.workflow.singletask.SingleTaskWorkflowListEdo;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.core.model.entity.workflow.SingleTaskWorkflowEntity;
import com.pth.iflow.core.service.interfaces.workflow.ISingleTaskWorkflowService;

@RestController
@RequestMapping
public class SingleTaskWorkflowController {

  final ISingleTaskWorkflowService workflowService;

  public SingleTaskWorkflowController(@Autowired final ISingleTaskWorkflowService workflowService) {

    this.workflowService = workflowService;
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.SINGLETASKWORKFLOW_READ_BY_IDENTITY)
  public ResponseEntity<SingleTaskWorkflowEdo> readWorkflow(@PathVariable(name = "identity") final String identity,
      final HttpServletRequest request) throws Exception {

    final SingleTaskWorkflowEntity model = this.workflowService.getByIdentity(identity);

    return ControllerHelper.createResponseEntity(request, this.workflowService.toEdo(model), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @IflowPostRequestMapping(path = IflowRestPaths.CoreModule.SINGLETASKWORKFLOW_SAVE)
  public ResponseEntity<SingleTaskWorkflowEdo> saveWorkflow(@RequestBody final SingleTaskWorkflowEdo invoiceWorkflowEdo,
      final HttpServletRequest request) throws Exception {

    final SingleTaskWorkflowEntity model = this.workflowService.save(this.workflowService.fromEdo(invoiceWorkflowEdo));
    return ControllerHelper.createResponseEntity(request, this.workflowService.toEdo(model), HttpStatus.ACCEPTED);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowPostRequestMapping(path = IflowRestPaths.CoreModule.SINGLETASKWORKFLOW_READ_LIST)
  public ResponseEntity<SingleTaskWorkflowListEdo> readWorkflowList(@RequestBody final IdentityListEdo idList,
      final HttpServletRequest request) throws Exception {

    final List<SingleTaskWorkflowEntity> modelList = this.workflowService.getListByIdentityList(idList.getIdentityList());

    return ControllerHelper
        .createResponseEntity(request, new SingleTaskWorkflowListEdo(this.workflowService.toEdoList(modelList)),
            HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.SINGLETASKWORKFLOW_READ_LIST_BY_USERIDENTITY)
  public ResponseEntity<SingleTaskWorkflowListEdo> readWorkflowListForUser(@PathVariable(name = "identity") final String identity,
      @PathVariable(required = false) final int status, final HttpServletRequest request) throws Exception {

    final List<SingleTaskWorkflowEntity> modelList = this.workflowService.getListForUser(identity, status);

    return ControllerHelper
        .createResponseEntity(request, new SingleTaskWorkflowListEdo(this.workflowService.toEdoList(modelList)),
            HttpStatus.OK);
  }

}
