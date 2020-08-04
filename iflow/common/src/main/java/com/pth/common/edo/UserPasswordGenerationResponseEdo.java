package com.pth.common.edo;


import javax.validation.constraints.NotNull;

public class UserPasswordGenerationResponseEdo {

    @NotNull
    private String userIdentity;

    @NotNull
    private String companyIdentity;

    @NotNull
    private String passwordHash;

    @NotNull
    private String passwordSalt;

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getCompanyIdentity() {
        return companyIdentity;
    }

    public void setCompanyIdentity(String companyIdentity) {
        this.companyIdentity = companyIdentity;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
}
