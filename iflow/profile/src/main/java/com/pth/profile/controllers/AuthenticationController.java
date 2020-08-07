package com.pth.profile.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.TokenProfileRequestEdo;
import com.pth.common.edo.UserAuthenticationRequestEdo;
import com.pth.common.edo.UserAuthenticationResponseEdo;
import com.pth.profile.mapper.UserAuthenticationEdoMapper;
import com.pth.profile.models.TokenProfileRequest;
import com.pth.profile.models.UserAuthenticationRequest;
import com.pth.profile.models.UserAuthenticationResponse;
import com.pth.profile.services.authentication.IAuthenticationManager;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller(ApiUrlConstants.ProfileUrlConstants.API001_PROFILE001_AUTHENTICATION)
public class AuthenticationController {

    private final IAuthenticationManager authenticationManager;
    private final UserAuthenticationEdoMapper authenticationEdoMapper;

    public AuthenticationController(IAuthenticationManager authenticationManager,
                                    UserAuthenticationEdoMapper authenticationEdoMapper){
        this.authenticationManager = authenticationManager;
        this.authenticationEdoMapper = authenticationEdoMapper;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Post(value = "/signIn")
    public HttpResponse<BearerAccessRefreshToken> userSignIn(@Body @Valid UserAuthenticationRequestEdo requestEdo) {

        UserAuthenticationRequest request =  this.authenticationEdoMapper.fromEdo(requestEdo);

        BearerAccessRefreshToken response = this.authenticationManager.authenticate(request);

        return HttpResponse.ok(response);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post(value = "/validateToken")
    public HttpResponse<?> validateAuthentication(@Body @Valid TokenProfileRequestEdo requestEdo) {

        TokenProfileRequest request =  this.authenticationEdoMapper.fromEdo(requestEdo);

        this.authenticationManager.validateAuthentication(request);
        return HttpResponse.ok();
    }

}
