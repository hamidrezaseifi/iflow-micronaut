package com.pth.clients.declaratives.workflow;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowListEdo;
import com.pth.common.edo.workflow.singletask.SingleTaskWorkflowSaveRequestEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import java.util.UUID;

@Client(id = "workflow")
public interface ISingleTaskWorkflowV001DeclarativeClient {

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_SINGLETASKWORKFLOW_ROOT + "/read/{id}")
    HttpResponse<SingleTaskWorkflowEdo> read(@Header String authorization,
                                             final UUID id);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_SINGLETASKWORKFLOW_ROOT + "/create")
    HttpResponse<SingleTaskWorkflowListEdo> create(@Header String authorization,
                                               @Body SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_SINGLETASKWORKFLOW_ROOT + "/save")
    HttpResponse<SingleTaskWorkflowEdo> save(@Header String authorization,
                                             @Body SingleTaskWorkflowSaveRequestEdo requestEdo);

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_SINGLETASKWORKFLOW_ROOT + "/readbyuserid/{id}/{status}")
    HttpResponse<SingleTaskWorkflowListEdo> readListForUser(@Header String authorization,
                                                            final UUID id,
                                                            final int status);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_SINGLETASKWORKFLOW_ROOT + "/validate")
    HttpResponse<?> validate(@Header String authorization,
                             @Body SingleTaskWorkflowSaveRequestEdo workflowCreateRequestEdo);

}
