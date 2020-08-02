package com.pth.iflow.profile.credentials;

public interface IPasswordHashGenerator {

    String produceSalt();

    String produceHash(String password,
                       String passwordSalt);
}
