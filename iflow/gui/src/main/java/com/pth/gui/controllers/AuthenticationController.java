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
    public ModelAndView login(@Body LoginModel loginModel,
                              @CookieValue(value = "company-id", defaultValue = "") String companyId,
                              @QueryValue(value="returnUrl" , defaultValue="") String returnUrl) {

        LoginModel model = new LoginModel();
        if(loginModel != null){
            model.setCompanyId(loginModel.getCompanyId());
            model.setPassword(loginModel.getPassword());
            model.setUsername(loginModel.getUsername());
        }

        if(model.getCompanyId().isEmpty() && companyId.isEmpty() == false){
            model.setCompanyId(companyId);
        }
        model.setFailed(false);

        return new ModelAndView("/auth/login", model);
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post(value = "/authenticate")
    public ModelAndView authenticate(@Body LoginModel loginModel,
                                     @CookieValue(value = "company-id", defaultValue = "") String companyId,
                                     @QueryValue(value="returnUrl" , defaultValue="") String returnUrl) {

        LoginModel model = new LoginModel();
        if(loginModel != null){
            model.setCompanyId(loginModel.getCompanyId() + "-authed");
            model.setPassword(loginModel.getPassword() + "-authed");
            model.setUsername(loginModel.getUsername() + "-authed");
        }
        if(model.getCompanyId().isEmpty() && companyId.isEmpty() == false){
            model.setCompanyId(companyId);
        }
        model.setFailed(false);

        return new ModelAndView("/auth/login", model);
    }

    @Produces(MediaType.TEXT_HTML)
    @Get("/authFailed") // <5>
    public ModelAndView authFailed(Optional<String> companyid,
                                   Optional<String> password,
                                   Optional<String> username,
                                   @CookieValue(value = "company-id", defaultValue = "") String companyId,
                                   @QueryValue(value="returnUrl" , defaultValue="") String returnUrl,
                                   HttpRequest request,
                                   HttpResponse response) {

        LoginModel model = new LoginModel();
        if(companyid.isPresent()){
            model.setCompanyId(companyid.get());
            response.setAttribute("Set-Cookie" , Arrays.asList("company-id=" + companyid.get()));
        }
        if(password.isPresent()){
            model.setPassword(password.get());
        }
        if(username.isPresent()){
            model.setUsername(username.get());
        }
        if(model.getCompanyId().isEmpty() && companyId.isEmpty() == false){
            model.setCompanyId(companyId);
        }
        model.setFailed(true);

        return new ModelAndView("/auth/login", model);
    }
}