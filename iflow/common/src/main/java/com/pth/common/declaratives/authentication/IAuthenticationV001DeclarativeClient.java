package com.pth.common.declaratives.authentication;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.TokenValidationRequestEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import javax.validation.constraints.NotBlank;

@Client(id = "profile")
public interface IAuthenticationV001DeclarativeClient {

    @Post(ApiUrlConstants.ProfileUrlConstants.API001_PROFILE001_AUTHENTICATION_SIGNIN)
    HttpResponse<BearerAccessRefreshToken> postLogin(@NotBlank @Body UsernamePasswordCredentials credentials);

    @Post(ApiUrlConstants.ProfileUrlConstants.API001_PROFILE001_AUTHENTICATION + "/validateToken")
    HttpResponse<BearerAccessRefreshToken> validateToken(@Header String authorization,
                                                         @NotBlank @Body TokenValidationRequestEdo requestEdo);
}
