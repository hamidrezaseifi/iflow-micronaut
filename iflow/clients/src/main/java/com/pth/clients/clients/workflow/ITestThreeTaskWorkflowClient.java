package com.pth.clients.clients.workflow;

import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowListEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowSaveRequestEdo;

import java.util.Optional;
import java.util.UUID;

public interface ITestThreeTaskWorkflowClient {

    Optional<TestThreeTaskWorkflowEdo> read(String authorization,
                                            final UUID id);

    Optional<TestThreeTaskWorkflowListEdo> create(String authorization,
                                              TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo);

    Optional<TestThreeTaskWorkflowEdo> save(String authorization,
                                            TestThreeTaskWorkflowSaveRequestEdo requestEdo);

    Optional<TestThreeTaskWorkflowListEdo> readListForUser(String authorization,
                                                           final UUID id,
                                                           final int status);

    Optional<?> validate(String authorization, TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo);
    
}
