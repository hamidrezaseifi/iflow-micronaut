package com.pth.core.controllers;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.validator.AuthenticationJWTClaimsSetAdapter;

import java.security.Principal;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class HomeController {

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    public String index(Principal principal, Authentication authentication, HttpRequest request, @Header String authorization) {
        AuthenticationJWTClaimsSetAdapter authenticationJWTClaimsSetAdapter = (AuthenticationJWTClaimsSetAdapter)authentication;


        return principal.getName();
    }
}