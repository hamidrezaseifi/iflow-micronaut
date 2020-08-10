package com.pth.common.authentication;

import com.pth.common.credentials.IPasswordHashGenerator;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import io.micronaut.security.authentication.*;
import io.micronaut.security.token.jwt.generator.JwtGeneratorConfigurationProperties;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
@ConfigurationProperties("team")
@Requires(property = "micronaut.extensions.project", notEquals = "profile")
public class RemoteAuthenticationValidator implements IAuthenticationValidator {

    public static final String LOGEDIN_INITIAL_TOKEN = "logedin";
    private final JwtGeneratorConfigurationProperties jwtConfigurationProperties;
    private final IPasswordHashGenerator passwordHashGenerator;

    public RemoteAuthenticationValidator(IPasswordHashGenerator passwordHashGenerator,
                                         JwtGeneratorConfigurationProperties jwtConfigurationProperties) {
        super();

        this.passwordHashGenerator = passwordHashGenerator;
        this.jwtConfigurationProperties = jwtConfigurationProperties;
    }

    @Override
    public AuthenticationResponse evaluate(@SuppressWarnings("rawtypes")
                                                   AuthenticationRequest authenticationRequest) {

        String username = (String) authenticationRequest.getIdentity();
        String password = (String) authenticationRequest.getSecret();

        if (username != null && password != null) {



        }

        return new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND);
    }
}
