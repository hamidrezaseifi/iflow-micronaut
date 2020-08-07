package com.pth.profile.services.authentication;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthenticationByUser {

    private Map<UUID, BearerAccessRefreshToken> loggedInUsers = new HashMap<>();


    public AuthenticationByUser() {
    }

    public BearerAccessRefreshToken getAuthentication(UUID userId){

        if(isUserIdLoggedIn(userId)){
            return loggedInUsers.get(userId);
        }
        return  null;
    }

    public boolean isUserIdLoggedIn(UUID userId){

        return loggedInUsers.containsKey(userId);
    }

    public BearerAccessRefreshToken addUser(UUID userId, BearerAccessRefreshToken token){

        loggedInUsers.put(userId, token);
        return getAuthentication(userId);
    }
}
