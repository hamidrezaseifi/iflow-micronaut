package com.pth.clients.workflow.impl;

import com.pth.clients.ClientBase;
import com.pth.clients.declaratives.workflow.IWorkflowTypeV001DeclarativeClient;
import com.pth.clients.workflow.IWorkflowTypeClient;
import com.pth.common.edo.WorkflowTypeEdo;
import com.pth.common.edo.WorkflowTypeListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class WorkflowTypeClient extends ClientBase implements IWorkflowTypeClient {

    private final IWorkflowTypeV001DeclarativeClient workflowTypeDeclarativeClient;


    public WorkflowTypeClient(IWorkflowTypeV001DeclarativeClient workflowTypeDeclarativeClient) {
        this.workflowTypeDeclarativeClient = workflowTypeDeclarativeClient;
    }

    @Override
    public Optional<WorkflowTypeEdo> readById(String authorization, UUID id) {

        HttpResponse<WorkflowTypeEdo> response = this.workflowTypeDeclarativeClient.readById(prepareBearerAuthorization(authorization), id);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }

        return Optional.empty();
    }

    @Override
    public Optional<WorkflowTypeListEdo> readByCompanyId(String authorization, UUID id) {

        HttpResponse<WorkflowTypeListEdo> response = this.workflowTypeDeclarativeClient.readByCompanyId(prepareBearerAuthorization(authorization), id);
        if(response.getStatus() == HttpStatus.OK){
            return response.getBody();
        }
        return Optional.empty();
    }
}
