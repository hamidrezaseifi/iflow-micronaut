package com.pth.gui.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.IdListEdo;
import com.pth.common.edo.WorkflowMessageListEdo;
import com.pth.gui.authentication.GuiTokenManualAuthentication;
import com.pth.gui.exception.GuiNotAuthorizedException;
import com.pth.gui.mapper.IWorkflowMessageMapper;
import com.pth.gui.models.workflow.WorkflowMessage;
import com.pth.gui.services.ICompanyCachDataManager;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.annotation.Error;
import io.micronaut.http.hateoas.JsonError;
import io.micronaut.http.hateoas.Link;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller(ApiUrlConstants.GuiUrlConstants.API001_GUI001_CALCACHDATA_ROOT)
public class CachDataController {

  private final ICompanyCachDataManager companyCachDataManager;
  private final IWorkflowMessageMapper workflowMessageMapper;
  private final GuiTokenManualAuthentication tokenManualAuthentication;

  public CachDataController(final ICompanyCachDataManager companyCachDataManager,
                            final IWorkflowMessageMapper workflowMessageMapper,
                            GuiTokenManualAuthentication tokenManualAuthentication) {

    this.companyCachDataManager = companyCachDataManager;
    this.workflowMessageMapper = workflowMessageMapper;
    this.tokenManualAuthentication = tokenManualAuthentication;
  }

  @Get(value = ApiUrlConstants.GuiUrlConstants.CACHDATA_READ_USER_WORKFLOWMESSAGELIST)
  public HttpResponse<WorkflowMessageListEdo> readUserWorkflowMessageList(final UUID companyid,
                                                                          final UUID userid,
                                                                          @Header String authorization){
    verifyToken(authorization);

    final List<WorkflowMessage> list = this.companyCachDataManager.getUserWorkflowMessages(companyid, userid);

    final WorkflowMessageListEdo listEdo = new WorkflowMessageListEdo(workflowMessageMapper.toEdoList(list));

    return HttpResponse.ok(listEdo);
  }

  @Get(value = ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_USER_DATARESET_BY_COMPANYID)
  public HttpResponse<?> resetUserData(UUID companyid,
                                       final UUID userid,
                                       @Header String authorization){

    verifyToken(authorization);

    this.companyCachDataManager.resetUserData(companyid, userid, authorization, true);

    return HttpResponse.ok();
  }

  @Get(value = ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_WORKFLOW_DATARESET_BY_WORKFLOWID)
  public HttpResponse<?> resetWorkflowrData(final UUID companyid,
                                            final UUID id,
                                            @Header String authorization){

    verifyToken(authorization);

    this.companyCachDataManager.resetWorkflowStepData(companyid, id, authorization);

    return HttpResponse.ok();
  }

  @Post(value = ApiUrlConstants.GuiUrlConstants.CACHDATA_CAL_USERLIST_DATARESET_BY_COMPANYID)
  public HttpResponse<?> resetUserListData(final UUID companyid,
                                           @Body @Valid final IdListEdo userIdListEdo,
                                           @Header String authorization){

    verifyToken(authorization);

    this.companyCachDataManager.resetUserListData(companyid, userIdListEdo.getIdList(), authorization);

    return HttpResponse.ok();
  }

  @Error(status = HttpStatus.UNAUTHORIZED, exception = GuiNotAuthorizedException.class)
  public HttpResponse notFound(HttpRequest request, GuiNotAuthorizedException ex) {

    JsonError error = new JsonError(ex.getMessage())
            .link(Link.SELF, Link.of(request.getUri()));

    return HttpResponse.<JsonError>unauthorized()
            .body(error);
  }

  private void verifyToken(String token){

    Optional<BearerAccessRefreshToken> bearerAccessRefreshTokenOptional =
            this.tokenManualAuthentication.authenticate(token);
    if(!bearerAccessRefreshTokenOptional.isPresent()){
      throw new GuiNotAuthorizedException(String.format("The token {} ist not authorized", token), token);
    }
  }
  
}
