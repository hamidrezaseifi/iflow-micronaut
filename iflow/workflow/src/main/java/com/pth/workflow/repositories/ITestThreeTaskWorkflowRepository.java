package com.pth.workflow.repositories;

import com.pth.common.repositories.IEntityRepository;
import com.pth.workflow.entities.workflow.TestThreeTaskWorkflowEntity;
import com.pth.workflow.entities.workflow.WorkflowEntity;

import java.util.Optional;

public interface ITestThreeTaskWorkflowRepository extends IWorkflowBaseRepository<TestThreeTaskWorkflowEntity> {

    Optional<TestThreeTaskWorkflowEntity> getByIdentity(String identity);
}
