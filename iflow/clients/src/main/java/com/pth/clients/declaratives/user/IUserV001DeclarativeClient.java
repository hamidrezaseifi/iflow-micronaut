package com.pth.clients.declaratives.user;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.*;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;

import javax.validation.Valid;
import java.util.UUID;

@Client(id = "profile")
public interface IUserV001DeclarativeClient {

    @Post(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS + ApiUrlConstants.ProfileUrlConstants.USER_SAVE)
    HttpResponse<UserEdo> saveUser(@Header("Authorization") String authorization,
                                   @Body @Valid final UserEdo userEdo);

    @Post(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS + ApiUrlConstants.ProfileUrlConstants.USER_PASSWORD_RESET)
    HttpResponse<?> resetPassword(@Header("Authorization") String authorization,
                                   @Body @Valid final UserPasswordResetRequestEdo userPasswordResetRequestEdo,
                                   final UUID id);

    @Post(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS + ApiUrlConstants.ProfileUrlConstants.USER_DELETE)
    HttpResponse<?> deleteUser(@Header("Authorization") String authorization,
                               @Body @Valid final UserEdo userEdo);

    @Get(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS + ApiUrlConstants.ProfileUrlConstants.USER_READ_BY_ID)
    HttpResponse<UserEdo> readUserById(@Header("Authorization") String authorization,
                                       final UUID id);

    @Get(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS + ApiUrlConstants.ProfileUrlConstants.USER_USER_LIST_BY_COMPANYID)
    HttpResponse<UserListEdo> readCompanyUsers(@Header("Authorization") String authorization,
                                               final UUID companyid);

    @Get(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS + ApiUrlConstants.ProfileUrlConstants.USERPROFILE_READ_BY_USERID)
    HttpResponse<ProfileResponseEdo> readUserProfileById(@Header("Authorization") String authorization,
                                                         final String appIdentity,
                                                         final UUID id);

    @Get(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS + ApiUrlConstants.ProfileUrlConstants.USERPROFILE_READ_BY_USERNAME)
    HttpResponse<ProfileResponseEdo> readUserProfileByUsername(@Header("Authorization") String authorization,
                                                               final String appIdentity,
                                                               final String username);


    @Get(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS + ApiUrlConstants.ProfileUrlConstants.USER_USER_LIST_BY_DEPARTMENTID)
    HttpResponse<UserListEdo> readDepartmentUsers(@Header("Authorization") String authorization,
                                                         final UUID id);

    @Get(ApiUrlConstants.ProfileUrlConstants.API001_CORE001_USERS + ApiUrlConstants.ProfileUrlConstants.USERDASHBOARDMENU_READ_BY_USERID)
    HttpResponse<UserDashboardMenuListEdo>
        readUserDashboardMenuByIdentity(@Header("Authorization") String authorization,
                                        final String appIdentity,
                                        final UUID userId);

    @Post(value = ApiUrlConstants.ProfileUrlConstants.USERDASHBOARDMENU_SAVE_BY_USERID)
    public HttpResponse<UserDashboardMenuListEdo>
        saveUserDashboardMenuByIdentity(@Header("Authorization") String authorization,
                                        @Body @Valid final UserDashboardMenuListEdo requestedEdoList,
                                        final String appIdentity,
                                        final UUID userId);
}
