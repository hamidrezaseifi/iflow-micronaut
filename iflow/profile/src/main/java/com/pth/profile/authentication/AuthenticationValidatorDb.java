package com.pth.profile.authentication;

import com.pth.common.credentials.IPasswordHashGenerator;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.repositories.IRefreshTokenRepository;
import com.pth.profile.repositories.IUserRepository;
import io.micronaut.context.annotation.Requires;
import io.micronaut.security.authentication.*;
import io.micronaut.security.token.generator.TokenGenerator;
import io.micronaut.security.token.jwt.generator.AccessRefreshTokenGenerator;
import io.micronaut.security.token.jwt.generator.JwtGeneratorConfigurationProperties;
import io.micronaut.security.token.jwt.render.AccessRefreshToken;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
@Requires(property = "micronaut.extensions.project", value = "profile")
public class AuthenticationValidatorDb implements IAuthenticationValidator {

    private final IUserRepository userRepository;
    private final JwtGeneratorConfigurationProperties jwtConfigurationProperties;
    private final IPasswordHashGenerator passwordHashGenerator;
    private final IRefreshTokenRepository refreshTokenRepository;
    private final AccessRefreshTokenGenerator accessRefreshTokenGenerator;

    public AuthenticationValidatorDb(IPasswordHashGenerator passwordHashGenerator,
                                     JwtGeneratorConfigurationProperties jwtConfigurationProperties,
                                     IUserRepository userRepository,
                                     IRefreshTokenRepository refreshTokenRepository,
                                     AccessRefreshTokenGenerator accessRefreshTokenGenerator) {
        super();

        this.passwordHashGenerator = passwordHashGenerator;
        this.jwtConfigurationProperties = jwtConfigurationProperties;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessRefreshTokenGenerator = accessRefreshTokenGenerator;
    }

    @Override
    public AuthenticationResponse evaluate(@SuppressWarnings("rawtypes")
                                                   AuthenticationRequest authenticationRequest) {

        String username = (String) authenticationRequest.getIdentity();
        String password = (String) authenticationRequest.getSecret();

        if (username != null && password != null) {

            Optional<UserEntity> userOptional = userRepository.getByUsername(username);

            if (userOptional.isPresent()) {

                UserEntity userEntity = userOptional.get();

                if (userEntity.isActive()
                    /*&& !authenticationAttemptMonitor.isAttemptProhibited(userEntity.getUsername())*/) {

                    UUID userId = userEntity.getId();
                    UUID companyId = userEntity.getCompanyId();

                    String passwordSalt = userEntity.getPasswordSalt();
                    String passwordHash = passwordHashGenerator.produceHash(password, passwordSalt);

                    boolean isCorrectUsername = username.equalsIgnoreCase(userEntity.getUsername());
                    boolean isCorrectPassword = passwordHash.equals(userEntity.getPasswordHash());

                    if (isCorrectUsername) {

                        if (isCorrectPassword) {

                            //userEntity.setAttempts(0);
                            //authenticationAttemptMonitor.notifyAttemptValid(userEntity.getUsername());

                            List<String> roles = new ArrayList<>();

                            Date issued = new Date();
                            Date expire = java.sql.Timestamp.valueOf(LocalDateTime.now().plusSeconds(
                                    jwtConfigurationProperties.getRefreshTokenExpiration()));

                            roles.add(String.format("uname=%s", username.toLowerCase()));
                            roles.add(String.format("uid=%s", userId));
                            roles.add(String.format("cid=%s", companyId));

                            roles.addAll(userEntity.getRoles()
                                                           .stream()
                                                           .map(r -> String.format("rid=%s", r))
                                                           .collect(Collectors.toList()));
                            Map<String, Object> attr = new HashMap<>();
                            attr.put("issued", issued);
                            attr.put("expire", expire);

                            UserDetails userDetails = new UserDetails(userEntity.getUsername(), roles, attr);
                            Optional<AccessRefreshToken> tokenOptional = this.accessRefreshTokenGenerator.generate(userDetails);

                            if(tokenOptional.isPresent()){
                                this.refreshTokenRepository.updateOrCreate(userEntity.getUsername(),
                                                                           tokenOptional.get().getAccessToken(),
                                                                           tokenOptional.get().getRefreshToken(),
                                                                           issued);
                                return userDetails;
                            }
                            else
                                return new AuthenticationFailed(AuthenticationFailureReason.UNKNOWN);
                        }
                        else
                            return new AuthenticationFailed(AuthenticationFailureReason.CREDENTIALS_DO_NOT_MATCH);
                    }
                    else
                        return new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND);
                }
                else
                    return new AuthenticationFailed(AuthenticationFailureReason.ACCOUNT_LOCKED);
            }
            else
                return new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND);
        }

        return new AuthenticationFailed(AuthenticationFailureReason.USER_NOT_FOUND);
    }
}
