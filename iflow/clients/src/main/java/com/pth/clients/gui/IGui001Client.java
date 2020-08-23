package com.pth.clients.gui;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.IdListEdo;
import com.pth.common.edo.IdentityListEdo;
import com.pth.common.edo.UserListEdo;
import com.pth.common.edo.WorkflowMessageListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import java.util.Optional;
import java.util.UUID;

public interface IGui001Client {

    void calUserDataResetByCompanyAndUserId(String authorization,
                                            UUID companyid,
                                            UUID userid);

    void calUserListDataResetByCompanyAndUserId(@Header String authorization,
                                                UUID companyid,
                                                IdListEdo userIdListEdo);

    void calWorkflowDataResetByCompanyAndUserId(@Header String authorization,
                                                UUID companyid,
                                                UUID id);

    Optional<WorkflowMessageListEdo> calReadUserWorkflowMessagesByCompanyAndUserId(@Header String authorization,
                                                                                   UUID companyid,
                                                                                   UUID userid);
}
