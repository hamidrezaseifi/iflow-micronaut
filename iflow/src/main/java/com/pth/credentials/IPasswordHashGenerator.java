package com.pth.credentials;

public interface IPasswordHashGenerator {

    String produceSalt();

    String produceHash(String password,
                       String passwordSalt);
}
