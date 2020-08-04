package com.pth.common.credentials;

import javax.inject.Singleton;

import io.micronaut.context.annotation.Property;

import java.util.UUID;

@Singleton
public class PasswordHashGenerator implements IPasswordHashGenerator {

    private final ISecureHashProvider secureHashProvider;
    private String passwordPepper;

    public PasswordHashGenerator(@Property(name = "micronaut.extensions.securities.passwordpepper")
                                         String passwordPepper,
                                 ISecureHashProvider secureHashProvider) {
        super();

        this.passwordPepper = passwordPepper;
        this.secureHashProvider = secureHashProvider;
    }

    @Override
    public String produceSalt() {

        return String.format("%s#%s",
                             UUID.randomUUID(),
                             UUID.randomUUID());
    }

    @Override
    public String produceHash(String password,
                              String passwordSalt) {

        return secureHashProvider.hashMessage(password,
                                              passwordSalt,
                                              passwordPepper,
                                              120000,
                                              64);
    }
}