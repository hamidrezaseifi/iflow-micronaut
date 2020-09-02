package com.pth.gui.models.gui;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Introspected;

@Introspected
public class LoginModel {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("companyId")
    private String companyId;

    private boolean isFailed;

    public LoginModel() {
        this.username = "";
        this.password = "";
        this.companyId = "";
        isFailed = false;
    }

    public LoginModel(String username,
                      String password,
                      String companyId,
                      boolean isFailed) {
        this.username = username;
        this.password = password;
        this.companyId = companyId;
        this.isFailed = isFailed;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public void setFailed(boolean failed) {
        isFailed = failed;
    }
}
