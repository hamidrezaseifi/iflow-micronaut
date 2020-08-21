package com.pth.profile.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.edo.TokenValidationRequestEdo;
import com.pth.profile.mapper.IUserAuthenticationEdoMapper;
import com.pth.profile.models.TokenValidationRequest;
import com.pth.profile.services.authentication.IProfileAuthenticationManager;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.validation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller(ApiUrlConstants.ProfileUrlConstants.API001_PROFILE001_AUTHENTICATION)
public class AuthenticationController {

    private final IProfileAuthenticationManager authenticationManager;
    private final IUserAuthenticationEdoMapper authenticationEdoMapper;

    public AuthenticationController(IProfileAuthenticationManager authenticationManager,
                                    IUserAuthenticationEdoMapper authenticationEdoMapper){
        this.authenticationManager = authenticationManager;
        this.authenticationEdoMapper = authenticationEdoMapper;
    }



    @Produces(MediaType.APPLICATION_JSON)
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post(value = "/validateToken")
    public HttpResponse<?> validateToken(@Body @Valid TokenValidationRequestEdo requestEdo) {

        TokenValidationRequest request =  this.authenticationEdoMapper.fromEdo(requestEdo);

        Optional<BearerAccessRefreshToken> resultOptional = this.authenticationManager.validateAuthentication(request);
        if(resultOptional.isPresent()){
            return HttpResponse.ok(resultOptional.get());
        }
        return HttpResponse.unauthorized();
    }

}
