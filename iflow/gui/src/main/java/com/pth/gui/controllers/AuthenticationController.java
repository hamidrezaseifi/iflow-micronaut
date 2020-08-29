package com.pth.gui.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.security.Principal;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/auth")
public class AuthenticationController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get(value = "/login")
    public String login(Principal principal) {
        return principal.getName();
    }
}