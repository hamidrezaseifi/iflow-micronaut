package com.pth.gui.controllers;

import com.pth.gui.models.gui.LoginModel;
import io.micronaut.context.MessageSource;
import io.micronaut.http.HttpParameters;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.cookie.Cookies;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;

import javax.inject.Inject;
import java.util.*;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/auth")
public class AuthenticationController {

    private final MessageSource messageSource;

    public AuthenticationController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Get(value = "/login")
    public ModelAndView login(@QueryValue(value="returnUrl" , defaultValue="") String returnUrl) {

        LoginModel model = new LoginModel();

        model.setFailed(false);

        return new ModelAndView("/auth/login", model);
    }

}