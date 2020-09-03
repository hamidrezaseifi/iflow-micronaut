package com.pth.gui.mapper;

import com.pth.common.edo.UserAuthenticationRequestEdo;
import com.pth.common.edo.UserAuthenticationResponseEdo;
import com.pth.gui.models.UserAuthenticationRequest;
import com.pth.gui.models.UserAuthenticationResponse;

public interface IUserAuthenticationEdoMapper {
  UserAuthenticationResponseEdo toEdo(UserAuthenticationResponse response);

  UserAuthenticationResponse fromEdo(UserAuthenticationResponseEdo edo);

  UserAuthenticationRequestEdo toEdo(UserAuthenticationRequest request);

  UserAuthenticationRequest fromEdo(UserAuthenticationRequestEdo edo);

}
