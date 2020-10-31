package com.pth.gui.models.gui.uisession;

import com.pth.gui.models.User;
import com.pth.gui.models.workflow.WorkflowType;

import java.util.ArrayList;
import java.util.List;

public class UserSessionData {

    private User currentUser;

    public UserSessionData() {
        currentUser = new User();
    }

    public UserSessionData(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
