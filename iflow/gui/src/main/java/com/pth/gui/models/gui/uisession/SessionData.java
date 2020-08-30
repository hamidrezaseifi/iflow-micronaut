package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.User;

public class SessionData {

    boolean isLogged;

    private CompanySessionData company;

    private WorkflowSessionData workflow;

    private User currentUser;

    private AppSessionData app;


    public SessionData() {
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

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public AppSessionData getApp() {
        return app;
    }

    public void setApp(AppSessionData app) {
        this.app = app;
    }
}
