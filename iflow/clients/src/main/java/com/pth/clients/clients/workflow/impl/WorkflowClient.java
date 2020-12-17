package com.pth.clients.clients.workflow.impl;

import com.pth.clients.clients.ClientBase;
import com.pth.clients.clients.workflow.IWorkflowClient;
import com.pth.clients.declaratives.workflow.IWorkflowV001DeclarativeClient;
import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.edo.WorkflowTypeEdo;
import com.pth.common.edo.WorkflowTypeListEdo;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.WorkflowListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class WorkflowClient extends ClientBase implements IWorkflowClient {

    private final IWorkflowV001DeclarativeClient workflowDeclarativeClient;


    public WorkflowClient(IWorkflowV001DeclarativeClient workflowDeclarativeClient) {
        this.workflowDeclarativeClient = workflowDeclarativeClient;
    }

    @Override
    public Optional<WorkflowListEdo> searchWorkflow(String authorization,
                                                    WorkflowSearchFilterEdo workflowSearchFilterEdo) {
        HttpResponse<WorkflowListEdo> response =
                this.workflowDeclarativeClient.search(prepareBearerAuthorization(authorization),
                                                              workflowSearchFilterEdo);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<WorkflowEdo> read(String authorization, UUID id) {
        HttpResponse<WorkflowEdo> response =
                this.workflowDeclarativeClient.read(prepareBearerAuthorization(authorization), id);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }
}
