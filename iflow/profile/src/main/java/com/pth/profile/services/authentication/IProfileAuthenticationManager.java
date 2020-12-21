package com.pth.profile.services.authentication;

import com.pth.profile.models.TokenValidationRequest;
import com.pth.profile.models.UserAuthenticationRequest;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public interface IProfileAuthenticationManager {

    Optional<BearerAccessRefreshToken> validateAuthentication(TokenValidationRequest request);
    Optional<BearerAccessRefreshToken> validateToken(String token);
}
