package com.pth.clients.clients.profile;

import com.pth.common.edo.TokenValidationRequestEdo;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import java.util.Optional;

public interface IProfileClient {

  Optional<BearerAccessRefreshToken> postLogin(UsernamePasswordCredentials credentials);
  Optional<BearerAccessRefreshToken> validateTokenRequest(String authorization, TokenValidationRequestEdo requestEdo);
  Optional<BearerAccessRefreshToken> validateToken(String authorization);

}
