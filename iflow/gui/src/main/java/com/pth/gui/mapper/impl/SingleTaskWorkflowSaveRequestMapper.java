package com.pth.gui.mapper.impl;

import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowSaveRequestEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.gui.mapper.*;
import com.pth.gui.models.workflow.singletask.SingleTaskWorkflowSaveRequest;

public class SingleTaskWorkflowSaveRequestMapper
        extends ModelEdoMapperBase<SingleTaskWorkflowSaveRequest, SingleTaskWorkflowSaveRequestEdo>
        implements ISingleTaskWorkflowSaveRequestMapper {

  private final ISingleTaskWorkflowMapper singleTaskWorkflowMapper;
  private final IAssignItemMapper assignItemMapper;

  public SingleTaskWorkflowSaveRequestMapper(ISingleTaskWorkflowMapper singleTaskWorkflowMapper,
                                             IAssignItemMapper assignItemMapper) {
    this.singleTaskWorkflowMapper = singleTaskWorkflowMapper;
    this.assignItemMapper = assignItemMapper;
  }

  @Override
  public SingleTaskWorkflowSaveRequest fromEdo(SingleTaskWorkflowSaveRequestEdo edo) {
    final SingleTaskWorkflowSaveRequest request = new SingleTaskWorkflowSaveRequest();
    request.setAssigns(assignItemMapper.fromEdoList(edo.getAssigns()));
    request.setCommand(EWorkflowProcessCommand.valueFromName(edo.getCommand()));
    request.setExpireDays(edo.getExpireDays());
    request.setWorkflow(singleTaskWorkflowMapper.fromEdo(edo.getWorkflow()));

    return request;
  }

  @Override
  public SingleTaskWorkflowSaveRequestEdo toEdo(SingleTaskWorkflowSaveRequest model) {
    final SingleTaskWorkflowSaveRequestEdo request = new SingleTaskWorkflowSaveRequestEdo();
    request.setAssigns(assignItemMapper.toEdoList(model.getAssigns()));
    request.setCommand(model.getCommand().getIdentity());
    request.setExpireDays(model.getExpireDays());
    request.setWorkflow(singleTaskWorkflowMapper.toEdo(model.getWorkflow()));

    return request;
  }
}
