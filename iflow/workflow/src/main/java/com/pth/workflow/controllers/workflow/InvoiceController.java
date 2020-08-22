package com.pth.workflow.controllers.workflow;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pth.iflow.common.annotations.IflowGetRequestMapping;
import com.pth.iflow.common.annotations.IflowPostRequestMapping;
import com.pth.iflow.common.controllers.helper.ControllerHelper;
import com.pth.iflow.common.models.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.iflow.common.models.edo.workflow.invoice.InvoiceWorkflowListEdo;
import com.pth.iflow.common.models.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;
import com.pth.iflow.common.moduls.security.RestAccessRoles;
import com.pth.iflow.common.rest.IflowRestPaths;
import com.pth.iflow.workflow.bl.IWorkflowProcessService;
import com.pth.iflow.workflow.models.mapper.WorkflowModelEdoMapper;
import com.pth.iflow.workflow.models.workflow.invoice.InvoiceWorkflow;

@RestController
@RequestMapping
public class InvoiceController {

  final IWorkflowProcessService<InvoiceWorkflow> workflowService;

  public InvoiceController(@Autowired final IWorkflowProcessService<InvoiceWorkflow> invoiceWorkflowService) {

    this.workflowService = invoiceWorkflowService;
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.InvoiceWorkflow.HAS_ROLE_INVOICE_READ)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.INVOICEWORKFLOW_READ_BY_IDENTITY)
  public ResponseEntity<InvoiceWorkflowEdo> readInvoice(@PathVariable final String identity, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final InvoiceWorkflow model = this.workflowService.getByIdentity(identity, authentication);

    return ControllerHelper.createResponseEntity(request, WorkflowModelEdoMapper.toEdo(model), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PreAuthorize(RestAccessRoles.InvoiceWorkflow.HAS_ROLE_INVOICE_CREATE)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.INVOICEWORKFLOW_CREATE)
  public ResponseEntity<InvoiceWorkflowListEdo> createInvoice(
      @RequestBody final InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<InvoiceWorkflow> modelList = this.workflowService
        .create(WorkflowModelEdoMapper.fromEdo(workflowCreateRequestEdo),
            authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new InvoiceWorkflowListEdo(WorkflowModelEdoMapper.toInvoiceWorkflowEdoList(modelList)), HttpStatus.CREATED);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PreAuthorize(RestAccessRoles.InvoiceWorkflow.HAS_ROLE_INVOICE_SAVE)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.INVOICEWORKFLOW_SAVE)
  public ResponseEntity<InvoiceWorkflowEdo> saveInvoice(@RequestBody final InvoiceWorkflowSaveRequestEdo requestEdo,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final InvoiceWorkflow model = this.workflowService.save(WorkflowModelEdoMapper.fromEdo(requestEdo), authentication);

    return ControllerHelper.createResponseEntity(request, WorkflowModelEdoMapper.toEdo(model), HttpStatus.ACCEPTED);
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.InvoiceWorkflow.HAS_ROLE_INVOICE_READ)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.INVOICEWORKFLOW_READ_LIST)
  public ResponseEntity<InvoiceWorkflowListEdo> readInvoiceList(@RequestBody final Set<String> idList,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<InvoiceWorkflow> modelList = this.workflowService.getListByIdentityList(idList, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new InvoiceWorkflowListEdo(WorkflowModelEdoMapper.toInvoiceWorkflowEdoList(modelList)), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(RestAccessRoles.InvoiceWorkflow.HAS_ROLE_INVOICE_READ)
  @IflowGetRequestMapping(path = IflowRestPaths.WorkflowModule.INVOICEWORKFLOW_READ_LIST_BY_USERIDENTITY)
  public ResponseEntity<InvoiceWorkflowListEdo> readInvoiceListForUser(@PathVariable final String Identity,
      @PathVariable(required = false) final int status, final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    final List<InvoiceWorkflow> modelList = this.workflowService.getListForUser(Identity, status, authentication);

    return ControllerHelper
        .createResponseEntity(request,
            new InvoiceWorkflowListEdo(WorkflowModelEdoMapper.toInvoiceWorkflowEdoList(modelList)), HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PreAuthorize(RestAccessRoles.InvoiceWorkflow.HAS_ROLE_INVOICE_READ)
  @IflowPostRequestMapping(path = IflowRestPaths.WorkflowModule.INVOICEWORKFLOW_VALIDATE)
  public void validateInvoiceRequest(@RequestBody final InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo,
      final HttpServletRequest request,
      final Authentication authentication) throws Exception {

    this.workflowService.validate(WorkflowModelEdoMapper.fromEdo(workflowCreateRequestEdo), authentication);

  }

}
