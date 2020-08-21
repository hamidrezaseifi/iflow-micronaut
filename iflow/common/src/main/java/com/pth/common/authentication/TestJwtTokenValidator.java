package com.pth.common.authentication;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.SignedJWT;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.encryption.EncryptionConfiguration;
import io.micronaut.security.token.jwt.signature.SignatureConfiguration;
import io.micronaut.security.token.jwt.validator.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Singleton
@Replaces(JwtTokenValidator.class)
@Requires(property = "micronaut.extensions.project", value = "profile")
@Requires(env = Environment.TEST)
public class TestJwtTokenValidator extends JwtTokenValidator {


    public TestJwtTokenValidator(Collection<SignatureConfiguration> signatureConfigurations,
                                 Collection<EncryptionConfiguration> encryptionConfigurations,
                                 Collection<GenericJwtClaimsValidator> genericJwtClaimsValidators,
                                 JwtAuthenticationFactory jwtAuthenticationFactory) {
        super(signatureConfigurations, encryptionConfigurations, genericJwtClaimsValidators, jwtAuthenticationFactory);

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
            return Flowable.just(authenticationOptional.get());
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
