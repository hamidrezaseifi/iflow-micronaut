package com.pth.profile.test

import org.apache.commons.lang3.RandomStringUtils
import spock.lang.Specification

import java.nio.charset.Charset
import java.util.UUID

import static java.lang.StringBuilder.*;

class ProfileTestDataProvider extends Specification {

    protected UUID testCompanyId = UUID.fromString("c7ada239-26a7-4402-ad12-ceae751fa7d8")

    protected String generateRandomString(int length) {
        String generatedString = RandomStringUtils.random(length, true, true)

        return generatedString
    }
}
