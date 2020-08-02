package com.pth.iflow.profile.service.handler;

import java.util.Set;

import com.pth.iflow.profile.model.UserAuthenticationSession;

public interface ISessionManager {

  UserAuthenticationSession addSession(String userIdentity, String companyIdentity, final Set<String> roles);

  UserAuthenticationSession findByUserIdentity(String userIdentity);

  UserAuthenticationSession findValidateByUserIdentity(String userIdentity, final String companyIdentity, boolean removeInvalid);

  UserAuthenticationSession findBySessionId(String sessionId);

  UserAuthenticationSession findByToken(String token);

  UserAuthenticationSession updateByToken(String token);

  UserAuthenticationSession updateUser(String userIdentity, String sessionId);

  void removeAllExpiredSessions();

  void removeExpiredSession(UserAuthenticationSession session);

  void removeExpiredSession(String sessionId);

  int getSessionMaxAge();

  void setSessionMaxAge(final int sessionMaxAge);

}
