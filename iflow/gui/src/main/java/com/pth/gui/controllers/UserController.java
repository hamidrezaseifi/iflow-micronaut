package com.pth.gui.controllers;

import com.pth.common.edo.enums.EModule;
import com.pth.common.edo.enums.EUserAcces;
import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.gui.controllers.helper.AuthenticatedController;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.helpers.SessionDataHelper;
import com.pth.gui.models.User;
import com.pth.gui.models.UserDashboardMenu;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowMessage;
import com.pth.gui.services.IUserHandler;
import com.pth.gui.services.IWorkflowMessageHandler;
import io.micronaut.http.*;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationUserDetailsAdapter;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.*;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/users/data")
public class UserController extends AuthenticatedController {

    private final IWorkflowMessageHandler workflowMessageHandler;
    private final IUserHandler userHandler;

    public UserController(IWorkflowMessageHandler workflowMessageHandler, IUserHandler userHandler) {
        this.workflowMessageHandler = workflowMessageHandler;
        this.userHandler = userHandler;
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/sessiondata")
    public HttpResponse<SessionData> sessionData(Session session, HttpRequest<?> request){
        SessionData sessionData = new SessionData();

        Optional<SessionData> sessionDataOptional = SessionDataHelper.getSessionData(session);
        if(sessionDataOptional.isPresent()){
            sessionData = sessionDataOptional.get();
        }

        return HttpResponse.ok(sessionData);
    }


    @Get(value = "/workflowmessages")
    public HttpResponse<List<WorkflowMessage>>
    listWorkflowMessages(@QueryValue(value = "reset" , defaultValue = "") String reset, Session session){

        List<WorkflowMessage> messageList = new ArrayList<>();

        SessionData sessionData = getSessionData(session);

        if ("1".equals(reset)) {
            this.callUserMessageReset(sessionData);
        }

        messageList = this.readUserMessages(sessionData);

        return HttpResponse.ok(messageList);
    }

    
    @Get(value = "/list")
    public HttpResponse<List<User>> listUsers(Session session) {

        SessionData sessionData = getSessionData(session);

        List<User> companyUserList =
                this.userHandler.getCompanyUserList(sessionData.getRefreshToken(), sessionData.getCompanyId());
        return HttpResponse.ok(companyUserList);
    }

    @Post(value = "/create")
    public HttpResponse<User> createUser(@Body final User requestUser, Session session) {

        SessionData sessionData = getSessionData(session);

        requestUser.setCompanyId(sessionData.getCompanyId());
        final Optional<User> userOptional = this.userHandler.createUser(sessionData.getRefreshToken(), requestUser);
        if(userOptional.isPresent()){
            final String generatedPassword =
                    this.userHandler.saveUserPassword(sessionData.getRefreshToken(),
                                                      userOptional.get(),
                                                      "",
                                                      true);
            return HttpResponse.created(userOptional.get());
        }
        return HttpResponse.badRequest();
    }

    @Post(value = "/update")
    public HttpResponse<User> saveUser(@Body final User requestUser, Session session) {

        SessionData sessionData = getSessionData(session);

        requestUser.setCompanyId(sessionData.getCompanyId());

        final Optional<User> userOptional = this.userHandler.saveUser(sessionData.getRefreshToken(), requestUser);
        if(userOptional.isPresent()){
            return HttpResponse.created(userOptional.get());
        }
        return HttpResponse.badRequest();
    }

    @Post(value = "/delete")
    public void deleteUser(@Body final User user, Session session) {

        SessionData sessionData = getSessionData(session);

        this.userHandler.deleteUser(sessionData.getRefreshToken(), user);
    }

    @Post(value = "/resetpassword")
    public HttpResponse<Map<String, Object>> resetUserPassword(@Body final User user, Session session) {

        SessionData sessionData = getSessionData(session);

        final String changedPassword = this.userHandler.saveUserPassword(sessionData.getRefreshToken(), user ,"", true);
        Map<String, Object> map = new HashMap<>();
        map.put("res" , "ok");
        map.put("password" , changedPassword);
        map.put("user" , user);

        return HttpResponse.created(map);
    }

    @Post(value = "/saveuserdashboardmenu")
    public HttpResponse<List<UserDashboardMenu>>
        saveUserDashboardMenus(@Body final List<UserDashboardMenu> userDashboardMenuList,
                               Session session){

        SessionData sessionData = getSessionData(session);

        final List<UserDashboardMenu> resultList =
        this.userHandler.saveUserDashboardMenus(sessionData.getRefreshToken(),
                                                userDashboardMenuList,
                                                sessionData.getCurrentUserId());
        return HttpResponse.created(resultList);
    }

    private List<WorkflowMessage> readUserMessages(SessionData sessionData) {

        UUID companyId = sessionData.getCompany().getCompany().getId();
        UUID userId = sessionData.getUser().getCurrentUser().getId();

        return this.workflowMessageHandler.readUserMessages(companyId, userId, sessionData);
    }

    private void callUserMessageReset(SessionData sessionData){

        UUID companyId = sessionData.getCompany().getCompany().getId();
        UUID userId = sessionData.getUser().getCurrentUser().getId();
        String refreshToken = sessionData.getRefreshToken();

        this.workflowMessageHandler.callUserMessageReset(companyId, userId,false, refreshToken);
    }

    private IWorkflowMessageHandler getWorkflowMessageHandler() {

        return this.workflowMessageHandler;
    }



}
