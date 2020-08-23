package com.pth.workflow.mapper.impl;

import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.mapping.ModelEdoMapperBase;
import com.pth.common.utils.MappingUtils;
import com.pth.workflow.entities.TestThreeTaskWorkflowEntity;
import com.pth.workflow.mapper.ITestThreeTaskWorkflowMapper;

import javax.inject.Singleton;

@Singleton
public class TestThreeTaskWorkflowMapper
        extends ModelEdoMapperBase<TestThreeTaskWorkflowEntity, TestThreeTaskWorkflowEdo>
        implements ITestThreeTaskWorkflowMapper {
    @Override
    public TestThreeTaskWorkflowEntity fromEdo(TestThreeTaskWorkflowEdo edo) {
        TestThreeTaskWorkflowEntity model = MappingUtils.copyProperties(edo, new TestThreeTaskWorkflowEntity());

        return model;
    }

    @Override
    public TestThreeTaskWorkflowEdo toEdo(TestThreeTaskWorkflowEntity model) {
        TestThreeTaskWorkflowEdo edo = MappingUtils.copyProperties(model, new TestThreeTaskWorkflowEdo());

        return edo;
    }
}
