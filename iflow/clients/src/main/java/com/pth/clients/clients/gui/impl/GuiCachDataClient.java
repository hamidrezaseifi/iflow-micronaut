package com.pth.clients.clients.gui.impl;

import com.pth.clients.clients.ClientBase;
import com.pth.clients.clients.gui.IGuiCachDataClient;
import com.pth.clients.declaratives.gui.IGuiCachDataV001DeclarativeClient;
import com.pth.common.edo.IdListEdo;
import com.pth.common.edo.WorkflowMessageListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

public class GuiCachDataClient extends ClientBase implements IGuiCachDataClient {

  private final IGuiCachDataV001DeclarativeClient guiCachDataV001DeclarativeClient;

  public GuiCachDataClient(IGuiCachDataV001DeclarativeClient guiCachDataV001DeclarativeClient) {
    this.guiCachDataV001DeclarativeClient = guiCachDataV001DeclarativeClient;
  }

  @Override
  public Optional<WorkflowMessageListEdo> readUserWorkflowMessageList(UUID companyid, UUID userid, String authorization) {
    HttpResponse<WorkflowMessageListEdo> response =
            this.guiCachDataV001DeclarativeClient.readUserWorkflowMessageList(companyid, userid, prepareBearerAuthorization(authorization));
    if(response.getStatus() == HttpStatus.OK){
      return response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public void resetUserData(UUID companyid, UUID userid, String authorization) {
    HttpResponse<?> response =
            this.guiCachDataV001DeclarativeClient.resetUserData(companyid, userid, prepareBearerAuthorization(authorization));
    if(response.getStatus() == HttpStatus.OK){

    }
  }

  @Override
  public void resetWorkflowData(UUID companyid, UUID id, String authorization) {
    HttpResponse<?> response =
            this.guiCachDataV001DeclarativeClient.resetWorkflowData(companyid, id, prepareBearerAuthorization(authorization));
    if(response.getStatus() == HttpStatus.OK){

    }
  }

  @Override
  public void resetUserListData(UUID companyid, IdListEdo userIdListEdo, String authorization) {
    HttpResponse<?> response =
            this.guiCachDataV001DeclarativeClient.resetUserListData(companyid, userIdListEdo, prepareBearerAuthorization(authorization));
    if(response.getStatus() == HttpStatus.OK){

    }
  }
}
