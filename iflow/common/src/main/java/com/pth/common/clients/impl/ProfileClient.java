package com.pth.common.clients.impl;

import com.pth.common.declaratives.authentication.IAuthenticationV001DeclarativeClient;
import com.pth.common.edo.TokenValidationRequestEdo;
import com.pth.gui.clients.IProfileClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class ProfileClient implements IProfileClient {

  private final IAuthenticationV001DeclarativeClient authenticationDeclarativeClient;

  public ProfileClient(IAuthenticationV001DeclarativeClient authenticationDeclarativeClient) {
    this.authenticationDeclarativeClient = authenticationDeclarativeClient;
  }

  @Override
  public Optional<BearerAccessRefreshToken> postLogin(UsernamePasswordCredentials credentials) {

    HttpResponse<BearerAccessRefreshToken> response = this.authenticationDeclarativeClient.postLogin(credentials);
    if(response.getStatus() == HttpStatus.OK){
      return response.getBody();
    }

    return Optional.empty();
  }

  @Override
  public Optional<BearerAccessRefreshToken> validateToken(String authorization, TokenValidationRequestEdo requestEdo) {
    HttpResponse<BearerAccessRefreshToken> response =
            this.authenticationDeclarativeClient.validateToken(authorization, requestEdo);
    if(response.getStatus() == HttpStatus.OK){
      return response.getBody();
    }

    return Optional.empty();
  }
}
