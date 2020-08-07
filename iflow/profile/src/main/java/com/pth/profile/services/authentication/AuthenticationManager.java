package com.pth.profile.services.authentication;

import com.pth.common.credentials.IPasswordHashGenerator;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.models.TokenProfileRequest;
import com.pth.profile.models.UserAuthenticationRequest;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class AuthenticationManager implements IAuthenticationManager {

    private final IPasswordHashGenerator passwordHashGenerator;
    private final AuthenticationByApp authenticationByApp;
    private final IAuthenticationValidator  authenticationValidator;


    public AuthenticationManager(IPasswordHashGenerator passwordHashGenerator,
                                 IAuthenticationValidator  authenticationValidator,
                                 AuthenticationByApp authenticationByApp){
        this.passwordHashGenerator = passwordHashGenerator;
        this.authenticationValidator = authenticationValidator;
        this.authenticationByApp = authenticationByApp;
    }


    @Override
    public BearerAccessRefreshToken authenticate(UserAuthenticationRequest request) {

        Optional<UserEntity> userOptional = this.authenticationValidator.validate(request);


        if(userOptional.isPresent()){

            UserEntity user = userOptional.get();

            BearerAccessRefreshToken token = new BearerAccessRefreshToken();
            token.setUsername(userOptional.get().getUsername());
            token.setRoles(user.getRoles().stream().collect(Collectors.toList()));

            this.authenticationByApp.addUser(request.getAppId(), user.getCompanyId(), user.getId(), token);


            return token;
        }

        return null;
    }

    @Override
    public void validateAuthentication(TokenProfileRequest authentication) {

    }
}
