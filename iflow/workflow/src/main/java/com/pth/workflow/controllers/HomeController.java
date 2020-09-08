package com.pth.workflow.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.security.Principal;


@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class HomeController {

    public HomeController() {

    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    public String index(Principal principal, @Header String authorization) {
        return principal.getName();
    }


}