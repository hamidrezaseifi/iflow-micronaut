package com.pth.profile.mapper;

import com.pth.common.edo.TokenValidationRequestEdo;
import com.pth.common.edo.UserAuthenticationRequestEdo;
import com.pth.common.edo.UserAuthenticationResponseEdo;
import com.pth.profile.models.TokenValidationRequest;
import com.pth.profile.models.UserAuthenticationRequest;
import com.pth.profile.models.UserAuthenticationResponse;

public interface IUserAuthenticationEdoMapper {
  UserAuthenticationResponseEdo toEdo(UserAuthenticationResponse response);

  UserAuthenticationResponse fromEdo(UserAuthenticationResponseEdo edo);

  UserAuthenticationRequestEdo toEdo(UserAuthenticationRequest request);

  UserAuthenticationRequest fromEdo(UserAuthenticationRequestEdo edo);

  TokenValidationRequestEdo toEdo(TokenValidationRequest request);

  TokenValidationRequest fromEdo(TokenValidationRequestEdo edo);
}
