package com.pth.gui.authentication;

import com.pth.common.authentication.IAuthenticationDetailResolver;
import com.pth.common.clients.IProfileClient;
import com.pth.common.clients.IUserClient;
import com.pth.common.edo.ProfileResponseEdo;
import com.pth.common.edo.enums.EApplication;
import io.micronaut.security.authentication.*;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class GuiAuthenticationProvider implements AuthenticationProvider {

    private final IAuthenticationDetailResolver authenticationDetailResolver;
    private final IProfileClient profileClient;
    private final IUserClient userClient;

    public GuiAuthenticationProvider(IAuthenticationDetailResolver authenticationDetailResolver,
                                     IProfileClient profileClient,
                                     IUserClient userClient) {
        this.authenticationDetailResolver = authenticationDetailResolver;

        this.profileClient = profileClient;
        this.userClient = userClient;
    }

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        return Flowable.create(emitter -> {

            String username = authenticationRequest.getIdentity().toString();
            String password = authenticationRequest.getSecret().toString();

            UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(username,
                                                                                                      password);

            Optional<BearerAccessRefreshToken> accessRefreshToken = this.profileClient.postLogin(usernamePasswordCredentials);
            if(accessRefreshToken.isPresent()){
                    BearerAccessRefreshToken bearerAccessRefreshToken = accessRefreshToken.get();

                    String refreshToken = bearerAccessRefreshToken.getRefreshToken();

                    Optional<Authentication> authenticationOptional = this.authenticationDetailResolver.resolveAuthentication(refreshToken);

                    if(authenticationOptional.isPresent()){
                        Authentication authentication = authenticationOptional.get();
                        UUID userId = this.authenticationDetailResolver.resolveUserId(authentication);
                        UUID companyId = this.authenticationDetailResolver.resolveCompanyId(authentication);

                        Optional<ProfileResponseEdo> profileResponseEdoOptional =
                                this.userClient.readUserProfileById(refreshToken, EApplication.IFLOW.getIdentity(), userId);

                        if(profileResponseEdoOptional.isPresent()){
                            ProfileResponseEdo profileResponseEdo = profileResponseEdoOptional.get();

                            Collection<String> roles = bearerAccessRefreshToken.getRoles();

                            Map<String, Object> attributes = new HashMap<>();
                            attributes.put("access_token" , bearerAccessRefreshToken.getAccessToken());
                            attributes.put("refresh_token" , refreshToken);
                            attributes.put("expires_in" , bearerAccessRefreshToken.getExpiresIn());
                            attributes.put("userId" , userId);
                            attributes.put("companyId" , companyId);

                            UserDetails userDetails = new UserDetails(bearerAccessRefreshToken.getUsername(),
                                    roles,
                                    attributes);
                            emitter.onNext(userDetails);
                            emitter.onComplete();
                        }


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
