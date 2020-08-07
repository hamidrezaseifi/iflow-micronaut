package com.pth.profile.services.authentication;

import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthenticationByCompany {

    private Map<UUID, AuthenticationByUser> loggedInCompanies = new HashMap<>();


    public AuthenticationByCompany() {
    }

    public BearerAccessRefreshToken getAuthentication(UUID companyId, UUID userId){

        if(isUserIdLoggedIn(companyId, userId)){
            return loggedInCompanies.get(companyId).getAuthentication(userId);
        }
        return  null;
    }

    public boolean isUserIdLoggedIn(UUID companyId, UUID userId){

        return loggedInCompanies.containsKey(companyId) && loggedInCompanies.get(companyId).isUserIdLoggedIn(userId);
    }

    public BearerAccessRefreshToken addUser(UUID companyId, UUID userId, BearerAccessRefreshToken token){

        if(loggedInCompanies.containsKey(companyId) == false){
            loggedInCompanies.put(companyId, new AuthenticationByUser());
        }
        return loggedInCompanies.get(companyId).addUser(userId, token);
    }
}
