package com.pth.profile.services.authentication;

import com.pth.profile.models.TokenProfileRequest;
import com.pth.profile.models.UserAuthenticationRequest;
import com.pth.profile.models.UserAuthenticationResponse;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

public interface IAuthenticationManager {

    BearerAccessRefreshToken authenticate(UserAuthenticationRequest request);
    void validateAuthentication(TokenProfileRequest request);
}
