package com.pth.common.authentication;

import com.pth.common.enums.UserRoles;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Singleton
@Requires(env = Environment.TEST)
public class InMemoryAuthenticationProvider implements AuthenticationProvider {

  public static final String VALID_USER_ADMIN  = "admin";
  public static final String VALID_USER_USER  = "user";
  public static final String VALID_PASSWORD  = "password";
  public static final UUID VALID_USER_ID = UUID.fromString("264162a1-a748-49dc-ae2b-7303b04ecfe5");

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
      roles.add(String.format("rid=%s", UserRoles.ADMIN.getId()));
      roles.add(String.format("rid=%s", UserRoles.DATAENTRY.getId()));
      roles.add(String.format("rid=%s", UserRoles.VIEW.getId()));


      return Flowable.just(new UserDetails(VALID_USER_ID.toString(), roles));
    }

    return Flowable.just(new AuthenticationFailed());
  }
}
