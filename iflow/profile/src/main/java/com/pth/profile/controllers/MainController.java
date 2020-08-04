package com.pth.profile.controllers;

import com.pth.common.credentials.IPasswordHashGenerator;
import com.pth.common.edo.UserAuthenticationRequestEdo;
import com.pth.common.edo.UserPasswordGenerationResponseEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.validation.Validated;
import io.micronaut.security.annotation.Secured;

import javax.validation.Valid;

@Validated
@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/")
public class MainController {

    private final IPasswordHashGenerator passwordHashGenerator;


    public MainController(IPasswordHashGenerator passwordHashGenerator){
        this.passwordHashGenerator = passwordHashGenerator;
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get(value = "/test")
    public HttpResponse<UserPasswordGenerationResponseEdo> getTasks() {

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
