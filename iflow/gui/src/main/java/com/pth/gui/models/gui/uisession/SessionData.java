package com.pth.gui.models.gui.uisession;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pth.gui.models.User;
import com.pth.gui.models.workflow.WorkflowType;

import java.util.UUID;

@JsonIgnoreProperties(value = {})
public class SessionData {

    boolean isLogged;

    private CompanySessionData company;

    private WorkflowSessionData workflow;

    private UserSessionData user;

    private AppSessionData app;

    private String refreshToken;

    private String sessionId = "";


    public SessionData() {
        isLogged = false;
        company = new CompanySessionData();
        workflow = new WorkflowSessionData();
        user = new UserSessionData();
        app = new AppSessionData();
    }

    public boolean getIsLogged() {
        return this.isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public CompanySessionData getCompany() {
        return company;
    }

    public UUID getCompanyId() {
        return company.getCompany().getId();
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

    public UUID getCurrentUserId() {
        return user.getCurrentUser().getId();
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User findUser(UUID id){
        if(id == null){
            return null;
        }

        if(user.getCurrentUser().hasId(id)){
            return  user.getCurrentUser();
        }

        for(User user: company.getUsers()){
            if(user.hasId(id)){
                return user;
            }
        }
        return null;
    }


    public WorkflowType findWorkflowType(UUID id){
        if(id == null){
            return null;
        }

        for(WorkflowType workflowType: workflow.getWorkflowTypes()){
            if(workflowType.hasId(id)){
                return workflowType;
            }
        }
        return null;
    }
}
