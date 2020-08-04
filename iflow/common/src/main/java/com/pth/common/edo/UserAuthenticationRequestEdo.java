package com.pth.common.edo;

import javax.validation.constraints.NotNull;


public class UserAuthenticationRequestEdo {

    @NotNull(message = "UserIdentity must not be null")
    private String userIdentity;

    @NotNull(message = "Password must not be null")
    private String password;

    @NotNull(message = "CompanyIdentity must not be null")
     private String companyIdentity;

    @NotNull(message = "AppId must not be null")
    private String appId;

    public String getUserIdentity() {

        return this.userIdentity;
    }

    public void setUserIdentity(final String userIdentity) {

        this.userIdentity = userIdentity;
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
