package com.pth.workflow.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pth.clients.profile.IProfile001Client;
import com.pth.common.edo.UserListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class HomeController {

    private final IProfile001Client profileClient;

    public HomeController(IProfile001Client profileClient) {
        this.profileClient = profileClient;
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get
    public String index(Principal principal, @Header String authorization) {
        return principal.getName();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Get("/test")
    public HttpResponse<UserListEdo> test(Principal principal, @Header String authorization) throws JsonProcessingException {
        Optional<UserListEdo> res = this.profileClient.getAllDocumentMetaData(authorization, UUID.fromString("12ae03f5-81b8-44bc-ac85-4b985d0af7d1"));

        return HttpResponse.ok(res.get());
    }
}