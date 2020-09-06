package com.pth.clients.profile;

import com.pth.common.edo.TokenValidationRequestEdo;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import java.util.Optional;

public interface IProfileClient {

  Optional<BearerAccessRefreshToken> postLogin(UsernamePasswordCredentials credentials);
  Optional<BearerAccessRefreshToken> validateToken(String authorization,
                                                   TokenValidationRequestEdo requestEdo);

}
