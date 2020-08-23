package com.pth.clients.profile;

import com.pth.common.edo.UserListEdo;
import io.micronaut.http.annotation.Header;
import io.micronaut.security.authentication.Authentication;

import java.util.Optional;
import java.util.UUID;

public interface IProfile001Client {

    Optional<UserListEdo> getAllDocumentMetaData(String authorization, UUID departmentId);
}
