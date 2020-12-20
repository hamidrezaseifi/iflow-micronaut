package com.pth.workflow.mapper.impl;

import com.pth.common.edo.enums.EWorkflowProcessCommand;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowSaveRequestEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.workflow.mapper.IAssignItemMapper;
import com.pth.workflow.mapper.ITestThreeTaskWorkflowMapper;
import com.pth.workflow.mapper.ITestThreeTaskWorkflowSaveRequestMapper;
import com.pth.workflow.models.workflow.TestThreeTaskWorkflowSaveRequest;

import javax.inject.Singleton;

@Singleton
public class TestThreeTaskWorkflowSaveRequestMapper
        extends ModelEdoMapperBase<TestThreeTaskWorkflowSaveRequest, TestThreeTaskWorkflowSaveRequestEdo>
        implements ITestThreeTaskWorkflowSaveRequestMapper {

  private final ITestThreeTaskWorkflowMapper testThreeTaskWorkflowMapper;
  private final IAssignItemMapper assignItemMapper;

  public TestThreeTaskWorkflowSaveRequestMapper(ITestThreeTaskWorkflowMapper testThreeTaskWorkflowMapper,
                                                IAssignItemMapper assignItemMapper) {
    this.testThreeTaskWorkflowMapper = testThreeTaskWorkflowMapper;
    this.assignItemMapper = assignItemMapper;
  }

  @Override
  public TestThreeTaskWorkflowSaveRequest fromEdo(TestThreeTaskWorkflowSaveRequestEdo edo) {
    final TestThreeTaskWorkflowSaveRequest request = new TestThreeTaskWorkflowSaveRequest();
    request.setAssigns(assignItemMapper.fromEdoList(edo.getAssigns()));
    request.setCommand(EWorkflowProcessCommand.valueFromName(edo.getCommand()));
    request.setExpireDays(edo.getExpireDays());
    request.setWorkflow(testThreeTaskWorkflowMapper.fromEdo(edo.getWorkflow()));

    return request;
  }

  @Override
  public TestThreeTaskWorkflowSaveRequestEdo toEdo(TestThreeTaskWorkflowSaveRequest model) {
    final TestThreeTaskWorkflowSaveRequestEdo request = new TestThreeTaskWorkflowSaveRequestEdo();
    request.setAssigns(assignItemMapper.toEdoList(model.getAssigns()));
    request.setCommand(model.getCommand().getIdentity());
    request.setExpireDays(model.getExpireDays());
    request.setWorkflow(testThreeTaskWorkflowMapper.toEdo(model.getWorkflow()));

    return request;
  }
}
