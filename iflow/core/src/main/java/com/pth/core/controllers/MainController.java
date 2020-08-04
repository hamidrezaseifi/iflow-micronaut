package com.pth.core.controllers;

import com.pth.core.edo.UserAuthenticationRequestEdo;
import com.pth.core.edo.UserPasswordGenerationResponseEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.validation.Validated;

import javax.validation.Valid;

@Validated
//@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/")
public class MainController {


    public MainController(){

    }

    @Produces(MediaType.APPLICATION_JSON)
    //@Secured(SecurityRule.IS_ANONYMOUS)
    @Get(value = "/test")
    public HttpResponse<?> getTasks() {

        UserPasswordGenerationResponseEdo response = new UserPasswordGenerationResponseEdo();
        response.setCompanyIdentity("cccc");
        response.setUserIdentity("uuuuu");
        response.setPasswordHash("pppp");
        response.setPasswordSalt("sssss");


        return HttpResponse.ok(response);
    }



}
