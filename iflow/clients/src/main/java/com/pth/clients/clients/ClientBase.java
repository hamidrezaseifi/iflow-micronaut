package com.pth.clients.clients;

import org.apache.commons.lang3.StringUtils;

public class ClientBase {

    public static final String BEARER = "Bearer";

    public String prepareBearerAuthorization(String authorization){
        if(authorization.startsWith(BEARER)){
            return authorization;
        }
        return String.format("%s %s", BEARER, authorization);
    }

    public static String removeBearer(String token){
        if(token.startsWith(BEARER)){
            token = token.substring(6).trim();
        }
        return token;
    }

}
