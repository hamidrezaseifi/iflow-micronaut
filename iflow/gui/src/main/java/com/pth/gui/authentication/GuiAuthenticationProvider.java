package com.pth.gui.authentication;

import com.pth.common.declaratives.IAuthenticationV001DeclarativeClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.authentication.*;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;

@Singleton
public class GuiAuthenticationProvider implements AuthenticationProvider {

    private final IAuthenticationV001DeclarativeClient authenticationV001DeclarativeClient;

    public GuiAuthenticationProvider(IAuthenticationV001DeclarativeClient authenticationV001DeclarativeClient) {
        this.authenticationV001DeclarativeClient = authenticationV001DeclarativeClient;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        return Flowable.create(emitter -> {

            String username = authenticationRequest.getIdentity().toString();
            String password = authenticationRequest.getSecret().toString();

            UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(username,
                                                                                                      password);

            HttpResponse<BearerAccessRefreshToken> response =
                    this.authenticationV001DeclarativeClient.postLogin(usernamePasswordCredentials);
            if(response.status() == HttpStatus.OK){
                if(response.getBody().isPresent()){
                    //return Flowable.just(authentication);

                    UserDetails userDetails = new UserDetails((String) authenticationRequest.getIdentity(), new ArrayList<>());
                    emitter.onNext(userDetails);
                    emitter.onComplete();
                }
            }
            emitter.onError(new AuthenticationException(new AuthenticationFailed()));

            /*if (authenticationRequest.getIdentity().equals("sherlock") &&
                authenticationRequest.getSecret().equals("password")) {
                UserDetails userDetails = new UserDetails((String) authenticationRequest.getIdentity(), new ArrayList<>());
                emitter.onNext(userDetails);
                emitter.onComplete();
            } else {
                emitter.onError(new AuthenticationException(new AuthenticationFailed()));
            }*/

        }, BackpressureStrategy.ERROR);
    }
}
