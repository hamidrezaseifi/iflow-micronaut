package com.pth.clients.clients.workflow.impl;

import com.pth.clients.clients.ClientBase;
import com.pth.clients.clients.workflow.ISingleTaskWorkflowClient;
import com.pth.clients.declaratives.workflow.ISingleTaskWorkflowV001DeclarativeClient;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowListEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowSaveRequestEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class SingleTaskWorkflowClient extends ClientBase implements ISingleTaskWorkflowClient {

    private final ISingleTaskWorkflowV001DeclarativeClient singleTaskWorkflowDeclarativeClient;


    public SingleTaskWorkflowClient(ISingleTaskWorkflowV001DeclarativeClient singleTaskWorkflowDeclarativeClient) {
        this.singleTaskWorkflowDeclarativeClient = singleTaskWorkflowDeclarativeClient;
    }


    @Override
    public Optional<SingleTaskWorkflowEdo> read(String authorization,
                                                UUID id) {
        HttpResponse<SingleTaskWorkflowEdo> response =
                this.singleTaskWorkflowDeclarativeClient.read(prepareBearerAuthorization(authorization),
                                                                      id);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<SingleTaskWorkflowListEdo> create(String authorization,
                                                   SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo) {
        HttpResponse<SingleTaskWorkflowListEdo> response =
                this.singleTaskWorkflowDeclarativeClient.create(prepareBearerAuthorization(authorization),
                                                                        workflowCreateRequestEdo);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<SingleTaskWorkflowEdo> save(String authorization,
                                             SingleTaskWorkflowSaveRequestEdo requestEdo) {
        HttpResponse<SingleTaskWorkflowEdo> response =
                this.singleTaskWorkflowDeclarativeClient.save(prepareBearerAuthorization(authorization),
                                                                      requestEdo);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<SingleTaskWorkflowListEdo> readListForUser(String authorization,
                                                               UUID id,
                                                               int status) {
        HttpResponse<SingleTaskWorkflowListEdo> response =
                this.singleTaskWorkflowDeclarativeClient.readListForUser(prepareBearerAuthorization(authorization),
                                                                                 id,
                                                                                 status);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public void validate(String authorization,
                                SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo) {
        HttpResponse<?> response =
                this.singleTaskWorkflowDeclarativeClient.validate(prepareBearerAuthorization(authorization),
                                                                          workflowCreateRequestEdo);
        if(response.getStatus() == HttpStatus.OK){

        }

    }
}
