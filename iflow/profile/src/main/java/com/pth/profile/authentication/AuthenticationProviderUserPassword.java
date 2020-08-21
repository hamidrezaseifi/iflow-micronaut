package com.pth.profile.authentication;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;

@Singleton
@Requires(notEnv = Environment.TEST)
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    private final IAuthenticationValidator authenticationValidator;

    public AuthenticationProviderUserPassword(IAuthenticationValidator authenticationValidator) {
        super();

        this.authenticationValidator = authenticationValidator;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(@SuppressWarnings("rawtypes")
                                                                  AuthenticationRequest authenticationRequest) {

        AuthenticationResponse authenticationResponse =
                authenticationValidator.evaluate(authenticationRequest);

        return Flowable.just(authenticationResponse);
    }
}
