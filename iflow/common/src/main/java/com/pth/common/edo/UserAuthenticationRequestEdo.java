package com.pth.common.edo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;


@Introspected
@JsonInclude(JsonInclude.Include.ALWAYS)
public class UserAuthenticationRequestEdo {

    @NotNull(message = "username must not be null")
    private String username;

    @NotNull(message = "Password must not be null")
    private String password;

    @NotNull(message = "CompanyIdentity must not be null")
     private String companyIdentity;

    @NotNull(message = "AppId must not be null")
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
