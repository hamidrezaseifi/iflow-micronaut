package com.pth.profile.config;

import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;

public interface IAuthenticationValidator {

    AuthenticationResponse evaluate(@SuppressWarnings("rawtypes")
                                            AuthenticationRequest authenticationRequest);
}
