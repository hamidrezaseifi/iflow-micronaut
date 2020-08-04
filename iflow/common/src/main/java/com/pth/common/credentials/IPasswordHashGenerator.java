package com.pth.common.credentials;

public interface IPasswordHashGenerator {

    String produceSalt();

    String produceHash(String password,
                       String passwordSalt);
}
