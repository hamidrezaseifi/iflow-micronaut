package com.pth.core.controllers;

import java.util.ArrayList;
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
import com.pth.iflow.common.models.edo.WorkflowTypeStepEdo;
import com.pth.iflow.common.models.edo.WorkflowTypeStepListEdo;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.core.model.entity.workflow.WorkflowTypeStepEntity;
import com.pth.iflow.core.service.interfaces.IWorkflowTypeStepService;

@RestController
@RequestMapping
public class WorkflowTypeStepController {

  final IWorkflowTypeStepService workflowStepService;

  public WorkflowTypeStepController(@Autowired final IWorkflowTypeStepService workflowStepService) {

    this.workflowStepService = workflowStepService;
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.WORKFLOWTYPESTEP_READ_BY_IDENTITY)
  public ResponseEntity<WorkflowTypeStepEdo> readByIdentity(@PathVariable(name = "identity") final String identity,
      final HttpServletRequest request) throws Exception {

    final WorkflowTypeStepEntity model = this.workflowStepService.getByIdentity(identity);

    return ControllerHelper.createResponseEntity(request, this.workflowStepService.toEdo(model), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowPostRequestMapping(path = IflowRestPaths.CoreModule.WORKFLOWTYPESTEP_READ_LIST)
  public ResponseEntity<WorkflowTypeStepListEdo> readByIdentityList(@RequestBody final IdentityListEdo idList,
      final HttpServletRequest request) throws Exception {

    final List<WorkflowTypeStepEntity> modelList = idList.getIdentityList().isEmpty() ? new ArrayList<>()
        : this.workflowStepService.getListByIdentityList(idList.getIdentityList());

    return ControllerHelper
        .createResponseEntity(request, new WorkflowTypeStepListEdo(this.workflowStepService.toEdoList(modelList)),
            HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @IflowGetRequestMapping(path = IflowRestPaths.CoreModule.WORKFLOWTYPESTEP_READ_LIST_BY_WORKFLOWIDENTITY)
  public ResponseEntity<WorkflowTypeStepListEdo> readWorkflowSteps(@PathVariable(name = "identity") final String identity,
      final HttpServletRequest request) throws Exception {

    final List<WorkflowTypeStepEntity> modelList = this.workflowStepService.getListByWorkflowTypeIdentity(identity);

    return ControllerHelper
        .createResponseEntity(request, new WorkflowTypeStepListEdo(this.workflowStepService.toEdoList(modelList)),
            HttpStatus.OK);
  }

}
