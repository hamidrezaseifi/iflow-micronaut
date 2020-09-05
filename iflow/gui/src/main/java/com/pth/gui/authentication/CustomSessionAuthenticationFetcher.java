package com.pth.gui.authentication;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.filters.SecurityFilter;
import io.micronaut.security.session.SessionAuthenticationFetcher;
import io.micronaut.security.session.SessionLoginHandler;
import io.micronaut.session.Session;
import io.micronaut.session.http.HttpSessionFilter;
import io.reactivex.Maybe;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.Optional;

//@Singleton
//@Replaces(SessionAuthenticationFetcher.class)
public class CustomSessionAuthenticationFetcher extends SessionAuthenticationFetcher {

    public CustomSessionAuthenticationFetcher() {
    }

    @Override
    public Publisher<Authentication> fetchAuthentication(HttpRequest<?> request) {
        return super.fetchAuthentication(request);
    }
}
