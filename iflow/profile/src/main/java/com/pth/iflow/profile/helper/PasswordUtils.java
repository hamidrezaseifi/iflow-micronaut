package com.pth.iflow.profile.helper;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils implements IPasswordUtils {

  @Value("${iflow.profile.password-key}")
  private String passwordKey;

  private final Random RANDOM = new SecureRandom();
  private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private final int ITERATIONS = 10000;
  private final int KEY_LENGTH = 256;
  private byte[] passwordKeyArray = null;

  @PostConstruct
  public void init() {

    this.passwordKeyArray = this.passwordKey.getBytes();
  }

  public String getSalt(final int length) {

    final StringBuilder returnValue = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      returnValue.append(this.ALPHABET.charAt(this.RANDOM.nextInt(this.ALPHABET.length())));
    }
    return new String(returnValue);
  }

  public byte[] hash(final char[] password) {

    final PBEKeySpec spec = new PBEKeySpec(password, this.passwordKeyArray, this.ITERATIONS, this.KEY_LENGTH);
    Arrays.fill(password, Character.MIN_VALUE);
    try {
      final SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      return skf.generateSecret(spec).getEncoded();
    }
    catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
    }
    finally {
      spec.clearPassword();
    }
  }

  /* (non-Javadoc)
   * @see com.pth.iflow.profile.helper.IPasswordUtils#generateSecurePassword(java.lang.String)
   */
  @Override
  public String generateSecurePassword(final String password) {

    String returnValue = null;
    final byte[] securePassword = this.hash(password.toCharArray());

    returnValue = Base64.getEncoder().encodeToString(securePassword);

    return returnValue;
  }

  /* (non-Javadoc)
   * @see com.pth.iflow.profile.helper.IPasswordUtils#verifyUserPassword(java.lang.String, java.lang.String)
   */
  @Override
  public boolean verifyUserPassword(final String providedPassword, final String securedPassword) {

    boolean returnValue = false;

    // Generate New secure password with the same salt
    final String newSecurePassword = this.generateSecurePassword(providedPassword);

    // Check if two passwords are equal
    returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);

    return returnValue;
  }
}
