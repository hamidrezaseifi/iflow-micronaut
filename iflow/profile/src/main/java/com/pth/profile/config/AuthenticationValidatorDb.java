package com.pth.profile.config;

//import de.mediqon.generic.iamcommons.credentials.IPasswordHashGenerator;
//import de.mediqon.generic.iamservice.views.authentifications.AuthenticationView;
//import de.mediqon.generic.iamservice.views.authentifications.IAuthenticationViewSource;
import com.pth.common.credentials.IPasswordHashGenerator;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
public class AuthenticationValidatorDb implements IAuthenticationValidator {

    //private final IAuthenticationViewSource authenticationSource;
    //private final IAuthenticationAttemptMonitor authenticationAttemptMonitor;

    private final IPasswordHashGenerator passwordHashGenerator;

    /*public AuthenticationValidator(IAuthenticationViewSource authenticationSource,
                                   IAuthenticationAttemptMonitor authenticationAttemptMonitor,
                                   IPasswordHashGenerator passwordHashGenerator) {
        super();

        this.authenticationSource = authenticationSource;
        this.authenticationAttemptMonitor = authenticationAttemptMonitor;

        this.passwordHashGenerator = passwordHashGenerator;
    }*/

    public AuthenticationValidatorDb(IPasswordHashGenerator passwordHashGenerator) {
        super();

        this.passwordHashGenerator = passwordHashGenerator;
    }

    @Override
    public AuthenticationResponse evaluate(@SuppressWarnings("rawtypes")
                                                   AuthenticationRequest authenticationRequest) {

        String username = (String) authenticationRequest.getIdentity();
        String password = (String) authenticationRequest.getSecret();

        if (authenticationRequest.getIdentity().equals("sherlock") &&
            authenticationRequest.getSecret().equals("password")) {

            List<String> roles = new ArrayList<>();

            roles.add(String.format("uname=%s", username.toLowerCase()));
            roles.add(String.format("uid=%s", username));

            roles.add(String.format("cid=%s", username));



            return new UserDetails(username, roles);
        }

        /*if (username != null && password != null) {

            Optional<AuthenticationView> authenticationOptional =
                    authenticationSource.getByName(username);

            if (authenticationOptional.isPresent()) {

                AuthenticationView authenticationView = authenticationOptional.get();

                if (!authenticationView.getLocked()
                    && !authenticationAttemptMonitor.isAttemptProhibited(authenticationView.getUsername())) {

                    UUID userId = authenticationView.getId();
                    UUID customerId = authenticationView.getCustomerId();

                    String passwordSalt = authenticationView.getPasswordSalt();
                    String passwordHash = passwordHashGenerator.produceHash(password, passwordSalt);

                    boolean isCorrectUsername = username.equalsIgnoreCase(authenticationView.getUsername());
                    boolean isCorrectPassword = passwordHash.equals(authenticationView.getPasswordHash());

                    if (isCorrectUsername) {

                        if (isCorrectPassword) {

                            authenticationView.setAttempts(0);
                            authenticationAttemptMonitor.notifyAttemptValid(authenticationView.getUsername());

                            List<String> roles = new ArrayList<>();

                            roles.add(String.format("uname=%s", username.toLowerCase()));
                            roles.add(String.format("uid=%s", userId));

                            roles.add(String.format("cid=%s", customerId));

                            roles.addAll(authenticationView.getRoles()
                                                           .stream()
                                                           .map(r -> String.format("rid=%s", r))
                                                           .collect(Collectors.toList()));

                            return new UserDetails(userId.toString(), roles);
                        }
                    }
                }
            }
        }*/

        return new AuthenticationFailed();
    }
}
