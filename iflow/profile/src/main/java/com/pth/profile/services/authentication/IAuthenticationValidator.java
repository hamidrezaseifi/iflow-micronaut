package com.pth.profile.services.authentication;

import com.pth.profile.entities.UserEntity;
import com.pth.profile.models.UserAuthenticationRequest;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.UsernamePasswordCredentials;

import java.util.Optional;

public interface IAuthenticationValidator {

    Optional<UserEntity> validate(UserAuthenticationRequest request);
}
