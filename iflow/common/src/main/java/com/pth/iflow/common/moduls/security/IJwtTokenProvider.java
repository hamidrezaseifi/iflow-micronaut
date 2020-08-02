package com.pth.iflow.common.moduls.security;

import java.util.Date;
import java.util.Set;

public interface IJwtTokenProvider {

  String createToken(String username, Set<String> roles);

  String getUsername(String token);

  Date getTokenExpireDate(String token);

  Date getTokenIssuedDate(String token);

}
