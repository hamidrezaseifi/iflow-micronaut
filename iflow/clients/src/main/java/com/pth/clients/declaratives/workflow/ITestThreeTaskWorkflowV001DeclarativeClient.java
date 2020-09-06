package com.pth.clients.declaratives.workflow;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowEdo;
import com.pth.common.edo.workflow.testthreetask.TestThreeTaskWorkflowListEdo;
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
    HttpResponse<TestThreeTaskWorkflowEdo> readInvoice(@Header String authorization,
                                                       final UUID id);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT + "/create")
    HttpResponse<TestThreeTaskWorkflowEdo> createInvoice(@Header String authorization,
                                                         @Body InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT + "/save")
    HttpResponse<TestThreeTaskWorkflowEdo> saveInvoice(@Header String authorization,
                                                       @Body InvoiceWorkflowSaveRequestEdo requestEdo);

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT + "/readbyuserid/{id}/{status}")
    HttpResponse<TestThreeTaskWorkflowListEdo> readInvoiceListForUser(@Header String authorization,
                                                                      final UUID id,
                                                                      final int status);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_TESTTHREETASKWORKFLOW_ROOT + "/validate")
    HttpResponse<?> validateInvoice(@Header String authorization,
                                    @Body InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo);

}
