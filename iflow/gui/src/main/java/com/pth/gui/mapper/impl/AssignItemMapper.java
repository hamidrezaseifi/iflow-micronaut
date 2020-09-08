package com.pth.gui.mapper.impl;

import com.pth.common.edo.AssignItemEdo;
import com.pth.common.edo.enums.EAssignType;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.IAssignItemMapper;
import com.pth.gui.models.workflow.AssignItem;
import com.pth.gui.models.workflow.invoice.InvoiceWorkflowSaveRequest;

public class AssignItemMapper
        extends ModelEdoMapperBase<AssignItem, AssignItemEdo>
        implements IAssignItemMapper {
  @Override
  public AssignItem fromEdo(AssignItemEdo edo) {
    return new AssignItem(edo.getItemId(), EAssignType.valueFromName(edo.getItemType()) );
  }

  @Override
  public AssignItemEdo toEdo(AssignItem model) {
    return new AssignItemEdo(model.getItemId(), model.getItemType().getIdentity());
  }
}
