package com.pth.profile.authentication;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import com.pth.profile.authentication.entities.RefreshTokenEntity;
import com.pth.profile.repositories.IRefreshTokenRepository;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.encryption.EncryptionConfiguration;
import io.micronaut.security.token.jwt.signature.SignatureConfiguration;
import io.micronaut.security.token.jwt.validator.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Singleton
@Replaces(JwtTokenValidator.class)
@Requires(property = "micronaut.extensions.project", value = "profile")
public class CustomJwtTokenValidator extends JwtTokenValidator {

    private final IRefreshTokenRepository refreshTokenRepository;

    public CustomJwtTokenValidator(Collection<SignatureConfiguration> signatureConfigurations,
                                   Collection<EncryptionConfiguration> encryptionConfigurations,
                                   Collection<GenericJwtClaimsValidator> genericJwtClaimsValidators,
                                   JwtAuthenticationFactory jwtAuthenticationFactory,
                                   IRefreshTokenRepository refreshTokenRepository) {
        super(signatureConfigurations, encryptionConfigurations, genericJwtClaimsValidators, jwtAuthenticationFactory);

        this.refreshTokenRepository = refreshTokenRepository;
    }

    public Optional<JWT> validatePlainJWTSignature(JWT jwt) {
        return JwtTokenValidatorUtils.validatePlainJWTSignature(jwt, this.signatureConfigurations);
    }

    public Optional<JWT> validateSignedJWTSignature(SignedJWT signedJWT) {
        return JwtTokenValidatorUtils.validateSignedJWTSignature(signedJWT, this.signatureConfigurations);
    }


    public Publisher<Authentication> validateToken(String token) {
        Optional<Authentication> authenticationOptional = this.authenticationIfValidJwtSignatureAndClaims(token, this.genericJwtClaimsValidators);

        if(authenticationOptional.isPresent()){

            Authentication authentication = authenticationOptional.get();

            String username = authentication.getAttributes().get("sub").toString();
            Date issuedAt = (Date)authentication.getAttributes().get("iat");

            Optional<RefreshTokenEntity> refreshTokenEntityOptional =
                    this.refreshTokenRepository.findByUsername(username);
            if(refreshTokenEntityOptional.isPresent()){

                RefreshTokenEntity refreshTokenEntity = refreshTokenEntityOptional.get();

                if(AuthenticationValidatorDb.LOGEDIN_INITIAL_TOKEN.equals(refreshTokenEntity.getRefreshToken()) ||
                   token.equals(refreshTokenEntity.getRefreshToken())){

                    if(AuthenticationValidatorDb.LOGEDIN_INITIAL_TOKEN.equals(refreshTokenEntity.getRefreshToken())){
                        refreshTokenEntity.setIssuedAt(issuedAt);
                        refreshTokenEntity.setRefreshToken(token);
                        this.refreshTokenRepository.update(refreshTokenEntity);
                    }
                    return Flowable.just(authentication);
                }
            }
        }

        return Flowable.empty();
    }

    public Optional<Authentication> authenticationIfValidJwtSignatureAndClaims(String token, Collection<? extends JwtClaimsValidator> claimsValidators) {
        Optional<JWT> jwt = this.validateJwtSignatureAndClaims(token, claimsValidators);
        return jwt.isPresent() ? this.jwtAuthenticationFactory.createAuthentication((JWT)jwt.get()) : Optional.empty();
    }

    public Optional<JWT> validateJwtSignatureAndClaims(String token) {
        return this.validateJwtSignatureAndClaims(token, this.genericJwtClaimsValidators);
    }

    public boolean validate(String token) {
        return this.validateJwtSignatureAndClaims(token).isPresent();
    }

    public boolean validate(String token, Collection<? extends JwtClaimsValidator> claimsValidators) {
        return this.validateJwtSignatureAndClaims(token, claimsValidators).isPresent();
    }

    public List<SignatureConfiguration> getSignatureConfigurations() {
        return this.signatureConfigurations;
    }

    public List<EncryptionConfiguration> getEncryptionConfigurations() {
        return this.encryptionConfigurations;
    }

    public List<GenericJwtClaimsValidator> getGenericJwtClaimsValidators() {
        return this.genericJwtClaimsValidators;
    }

}
