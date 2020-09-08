package com.pth.clients.declaratives.gui;

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

@Client(id = "gui")
public interface IGuiCachDataV001DeclarativeClient {

    @Get(ApiUrlConstants.GuiUrlConstants.API001_GUI001_CALCACHDATA_ROOT + ApiUrlConstants.GuiUrlConstants.CACHDATA_READ_USER_WORKFLOWMESSAGELIST)
    HttpResponse<WorkflowMessageListEdo> readUserWorkflowMessageList(final UUID companyid, final UUID userid, @Header String authorization);


    @Get(ApiUrlConstants.GuiUrlConstants.API001_GUI001_CALCACHDATA_ROOT + ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_USER_DATARESET_BY_COMPANYID)
    HttpResponse<?> resetUserData(UUID companyid,  final UUID userid, @Header String authorization);

    @Get(ApiUrlConstants.GuiUrlConstants.API001_GUI001_CALCACHDATA_ROOT + ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_WORKFLOW_DATARESET_BY_WORKFLOWID)
    HttpResponse<?> resetWorkflowData(final UUID companyid, final UUID id, @Header String authorization);

    @Post(ApiUrlConstants.GuiUrlConstants.API001_GUI001_CALCACHDATA_ROOT + ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_USERLIST_DATARESET_BY_COMPANYID)
    HttpResponse<?> resetUserListData(final UUID companyid, @Body final IdListEdo userIdListEdo, @Header String authorization);

}
