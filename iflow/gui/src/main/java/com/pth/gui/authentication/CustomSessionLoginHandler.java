package com.pth.gui.authentication;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.session.SecuritySessionConfiguration;
import io.micronaut.security.session.SessionLoginHandler;
import io.micronaut.security.token.config.TokenConfiguration;
import io.micronaut.session.Session;
import io.micronaut.session.SessionStore;

import javax.inject.Singleton;

@Singleton
@Replaces(SessionLoginHandler.class)
public class CustomSessionLoginHandler extends SessionLoginHandler {


    public CustomSessionLoginHandler(SecuritySessionConfiguration securitySessionConfiguration,
                                     SessionStore<Session> sessionStore,
                                     TokenConfiguration tokenConfiguration) {
        super(securitySessionConfiguration, sessionStore, tokenConfiguration);
    }

    @Override
    public HttpResponse loginSuccess(UserDetails userDetails, HttpRequest<?> request){

        return super.loginSuccess(userDetails, request);
    }

    @Override
    public HttpResponse loginFailed(AuthenticationFailed authenticationFailed){
        return super.loginFailed(authenticationFailed);
    }



}
