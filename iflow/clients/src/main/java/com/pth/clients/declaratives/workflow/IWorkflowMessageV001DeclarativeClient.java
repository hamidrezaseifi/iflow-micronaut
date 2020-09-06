package com.pth.clients.declaratives.workflow;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.WorkflowMessageListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import java.util.UUID;

@Client(id = "workflow")
public interface IWorkflowMessageV001DeclarativeClient {

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOWMESSAGE_ROOT + "/readbyuserid/{id}/{status}")
    HttpResponse<WorkflowMessageListEdo>
        readUserWorkflowMessageList(@Header String authorization,
                                    final UUID id,
                                    int status);

    @Get(ApiUrlConstants.WorkflowUrlConstants.API001_WORKFLOW001_WORKFLOWMESSAGE_ROOT + "/readallforworkflow/{id}")
    HttpResponse<WorkflowMessageListEdo>
        readWorkfloWorkflowMessageList(@Header String authorization,
                                       final UUID id);


}
