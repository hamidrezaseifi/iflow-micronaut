package com.pth.clients.declaratives.company;

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

@Client(id = "profile")
public interface IDepartmentV001DeclarativeClient {

    @Get(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_DEPARTMENT + ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_READ_BY_ID)
    HttpResponse<DepartmentEdo> read(@Header String authorization, final UUID id);

    @Get(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_DEPARTMENT + ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_READ_LIST_BY_COMPANYID)
    HttpResponse<DepartmentListEdo> listByCompanyId(@Header String authorization, final UUID companyId);

    @Post(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_DEPARTMENT + ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_CREATE)
    HttpResponse<DepartmentEdo> create(@Header String authorization, @Body final DepartmentEdo edo);

    @Post(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_DEPARTMENT + ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_UPDATE)
    HttpResponse<DepartmentEdo> update(@Header String authorization, @Body final DepartmentEdo edo);

    @Post(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_DEPARTMENT + ApiUrlConstants.ProfileUrlConstants.DEPARTMENT_DELETE)
    HttpResponse<?> delete(@Header String authorization, @Body final DepartmentEdo edo);
}
