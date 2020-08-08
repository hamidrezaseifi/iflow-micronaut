package com.pth.profile.authentication;

import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    private final IAuthenticationValidator authenticationValidator;

    public AuthenticationProviderUserPassword(@Named("authenticationValidatorDb")
                                                IAuthenticationValidator authenticationValidator) {
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
