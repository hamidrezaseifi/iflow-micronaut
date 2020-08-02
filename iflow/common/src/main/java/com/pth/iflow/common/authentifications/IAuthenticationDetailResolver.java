package com.pth.iflow.common.authentifications;

import io.micronaut.security.authentication.Authentication;

import java.util.List;
import java.util.UUID;

public interface IAuthenticationDetailResolver {

    UUID resolveUserId(Authentication authentication);

    String resolveUserName(Authentication authentication);

    UUID resolveCustomerId(Authentication authentication);

    List<String> resolveRoleNames(Authentication authentication);

    Boolean containsRoleName(Authentication authentication,
                             String roleSearchString);
}
