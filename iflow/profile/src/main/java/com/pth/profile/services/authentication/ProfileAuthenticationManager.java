package com.pth.profile.services.authentication;

import com.pth.clients.clients.ClientBase;
import com.pth.common.authentication.IAuthenticationDetailResolver;
import com.pth.common.credentials.IPasswordHashGenerator;
import com.pth.profile.authentication.entities.RefreshTokenEntity;
import com.pth.profile.models.TokenValidationRequest;
import com.pth.profile.repositories.IRefreshTokenRepository;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.generator.JwtGeneratorConfigurationProperties;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import javax.inject.Singleton;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Singleton
public class ProfileAuthenticationManager implements IProfileAuthenticationManager {

    private final IPasswordHashGenerator passwordHashGenerator;
    private final IRefreshTokenRepository refreshTokenRepository;
    private final JwtGeneratorConfigurationProperties jwtConfigurationProperties;
    private final IAuthenticationDetailResolver authenticationDetailResolver;

    public ProfileAuthenticationManager(IPasswordHashGenerator passwordHashGenerator,
                                        IRefreshTokenRepository refreshTokenRepository,
                                        JwtGeneratorConfigurationProperties jwtConfigurationProperties,
                                        IAuthenticationDetailResolver authenticationDetailResolver){
        this.passwordHashGenerator = passwordHashGenerator;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtConfigurationProperties = jwtConfigurationProperties;
        this.authenticationDetailResolver = authenticationDetailResolver;
    }

    @Override
    public Optional<BearerAccessRefreshToken> validateAuthentication(TokenValidationRequest tokenProfileRequest) {


        Authentication authentication = tokenProfileRequest.getAuthentication();
        return validateAuthenticationAndToken(authentication, tokenProfileRequest.getToken());
    }

    @Override
    public Optional<BearerAccessRefreshToken> validateToken(String token) {
        token = ClientBase.removeBearer(token);

        Optional<Authentication> authenticationOptional =  authenticationDetailResolver.resolveAuthentication(token);

        if(authenticationOptional.isPresent()){
            return validateAuthenticationAndToken(authenticationOptional.get(), token);
        }
        return Optional.empty();
    }

    private Optional<BearerAccessRefreshToken> validateAuthenticationAndToken(Authentication authentication,
                                                                              String token) {


        String username = authentication.getAttributes().get("sub").toString();
        List<String> roles = (List<String>)authentication.getAttributes().get("roles");

        Optional<RefreshTokenEntity> refreshTokenEntityOptional =
                this.refreshTokenRepository.findByUsername(username);
        if(refreshTokenEntityOptional.isPresent()){

            RefreshTokenEntity refreshTokenEntity = refreshTokenEntityOptional.get();

            if(token.equals(refreshTokenEntity.getRefreshToken())){

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
