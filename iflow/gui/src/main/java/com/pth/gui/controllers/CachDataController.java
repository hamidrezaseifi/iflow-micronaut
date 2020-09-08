package com.pth.gui.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.IdListEdo;
import com.pth.common.edo.WorkflowMessageListEdo;
import com.pth.gui.mapper.IWorkflowMessageMapper;
import com.pth.gui.models.workflow.WorkflowMessage;
import com.pth.gui.services.ICompanyCachDataManager;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller(ApiUrlConstants.GuiUrlConstants.API001_GUI001_CALCACHDATA_ROOT)
public class CachDataController {

  private final ICompanyCachDataManager companyCachDataManager;
  private final IWorkflowMessageMapper workflowMessageMapper;
  public CachDataController(final ICompanyCachDataManager companyCachDataManager,
                            final IWorkflowMessageMapper workflowMessageMapper) {

    this.companyCachDataManager = companyCachDataManager;
    this.workflowMessageMapper = workflowMessageMapper;
  }

  @Get(value = ApiUrlConstants.GuiUrlConstants.CACHDATA_READ_USER_WORKFLOWMESSAGELIST)
  public HttpResponse<WorkflowMessageListEdo> readUserWorkflowMessageList(final UUID companyid, final UUID userid){

    final List<WorkflowMessage> list = this.companyCachDataManager.getUserWorkflowMessages(companyid, userid);

    final WorkflowMessageListEdo listEdo = new WorkflowMessageListEdo(workflowMessageMapper.toEdoList(list));

    return HttpResponse.ok(listEdo);
  }

  @Get(value = ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_USER_DATARESET_BY_COMPANYID)
  public HttpResponse<?> resetUserData(UUID companyid,  final UUID userid, @Header String authorization){

    this.companyCachDataManager.resetUserData(companyid, userid, authorization, true);

    return HttpResponse.ok();
  }

  @Get(value = ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_WORKFLOW_DATARESET_BY_WORKFLOWID)
  public HttpResponse<?> resetWorkflowrData(final UUID companyid, final UUID id, @Header String authorization){

    this.companyCachDataManager.resetWorkflowStepData(companyid, id, authorization);

    return HttpResponse.ok();
  }

  @Post(value = ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_USERLIST_DATARESET_BY_COMPANYID)
  public HttpResponse<?> resetUserListData(final UUID companyid, @Body @Valid final IdListEdo userIdListEdo, @Header String authorization){

    this.companyCachDataManager.resetUserListData(companyid, userIdListEdo.getIdList(), authorization);

    return HttpResponse.ok();
  }
  
}
