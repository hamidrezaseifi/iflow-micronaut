package com.pth.gui.authentication;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.handlers.LoginHandler;

import javax.inject.Singleton;

@Singleton
public class GuiLoginHandler implements LoginHandler {
    @Override
    public HttpResponse loginSuccess(UserDetails userDetails,
                                     HttpRequest<?> request) {

        return HttpResponse.ok(userDetails.getAttributes("roles", "uname"));
    }

    @Override
    public HttpResponse loginFailed(AuthenticationFailed authenticationFailed) {
        return HttpResponse.unauthorized();
    }
}
