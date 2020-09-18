package com.pth.common.edo;

import javax.validation.constraints.NotNull;
import java.util.UUID;


public class UserPasswordResetRequestEdo {

    @NotNull(message = "UserId must not be null")
    private UUID userId;

    @NotNull(message = "Password must not be null")
    private String password;

    @NotNull(message = "AppId must not be null")
    private String appId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPassword() {

        return this.password;
    }

    public void setPassword(final String password) {

        this.password = password;
    }

    public String getAppId() {

        return this.appId;
    }

    public void setAppId(final String appId) {

        this.appId = appId;
    }

}
