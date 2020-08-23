package com.pth.clients.gui;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.IdListEdo;
import com.pth.common.edo.IdentityListEdo;
import com.pth.common.edo.WorkflowMessageListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import java.util.UUID;

@Client(id = "gui")
public interface IGui001DeclarativeClient {

    @Get(ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_USER_DATARESET_BY_COMPANYID)
    HttpResponse<?> calUserDataResetByCompanyAndUserId(@Header String authorization,
                                                       UUID companyid,
                                                       UUID userid);

    @Post(ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_USERLIST_DATARESET_BY_COMPANYID)
    HttpResponse<?> calUserListDataResetByCompanyAndUserId(@Header String authorization,
                                                           UUID companyid,
                                                           @Body IdListEdo userIdListEdo);

    @Get(ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_WORKFLOW_DATARESET_BY_WORKFLOWID)
    HttpResponse<?> calWorkflowDataResetByCompanyAndUserId(@Header String authorization, UUID companyid,
                                                           UUID id);

    @Get(ApiUrlConstants.GuiUrlConstants.CACHDATA_READ_USER_WORKFLOWMESSAGELIST)
    HttpResponse<WorkflowMessageListEdo> calReadUserWorkflowMessagesByCompanyAndUserId(@Header String authorization,
                                                                                       UUID companyid,
                                                                                       UUID userid);


}
