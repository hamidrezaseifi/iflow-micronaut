package com.pth.iflow.profile.credentials;

import org.apache.commons.lang3.ArrayUtils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Singleton;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Singleton
public class HmacSha512HashProvider implements ISecureHashProvider {

    private static final Charset UTF8 = StandardCharsets.UTF_8;

    public String hashMessage(final String message,
                              final String salt,
                              final String pepper,
                              final int iterations,
                              final int length) {

        char[] messageBytes = message.toCharArray();
        byte[] saltBytes = salt.getBytes(UTF8);
        byte[] pepperBytes = pepper.getBytes(UTF8);

        byte[] hashBytes = hashMessage(messageBytes,
                                       saltBytes,
                                       pepperBytes,
                                       iterations,
                                       length);

        return Base64.getEncoder().encodeToString(hashBytes);
        //return new String(hashBytes, utf8);
    }

    public byte[] hashMessage(final char[] message,
                              final byte[] salt,
                              final byte[] pepper,
                              final int iterations,
                              final int length) {

        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");

            byte[] saltAndPepper = ArrayUtils.addAll(salt, pepper);
            int lengthBytes = length * 8;

            PBEKeySpec pbKeySpec = new PBEKeySpec(message,
                                                  saltAndPepper,
                                                  iterations,
                                                  lengthBytes);

            SecretKey hash = secretKeyFactory.generateSecret(pbKeySpec);

            return hash.getEncoded();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
            throw new RuntimeException(exception);
        }
    }
}