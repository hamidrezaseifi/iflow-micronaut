package com.pth.clients;

public class ClientBase {

    public String prepareBearerAuthorization(String authorization){
        return String.format("Bearer %s", authorization);
    }
}
