package com.pth.clients.clients.gui;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.IdListEdo;
import com.pth.common.edo.WorkflowMessageListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;

import java.util.Optional;
import java.util.UUID;

public interface IGuiCachDataClient {

  Optional<WorkflowMessageListEdo> readUserWorkflowMessageList(final UUID companyid, final UUID userid, String authorization);

  void resetUserData(UUID companyid,  final UUID userid, String authorization);

  void resetWorkflowData(final UUID companyid, final UUID id, String authorization);

  void resetUserListData(final UUID companyid, final IdListEdo userIdListEdo, String authorization);

}
