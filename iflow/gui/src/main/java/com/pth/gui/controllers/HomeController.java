package com.pth.gui.controllers;

import com.pth.gui.models.gui.Person;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.ModelAndView;

import java.security.Principal;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller
public class HomeController {

    @Get
    public ModelAndView index() {
        return new ModelAndView("index", new Person(true, "sdelamo"));
    }

    @Produces(MediaType.TEXT_PLAIN)
    @Get("/test")
    public String test(Principal principal) {
        return principal.getName();
    }
}