package com.pth.gui.controllers;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;
import io.micronaut.views.ModelAndView;

import java.security.Principal;
import javax.annotation.Nullable;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller
public class HomeController {

    @Get
    @Secured(SecurityRule.IS_ANONYMOUS)
    public ModelAndView index(Session session, @Nullable Principal principal) {
        return new ModelAndView("index", null);
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/test")
    public String test(Principal principal) {
        return principal.getName();
    }
}