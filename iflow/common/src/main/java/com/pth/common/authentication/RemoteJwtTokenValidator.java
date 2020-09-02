package com.pth.common.authentication;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import com.pth.common.declaratives.IAuthenticationV001DeclarativeClient;
import com.pth.common.edo.TokenValidationRequestEdo;
import com.pth.common.edo.enums.EApplication;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.encryption.EncryptionConfiguration;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
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
@Requires(property = "micronaut.extensions.project", notEquals = "profile")
@Requires(property = "micronaut.extensions.project", notEquals = "gui")
public class RemoteJwtTokenValidator extends JwtTokenValidator {

    private final IAuthenticationV001DeclarativeClient authenticationV001DeclarativeClient;

    public RemoteJwtTokenValidator(Collection<SignatureConfiguration> signatureConfigurations,
                                   Collection<EncryptionConfiguration> encryptionConfigurations,
                                   Collection<GenericJwtClaimsValidator> genericJwtClaimsValidators,
                                   JwtAuthenticationFactory jwtAuthenticationFactory,
                                   IAuthenticationV001DeclarativeClient authenticationV001DeclarativeClient) {
        super(signatureConfigurations, encryptionConfigurations, genericJwtClaimsValidators, jwtAuthenticationFactory);

        this.authenticationV001DeclarativeClient = authenticationV001DeclarativeClient;
    }

    public Optional<JWT> validatePlainJWTSignature(JWT jwt) {
        return JwtTokenValidatorUtils.validatePlainJWTSignature(jwt, this.signatureConfigurations);
    }

    public Optional<JWT> validateSignedJWTSignature(SignedJWT signedJWT) {
        return JwtTokenValidatorUtils.validateSignedJWTSignature(signedJWT, this.signatureConfigurations);
    }


    public Publisher<Authentication> validateToken(String token) {
        Optional<Authentication> authenticationOptional =
                this.authenticationIfValidJwtSignatureAndClaims(token, this.genericJwtClaimsValidators);

        if(authenticationOptional.isPresent()){

            Authentication authentication = authenticationOptional.get();

            TokenValidationRequestEdo requestEdo = new TokenValidationRequestEdo();
            requestEdo.setAppId(EApplication.IFLOW.getIdentity());
            requestEdo.setAuthentication(authentication);
            requestEdo.setToken(token);

            HttpResponse<BearerAccessRefreshToken> response =
                    this.authenticationV001DeclarativeClient.validateToken(token, requestEdo);
            if(response.status() == HttpStatus.OK){
                if(response.getBody().isPresent()){
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
