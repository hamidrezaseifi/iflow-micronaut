package com.pth.profile.config;

import edu.umd.cs.findbugs.annotations.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.ArrayList;

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
