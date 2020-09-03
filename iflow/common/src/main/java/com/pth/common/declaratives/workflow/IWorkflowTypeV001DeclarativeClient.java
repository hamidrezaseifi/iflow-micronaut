package com.pth.common.declaratives.workflow;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.*;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Client(id = "workflow")
public interface IWorkflowTypeV001DeclarativeClient {

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOWTYPE_ROOT + "/read/{id}")
    HttpResponse<WorkflowTypeEdo> readById(@Header String authorization, final UUID id);

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOWTYPE_ROOT + "/readbycompanyid/{id}")
    HttpResponse<WorkflowTypeListEdo> readByCompanyId(@Header String authorization, final UUID id);
}
