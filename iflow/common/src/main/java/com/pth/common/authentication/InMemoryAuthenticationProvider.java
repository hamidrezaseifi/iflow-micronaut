package com.pth.common.authentication;

import com.pth.common.enums.UserRoles;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.*;

@Singleton
@Requires(env = Environment.TEST)
public class InMemoryAuthenticationProvider implements AuthenticationProvider {

  public static final String VALID_USER_ADMIN  = "admin";
  public static final String VALID_USER_USER  = "user";
  public static final String VALID_USER_DATAENTRY  = "dt";
  public static final String VALID_PASSWORD  = "password";
  public static final UUID VALID_USER_ID = UUID.fromString("264162a1-a748-49dc-ae2b-7303b04ecfe5");
  public static final UUID VALID_COMPANY_ID = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7d8");

  public InMemoryAuthenticationProvider() {
  }

  @Override
  public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
    if ((authenticationRequest.getIdentity().equals(VALID_USER_ADMIN)
            || authenticationRequest.getIdentity().equals(VALID_USER_USER)) &&
          authenticationRequest.getSecret().equals(VALID_PASSWORD)) {

      String username = (String) authenticationRequest.getIdentity();

      List<String> roles = new ArrayList<>();

      roles.add(String.format("uname=%s", username));
      roles.add(String.format("uid=%s", VALID_USER_ID));

      Date issued = new Date();
      Date expire = java.sql.Timestamp.valueOf(LocalDateTime.now().plusSeconds(7200));

      Map<String, Object> attr = new HashMap<>();
      attr.put("issued", issued);
      attr.put("expire", expire);
      attr.put("uid", VALID_USER_ID);
      attr.put("cid", VALID_COMPANY_ID);
      attr.put("uname", username.toLowerCase());

      roles.add(UserRoles.ROLE_VIEW);
      if(authenticationRequest.getIdentity().equals(VALID_USER_ADMIN)){
        roles.add(UserRoles.ROLE_ADMIN);
        roles.add(UserRoles.ROLE_DATAENTRY);
      }
      if(authenticationRequest.getIdentity().equals(VALID_USER_DATAENTRY)){
        roles.add(UserRoles.ROLE_DATAENTRY);
      }

      return Flowable.just(new UserDetails(VALID_USER_ID.toString(), roles));
    }

    return Flowable.just(new AuthenticationFailed());
  }
}
