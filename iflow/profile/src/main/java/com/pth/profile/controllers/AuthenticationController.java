package com.pth.profile.controllers;

import com.pth.common.contants.ApiUrlConstants;
import com.pth.common.credentials.IPasswordHashGenerator;
import com.pth.common.edo.UserAuthenticationRequestEdo;
import com.pth.common.edo.UserPasswordGenerationResponseEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;

import javax.validation.Valid;

@Validated
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller(ApiUrlConstants.ProfileUrlConstants.API001_PROFILE001_AUTHENTICATION)
public class AuthenticationController {

    private final IPasswordHashGenerator passwordHashGenerator;


    public AuthenticationController(IPasswordHashGenerator passwordHashGenerator){
        this.passwordHashGenerator = passwordHashGenerator;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Post(value = "/signIn")
    public HttpResponse<UserPasswordGenerationResponseEdo> userSignIn(@Body @Valid UserAuthenticationRequestEdo request) {

        UserPasswordGenerationResponseEdo response = new UserPasswordGenerationResponseEdo();
        response.setCompanyIdentity("cccc");
        response.setUserIdentity("uuuuu");
        response.setPasswordHash("pppp");
        response.setPasswordSalt("sssss");


        return HttpResponse.ok(response);
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Post(value = "/createpassword")
    public HttpResponse<UserPasswordGenerationResponseEdo> createPassword(@Body @Valid UserAuthenticationRequestEdo request) {

        String salt = passwordHashGenerator.produceSalt();

        UserPasswordGenerationResponseEdo response = new UserPasswordGenerationResponseEdo();
        response.setCompanyIdentity(request.getCompanyIdentity());
        response.setUserIdentity(request.getUserIdentity());
        response.setPasswordHash(passwordHashGenerator.produceHash(request.getPassword(), salt));
        response.setPasswordSalt(salt);


        return HttpResponse.ok(response);
    }

}
