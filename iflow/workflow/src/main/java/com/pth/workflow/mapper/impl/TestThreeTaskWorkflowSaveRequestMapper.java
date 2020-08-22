package com.pth.workflow.mapper.impl;

import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowSaveRequestEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.mapper.ITestThreeTaskWorkflowSaveRequestMapper;
import com.pth.workflow.models.workflow.TestThreeTaskWorkflowSaveRequest;

import javax.inject.Singleton;

@Singleton
public class TestThreeTaskWorkflowSaveRequestMapper
        extends ModelEdoMapperBase<TestThreeTaskWorkflowSaveRequest, TestThreeTaskWorkflowSaveRequestEdo>
        implements ITestThreeTaskWorkflowSaveRequestMapper {
    @Override
    public TestThreeTaskWorkflowSaveRequest fromEdo(TestThreeTaskWorkflowSaveRequestEdo edo) {
        TestThreeTaskWorkflowSaveRequest model = MappingUtils.copyProperties(edo, new TestThreeTaskWorkflowSaveRequest());

        return model;
    }

    @Override
    public TestThreeTaskWorkflowSaveRequestEdo toEdo(TestThreeTaskWorkflowSaveRequest model) {
        TestThreeTaskWorkflowSaveRequestEdo edo = MappingUtils.copyProperties(model, new TestThreeTaskWorkflowSaveRequestEdo());

        return edo;
    }
}
