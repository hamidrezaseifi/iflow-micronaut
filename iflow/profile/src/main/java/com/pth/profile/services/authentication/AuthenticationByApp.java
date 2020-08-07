package com.pth.profile.services.authentication;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import org.checkerframework.checker.index.qual.SearchIndexBottom;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Singleton
public class AuthenticationByApp {

    private Map<String, AuthenticationByCompany> loggedInApp = new HashMap<>();


    public AuthenticationByApp() {
    }

    public BearerAccessRefreshToken getAuthentication(String appId, UUID companyId, UUID userId){

        if(isUserIdLoggedIn(appId, companyId, userId)){
            return loggedInApp.get(appId).getAuthentication(companyId, userId);
        }
        return null;
    }

    public boolean isUserIdLoggedIn(String appId, UUID companyId, UUID userId){

        return loggedInApp.containsKey(appId) && loggedInApp.get(appId).isUserIdLoggedIn(companyId, userId);
    }

    public BearerAccessRefreshToken addUser(String appId, UUID companyId, UUID userId, BearerAccessRefreshToken token){

        if(loggedInApp.containsKey(appId) == false){
            loggedInApp.put(appId, new AuthenticationByCompany());
        }
        return loggedInApp.get(appId).addUser(companyId, userId, token);
    }
}
