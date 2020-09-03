package com.pth.common.authentication;

import io.micronaut.security.authentication.Authentication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAuthenticationDetailResolver {

    Optional<Authentication> resolveAuthentication(String token);

    UUID resolveUserId(Authentication authentication);

    String resolveUserName(Authentication authentication);

    UUID resolveCompanyId(Authentication authentication);

    List<String> resolveRoleNames(Authentication authentication);

    Boolean containsRoleName(Authentication authentication, String roleSearchString);
}
