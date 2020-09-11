package com.pth.clients.clients.workflow.impl;

import com.pth.clients.clients.ClientBase;
import com.pth.clients.clients.workflow.ITestThreeTaskWorkflowClient;
import com.pth.clients.declaratives.workflow.ITestThreeTaskWorkflowV001DeclarativeClient;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowListEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowSaveRequestEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class TestThreeTaskWorkflowClient extends ClientBase implements ITestThreeTaskWorkflowClient {

    private final ITestThreeTaskWorkflowV001DeclarativeClient testThreeTaskWorkflowDeclarativeClient;


    public TestThreeTaskWorkflowClient(ITestThreeTaskWorkflowV001DeclarativeClient
                                               testThreeTaskWorkflowDeclarativeClient) {
        this.testThreeTaskWorkflowDeclarativeClient = testThreeTaskWorkflowDeclarativeClient;
    }


    @Override
    public Optional<TestThreeTaskWorkflowEdo> read(String authorization,
                                                   UUID id) {
        HttpResponse<TestThreeTaskWorkflowEdo> response =
                this.testThreeTaskWorkflowDeclarativeClient.read(prepareBearerAuthorization(authorization),
                                                                      id);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<TestThreeTaskWorkflowListEdo> create(String authorization,
                                                   TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo) {
        HttpResponse<TestThreeTaskWorkflowListEdo> response =
                this.testThreeTaskWorkflowDeclarativeClient.create(prepareBearerAuthorization(authorization),
                                                                        workflowCreateRequestEdo);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<TestThreeTaskWorkflowEdo> save(String authorization,
                                             TestThreeTaskWorkflowSaveRequestEdo requestEdo) {
        HttpResponse<TestThreeTaskWorkflowEdo> response =
                this.testThreeTaskWorkflowDeclarativeClient.save(prepareBearerAuthorization(authorization),
                                                                      requestEdo);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<TestThreeTaskWorkflowListEdo> readListForUser(String authorization,
                                                                  UUID id,
                                                                  int status) {
        HttpResponse<TestThreeTaskWorkflowListEdo> response =
                this.testThreeTaskWorkflowDeclarativeClient.readListForUser(prepareBearerAuthorization(authorization),
                                                                                 id,
                                                                                 status);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public void validate(String authorization,
                                TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo) {
        HttpResponse<?> response =
                this.testThreeTaskWorkflowDeclarativeClient.validate(prepareBearerAuthorization(authorization),
                                                                          workflowCreateRequestEdo);
        if(response.getStatus() == HttpStatus.OK){

        }

    }
}
