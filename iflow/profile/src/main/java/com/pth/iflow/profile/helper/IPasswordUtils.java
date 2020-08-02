package com.pth.iflow.profile.helper;

public interface IPasswordUtils {

  String generateSecurePassword(String password);

  boolean verifyUserPassword(String providedPassword, String securedPassword);

}
