package com.pth.gui.mapper.impl;

import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.IAssignItemMapper;
import com.pth.gui.mapper.IInvoiceWorkflowMapper;
import com.pth.gui.mapper.IInvoiceWorkflowSaveRequestMapper;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflowSaveRequest;

public class InvoiceWorkflowSaveRequestMapper
        extends ModelEdoMapperBase<InvoiceWorkflowSaveRequest, InvoiceWorkflowSaveRequestEdo>
        implements IInvoiceWorkflowSaveRequestMapper {

  private final IInvoiceWorkflowMapper invoiceWorkflowMapper;
  private final IAssignItemMapper assignItemMapper;

  public InvoiceWorkflowSaveRequestMapper(IInvoiceWorkflowMapper invoiceWorkflowMapper,
                                          IAssignItemMapper assignItemMapper) {
    this.invoiceWorkflowMapper = invoiceWorkflowMapper;
    this.assignItemMapper = assignItemMapper;
  }

  @Override
  public InvoiceWorkflowSaveRequest fromEdo(InvoiceWorkflowSaveRequestEdo edo) {
    final InvoiceWorkflowSaveRequest request = new InvoiceWorkflowSaveRequest();
    request.setAssigns(assignItemMapper.fromEdoList(edo.getAssigns()));
    request.setCommand(EWorkflowProcessCommand.valueFromName(edo.getCommand()));
    request.setExpireDays(edo.getExpireDays());
    request.setWorkflow(invoiceWorkflowMapper.fromEdo(edo.getWorkflow()));

    return request;
  }

  @Override
  public InvoiceWorkflowSaveRequestEdo toEdo(InvoiceWorkflowSaveRequest model) {
    final InvoiceWorkflowSaveRequestEdo request = new InvoiceWorkflowSaveRequestEdo();
    request.setAssigns(assignItemMapper.toEdoList(model.getAssigns()));
    request.setCommand(model.getCommand().getIdentity());
    request.setExpireDays(model.getExpireDays());
    request.setWorkflow(invoiceWorkflowMapper.toEdo(model.getWorkflow()));

    return request;
  }
}
