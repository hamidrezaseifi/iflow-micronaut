package com.pth.iflow.common.authentifications;

import io.micronaut.security.authentication.Authentication;
import net.minidev.json.JSONArray;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class AuthenticationDetailResolver implements IAuthenticationDetailResolver {

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
    public UUID resolveCustomerId(Authentication authentication) {

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
}