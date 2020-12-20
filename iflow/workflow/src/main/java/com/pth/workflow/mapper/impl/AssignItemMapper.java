package com.pth.workflow.mapper.impl;

import com.pth.common.edo.AssignItemEdo;
import com.pth.common.edo.enums.EAssignType;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.mapper.IAssignItemMapper;
import com.pth.workflow.models.AssignItem;


import javax.inject.Singleton;

@Singleton
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
