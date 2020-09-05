package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.User;

public class SessionData {

    boolean isLogged;

    private CompanySessionData company;

    private WorkflowSessionData workflow;

    private UserSessionData user;

    private AppSessionData app;


    public SessionData() {
        isLogged = false;
        company = new CompanySessionData();
        workflow = new WorkflowSessionData();
        user = new UserSessionData();
        app = new AppSessionData();
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public CompanySessionData getCompany() {
        return company;
    }

    public void setCompany(CompanySessionData company) {
        this.company = company;
    }

    public WorkflowSessionData getWorkflow() {
        return workflow;
    }

    public void setWorkflow(WorkflowSessionData workflow) {
        this.workflow = workflow;
    }

    public UserSessionData getUser() {
        return user;
    }

    public void setUser(UserSessionData user) {
        this.user = user;
    }

    public AppSessionData getApp() {
        return app;
    }

    public void setApp(AppSessionData app) {
        this.app = app;
    }
}
