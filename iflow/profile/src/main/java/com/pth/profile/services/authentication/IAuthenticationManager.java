package com.pth.profile.services.authentication;

import com.pth.profile.models.TokenValidationRequest;
import com.pth.profile.models.UserAuthenticationRequest;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import java.util.Optional;

public interface IAuthenticationManager {

    Optional<BearerAccessRefreshToken> authenticate(UserAuthenticationRequest request);
    Optional<BearerAccessRefreshToken> validateAuthentication(TokenValidationRequest request);
}
