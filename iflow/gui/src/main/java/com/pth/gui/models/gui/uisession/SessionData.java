package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.User;

public class SessionData {

    boolean isLogged;

    private CompanySessionData companySessionData;

    private WorkflowSessionData workflowSessionData;

    private User currentUser;

    private AppSessionData appSessionData;


    public SessionData() {
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public CompanySessionData getCompanySessionData() {
        return companySessionData;
    }

    public void setCompanySessionData(CompanySessionData companySessionData) {
        this.companySessionData = companySessionData;
    }

    public WorkflowSessionData getWorkflowSessionData() {
        return workflowSessionData;
    }

    public void setWorkflowSessionData(WorkflowSessionData workflowSessionData) {
        this.workflowSessionData = workflowSessionData;
    }

    public AppSessionData getAppSessionData() {
        return appSessionData;
    }

    public void setAppSessionData(AppSessionData appSessionData) {
        this.appSessionData = appSessionData;
    }
}
