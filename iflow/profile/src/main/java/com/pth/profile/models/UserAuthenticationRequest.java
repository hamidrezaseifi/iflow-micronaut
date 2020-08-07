package com.pth.profile.models;


public class UserAuthenticationRequest {

    private String username;

    private String password;

     private String companyIdentity;

    private String appId;

    public String getUsername() {

        return this.username;
    }

    public void setUsername(final String username) {

        this.username = username;
    }

    public String getPassword() {

        return this.password;
    }

    public void setPassword(final String password) {

        this.password = password;
    }

    public String getCompanyIdentity() {

        return this.companyIdentity;
    }

    public void setCompanyIdentity(final String companyIdentity) {

        this.companyIdentity = companyIdentity;
    }

    public String getAppId() {

        return this.appId;
    }

    public void setAppId(final String appId) {

        this.appId = appId;
    }

}
