package com.pth.clients.declaratives.workflow;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.WorkflowTypeEdo;
import com.pth.common.edo.WorkflowTypeListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import java.util.UUID;

@Client(id = "workflow")
public interface IWorkflowTypeV001DeclarativeClient {

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOWTYPE_ROOT + "/read/{id}")
    HttpResponse<WorkflowTypeEdo> readById(@Header String authorization,
                                           final UUID id);

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOWTYPE_ROOT + "/readbycompanyid/{id}")
    HttpResponse<WorkflowTypeListEdo> readByCompanyId(@Header String authorization,
                                                      final UUID id);
}
