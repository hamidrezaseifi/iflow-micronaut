package com.pth.profile.services.authentication;

import com.pth.common.credentials.IPasswordHashGenerator;
import com.pth.profile.entities.UserEntity;
import com.pth.profile.models.UserAuthenticationRequest;
import com.pth.profile.repositories.IUserRepository;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.UsernamePasswordCredentials;

import java.util.Optional;

public class AuthenticationValidator implements IAuthenticationValidator {

    private final IPasswordHashGenerator passwordHashGenerator;
    private final IUserRepository userRepository;

    public AuthenticationValidator(IPasswordHashGenerator passwordHashGenerator,
                                   IUserRepository userRepository) {
        this.passwordHashGenerator = passwordHashGenerator;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> validate(UserAuthenticationRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();

        Optional<UserEntity> userOptional = this.userRepository.getByUsername(username);
        if(userOptional.isPresent()){
            UserEntity user = userOptional.get();

            String passwordHash = this.passwordHashGenerator.produceHash(username, user.getPasswordSalt());

            if(passwordHash.equals(user.getPasswordHash())){
                return userOptional;
            }
        }
        return Optional.empty();
    }
}
