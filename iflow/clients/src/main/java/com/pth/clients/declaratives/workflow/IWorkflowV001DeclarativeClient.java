package com.pth.clients.declaratives.workflow;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.WorkflowSearchFilterEdo;
import com.pth.common.edo.workflow.WorkflowEdo;
import com.pth.common.edo.workflow.WorkflowListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import java.util.UUID;

@Client(id = "workflow")
public interface IWorkflowV001DeclarativeClient {

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOW_ROOT + "/search")
    HttpResponse<WorkflowListEdo> search(@Header String authorization,
                                                 @Body final WorkflowSearchFilterEdo workflowSearchFilterEdo);

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOW_ROOT + "/read/{id}")
    HttpResponse<WorkflowEdo> read(@Header String authorization,
                                           final UUID id);
}
