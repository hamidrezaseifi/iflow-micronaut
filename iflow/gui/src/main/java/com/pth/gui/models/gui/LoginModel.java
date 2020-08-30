package com.pth.gui.models.gui;

public class LoginModel {
    private String username;
    private String password;
    private String companyId;

    public LoginModel() {
        this.username = "";
        this.password = "";
        this.companyId = "";
    }

    public LoginModel(String username,
                      String password,
                      String companyId) {
        this.username = username;
        this.password = password;
        this.companyId = companyId;
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
}
