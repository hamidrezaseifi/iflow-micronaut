package com.pth.workflow.repositories;

import com.pth.workflow.entities.TestThreeTaskWorkflowEntity;

import java.util.Optional;

public interface ITestThreeTaskWorkflowRepository extends IWorkflowBaseRepository<TestThreeTaskWorkflowEntity> {

    Optional<TestThreeTaskWorkflowEntity> getByIdentity(String identity);
}
