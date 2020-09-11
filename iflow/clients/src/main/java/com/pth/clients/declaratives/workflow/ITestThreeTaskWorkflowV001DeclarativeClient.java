package com.pth.clients.declaratives.workflow;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowListEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowSaveRequestEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import java.util.UUID;

@Client(id = "workflow")
public interface ITestThreeTaskWorkflowV001DeclarativeClient {

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT + "/read/{id}")
    HttpResponse<TestThreeTaskWorkflowEdo> read(@Header String authorization,
                                                       final UUID id);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT + "/create")
    HttpResponse<TestThreeTaskWorkflowListEdo> create(@Header String authorization,
                                                  @Body TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT + "/save")
    HttpResponse<TestThreeTaskWorkflowEdo> save(@Header String authorization,
                                                       @Body TestThreeTaskWorkflowSaveRequestEdo requestEdo);

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT + "/readbyuserid/{id}/{status}")
    HttpResponse<TestThreeTaskWorkflowListEdo> readListForUser(@Header String authorization,
                                                                      final UUID id,
                                                                      final int status);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT + "/validate")
    HttpResponse<?> validate(@Header String authorization,
                                    @Body TestThreeTaskWorkflowSaveRequestEdo workflowCreateRequestEdo);

}
