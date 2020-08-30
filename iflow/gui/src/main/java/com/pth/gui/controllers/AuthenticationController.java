package com.pth.gui.controllers;

import com.pth.gui.models.gui.LoginModel;
import io.micronaut.http.HttpParameters;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.cookie.Cookies;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.ModelAndView;

import java.util.Arrays;
import java.util.Optional;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/auth")
public class AuthenticationController {

    @Get(value = "/login")
    public ModelAndView login(Optional<LoginModel> loginModelOptional,
                              @CookieValue(value = "company-id", defaultValue = "") String companyId,
                              @QueryValue(value="returnUrl" , defaultValue="") String returnUrl) {

        LoginModel model = loginModelOptional.isPresent() ? loginModelOptional.get() : new LoginModel();
        if(model.getCompanyId().isEmpty() && companyId.isEmpty() == false){
            model.setCompanyId(companyId);
        }

        return new ModelAndView("/auth/login", model);
    }

    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Post(value = "/authenticate")
    public ModelAndView authenticate(Optional<String> companyid,
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

        ModelAndView mv = new ModelAndView();

        return new ModelAndView("/auth/login", model);
    }
}