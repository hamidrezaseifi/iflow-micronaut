package com.pth.common.authentication;

import com.nimbusds.jwt.JWT;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.encryption.EncryptionConfiguration;
import io.micronaut.security.token.jwt.signature.SignatureConfiguration;
import io.micronaut.security.token.jwt.validator.GenericJwtClaimsValidator;
import io.micronaut.security.token.jwt.validator.JwtAuthenticationFactory;
import io.micronaut.security.token.jwt.validator.JwtClaimsValidator;
import io.micronaut.security.token.jwt.validator.JwtTokenValidatorUtils;
import net.minidev.json.JSONArray;

import javax.inject.Singleton;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class TokenProcessor implements IAuthenticationDetailResolver {

  protected final List<SignatureConfiguration> signatureConfigurations = new ArrayList<>();
  protected final List<EncryptionConfiguration> encryptionConfigurations = new ArrayList<>();
  protected final List<GenericJwtClaimsValidator> genericJwtClaimsValidators = new ArrayList<>();
  private final JwtAuthenticationFactory jwtAuthenticationFactory;

  public TokenProcessor(Collection<SignatureConfiguration> signatureConfigurations,
                        Collection<EncryptionConfiguration> encryptionConfigurations,
                        JwtAuthenticationFactory jwtAuthenticationFactory,
                        Collection<GenericJwtClaimsValidator> genericJwtClaimsValidators) {
    this.signatureConfigurations.addAll(signatureConfigurations);
    this.encryptionConfigurations.addAll(encryptionConfigurations);
    this.genericJwtClaimsValidators.addAll(genericJwtClaimsValidators);
    this.jwtAuthenticationFactory = jwtAuthenticationFactory;
  }

  @Override
  public Optional<Authentication> resolveAuthentication(String token){
    Optional<JWT> jwt = this.validateJwtSignatureAndClaims(token, genericJwtClaimsValidators);
    return jwt.isPresent() ? this.jwtAuthenticationFactory.createAuthentication((JWT)jwt.get()) : Optional.empty();
  }

  @Override
  public UUID resolveUserId(Authentication authentication) {

    String userIdString = authentication.getName();

    return UUID.fromString(userIdString);
  }

  @Override
  public String resolveUserName(Authentication authentication) {

    return resolveElements(authentication, "uname").stream()
            .findFirst()
            .get();
  }

  @Override
  public UUID resolveCompanyId(Authentication authentication) {

    String customerIdString = resolveElements(authentication, "cid").stream()
            .findFirst()
            .get();

    return UUID.fromString(customerIdString);
  }

  @Override
  public List<String> resolveRoleNames(Authentication authentication) {

    return resolveElements(authentication, "rid");
  }

  @Override
  public Boolean containsRoleName(Authentication authentication,
                                  String roleSearchString) {

    List<String> roleNames = resolveElements(authentication, "rid");

    return roleNames.stream()
            .anyMatch(rn -> rn.equalsIgnoreCase(roleSearchString));
  }

  private List<String> resolveElements(Authentication authentication,
                                       String elementTypeSearchString) {

    List<String> roleElements = new ArrayList<>();

    if (authentication != null
            && elementTypeSearchString != null
            && !elementTypeSearchString.equals("")) {

      JSONArray roles = (JSONArray) authentication.getAttributes()
              .get("roles");
      roleElements = roles.stream()
              .map(r -> (String) r)
              .filter(r -> r.toLowerCase().startsWith(elementTypeSearchString.toLowerCase() + "="))
              .map(rn -> rn.replace("uname=", ""))
              .map(rn -> rn.replace("uid=", ""))
              .map(rn -> rn.replace("cid=", ""))
              .map(rn -> rn.replace("rid=", ""))
              .collect(Collectors.toList());
    }

    return roleElements;
  }

  private Optional<JWT> validateJwtSignatureAndClaims(String token, Collection<? extends JwtClaimsValidator> claimsValidators) {
    return JwtTokenValidatorUtils.validateJwtSignatureAndClaims(token,
            claimsValidators,
            signatureConfigurations,
            encryptionConfigurations);
  }

}
