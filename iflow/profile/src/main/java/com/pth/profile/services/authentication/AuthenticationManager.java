package com.pth.profile.services.authentication;

import com.pth.common.credentials.IPasswordHashGenerator;
import com.pth.profile.authentication.AuthenticationValidatorDb;
import com.pth.profile.authentication.entities.RefreshTokenEntity;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.models.TokenValidationRequest;
import com.pth.profile.models.UserAuthenticationRequest;
import com.pth.profile.repositories.IRefreshTokenRepository;
import com.pth.profile.repositories.IUserRepository;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.generator.JwtGeneratorConfigurationProperties;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.reactivex.Flowable;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Singleton
public class AuthenticationManager implements IAuthenticationManager {

    private final IPasswordHashGenerator passwordHashGenerator;
    private final IAuthenticationValidator  authenticationValidator;
    private final IRefreshTokenRepository refreshTokenRepository;
    private final JwtGeneratorConfigurationProperties jwtConfigurationProperties;

    public AuthenticationManager(IPasswordHashGenerator passwordHashGenerator,
                                 IAuthenticationValidator  authenticationValidator,
                                 IRefreshTokenRepository refreshTokenRepository,
                                 JwtGeneratorConfigurationProperties jwtConfigurationProperties){
        this.passwordHashGenerator = passwordHashGenerator;
        this.authenticationValidator = authenticationValidator;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtConfigurationProperties = jwtConfigurationProperties;
    }


    @Override
    public Optional<BearerAccessRefreshToken> authenticate(UserAuthenticationRequest request) {



        return Optional.empty();
    }

    @Override
    public Optional<BearerAccessRefreshToken> validateAuthentication(TokenValidationRequest tokenProfileRequest) {


        Authentication authentication = tokenProfileRequest.getAuthentication();
        String username =authentication.getAttributes().get("sub").toString();
        Date issuedAt = new Date((long)authentication.getAttributes().get("iat"));  ;
        List<String> roles = (List<String>)authentication.getAttributes().get("roles");


        Optional<RefreshTokenEntity> refreshTokenEntityOptional =
                this.refreshTokenRepository.findByUsername(username);
        if(refreshTokenEntityOptional.isPresent()){

            RefreshTokenEntity refreshTokenEntity = refreshTokenEntityOptional.get();

            if(tokenProfileRequest.getToken().equals(refreshTokenEntity.getRefreshToken())){

                return Optional.of(new BearerAccessRefreshToken(username,
                                                                roles,
                                                    jwtConfigurationProperties.getRefreshTokenExpiration(),
                                                    refreshTokenEntity.getRefreshToken(),
                                                    refreshTokenEntity.getRefreshToken(),
                                                    "bearer"));
             }
        }
        return Optional.empty();
    }
}
