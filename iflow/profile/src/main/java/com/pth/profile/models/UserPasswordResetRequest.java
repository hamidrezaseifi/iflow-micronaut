package com.pth.profile.models;

import javax.validation.constraints.NotNull;
import java.util.UUID;


public class UserPasswordResetRequest {

    private UUID userId;

    private String password;

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
