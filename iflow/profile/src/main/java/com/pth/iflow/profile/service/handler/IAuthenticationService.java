package com.pth.iflow.profile.service.handler;

import com.pth.iflow.profile.model.UserAuthenticationRequest;

public interface IAuthenticationService {

  UserAuthenticationRequest authenticate(final UserAuthenticationRequest user);

  UserAuthenticationRequest setAuthentication(final UserAuthenticationRequest user);

  void deleteAuthentication(UserAuthenticationRequest request);
}
