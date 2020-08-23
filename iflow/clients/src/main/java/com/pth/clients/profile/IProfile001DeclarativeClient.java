package com.pth.clients.profile;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.UserListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;

import java.util.UUID;

@Client(id = "profile")
public interface IProfile001DeclarativeClient {

    @Get(ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_READ_ALLUSERLIST_BY_DEPARTMENTID)
    HttpResponse<UserListEdo> getAllDocumentMetaData(@Header String authorization, UUID id);

}
