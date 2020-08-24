package com.pth.clients.profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pth.common.edo.UserListEdo;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.authentication.Authentication;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class Profile001Client implements IProfile001Client {

    private final IProfile001DeclarativeClient profileDeclarativeClient;

    public Profile001Client(IProfile001DeclarativeClient profileDeclarativeClient) {
        this.profileDeclarativeClient = profileDeclarativeClient;
    }

    @Override
    public Optional<UserListEdo> getAllDocumentMetaData(String authorization, UUID departmentId) {

        HttpResponse<UserListEdo> response =
                profileDeclarativeClient.getAllDocumentMetaData(authorization, departmentId);

        if(response.status() == HttpStatus.OK && response.body() != null){
            return Optional.of(response.body());
        }
        return Optional.empty();
    }
}
