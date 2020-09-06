package com.pth.clients.workflow.impl;

import com.pth.clients.ClientBase;
import com.pth.clients.declaratives.workflow.IWorkflowMessageV001DeclarativeClient;
import com.pth.clients.workflow.IWorkflowMessageClient;
import com.pth.common.edo.WorkflowMessageListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class WorkflowMessageClient extends ClientBase implements IWorkflowMessageClient {

    private final IWorkflowMessageV001DeclarativeClient workflowMessageDeclarativeClient;

    public WorkflowMessageClient(IWorkflowMessageV001DeclarativeClient workflowMessageDeclarativeClient) {
        this.workflowMessageDeclarativeClient = workflowMessageDeclarativeClient;
    }

    @Override
    public Optional<WorkflowMessageListEdo> readUserWorkflowMessageList(String authorization,
                                                                        UUID id,
                                                                        int status) {
        HttpResponse<WorkflowMessageListEdo> response =
                this.workflowMessageDeclarativeClient.readUserWorkflowMessageList(prepareBearerAuthorization(authorization), id, status);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<WorkflowMessageListEdo> readUserWorkflowMessageList(String authorization,
                                                                        UUID id) {
        return readUserWorkflowMessageList(authorization, id, 0);
    }

    @Override
    public Optional<WorkflowMessageListEdo> readWorkflowWorkflowMessageList(String authorization,
                                                                            UUID id) {
        HttpResponse<WorkflowMessageListEdo> response =
                this.workflowMessageDeclarativeClient.readWorkfloWorkflowMessageList(prepareBearerAuthorization(authorization), id);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }
}
