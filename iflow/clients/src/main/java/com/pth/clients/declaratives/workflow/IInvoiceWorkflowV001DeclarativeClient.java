package com.pth.clients.declaratives.workflow;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowListEdo;
import com.pth.common.edo.workflow.invoice.InvoiceWorkflowSaveRequestEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import java.util.UUID;

@Client(id = "workflow")
public interface IInvoiceWorkflowV001DeclarativeClient {

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_INVOICEWORKFLOW_ROOT + "/read/{id}")
    HttpResponse<InvoiceWorkflowEdo> readInvoice(@Header String authorization,
                                                 final UUID id);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_INVOICEWORKFLOW_ROOT + "/create")
    HttpResponse<InvoiceWorkflowListEdo> createInvoice(@Header String authorization,
                                                   @Body InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_INVOICEWORKFLOW_ROOT + "/save")
    HttpResponse<InvoiceWorkflowEdo> saveInvoice(@Header String authorization,
                                                 @Body InvoiceWorkflowSaveRequestEdo requestEdo);

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_INVOICEWORKFLOW_ROOT + "/readbyuserid/{id}/{status}")
    HttpResponse<InvoiceWorkflowListEdo> readInvoiceListForUser(@Header String authorization,
                                                                final UUID id,
                                                                final int status);

    @Post(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_INVOICEWORKFLOW_ROOT + "/validate")
    HttpResponse<?> validateInvoice(@Header String authorization,
                                    @Body InvoiceWorkflowSaveRequestEdo workflowCreateRequestEdo);

}
