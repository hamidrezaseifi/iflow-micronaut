package com.pth.clients.gui;

import com.pth.clients.profile.IProfile001DeclarativeClient;
import com.pth.common.edo.IdListEdo;
import com.pth.common.edo.IdentityListEdo;
import com.pth.common.edo.UserListEdo;
import com.pth.common.edo.WorkflowMessageListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class Gui001Client implements IGui001Client {

    private final IGui001DeclarativeClient gui001DeclarativeClient;

    public Gui001Client(IGui001DeclarativeClient gui001DeclarativeClient) {
        this.gui001DeclarativeClient = gui001DeclarativeClient;
    }

    @Override
    public void calUserDataResetByCompanyAndUserId(String authorization,
                                                   UUID companyid,
                                                   UUID userid) {
        HttpResponse<?> response =
                gui001DeclarativeClient.calUserDataResetByCompanyAndUserId(authorization,
                                                                           companyid,
                                                                           userid);

        if(response.status() == HttpStatus.OK){

        }

    }

    @Override
    public void calUserListDataResetByCompanyAndUserId(String authorization,
                                                       UUID companyid,
                                                       IdListEdo userIdListEdo) {
        HttpResponse<?> response =
                gui001DeclarativeClient.calUserListDataResetByCompanyAndUserId(authorization,
                                                                               companyid,
                                                                               userIdListEdo);

        if(response.status() == HttpStatus.OK){

        }
    }

    @Override
    public void calWorkflowDataResetByCompanyAndUserId(String authorization,
                                                       UUID companyid,
                                                       UUID id) {
        HttpResponse<?> response =
                gui001DeclarativeClient.calWorkflowDataResetByCompanyAndUserId(authorization,
                                                                               companyid,
                                                                               id);

        if(response.status() == HttpStatus.OK){

        }
    }

    @Override
    public Optional<WorkflowMessageListEdo> calReadUserWorkflowMessagesByCompanyAndUserId(String authorization,
                                                                                          UUID companyid,
                                                                                          UUID userid) {
        HttpResponse<WorkflowMessageListEdo> response =
                gui001DeclarativeClient.calReadUserWorkflowMessagesByCompanyAndUserId(authorization,
                                                                                      companyid,
                                                                                      userid);

        if(response.status() == HttpStatus.OK && response.body() != null){
            return Optional.of(response.body());
        }
        return Optional.empty();
    }
}
