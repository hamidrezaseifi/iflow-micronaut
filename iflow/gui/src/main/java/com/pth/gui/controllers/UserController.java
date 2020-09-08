package com.pth.gui.controllers;

import com.pth.common.edo.enums.EUserAcces;
import com.pth.gui.helpers.SessionDataHelper;
import com.pth.gui.models.User;
import com.pth.gui.models.gui.uisession.SessionData;
import com.pth.gui.models.workflow.WorkflowMessage;
import com.pth.gui.services.IWorkflowMessageHandler;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationUserDetailsAdapter;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;

import java.util.*;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/users")
public class UserController {

    private final IWorkflowMessageHandler workflowMessageHandler;

    public UserController(IWorkflowMessageHandler workflowMessageHandler) {
        this.workflowMessageHandler = workflowMessageHandler;
    }

    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/sessiondata")
    public HttpResponse<SessionData> sessionData(Session session){
        SessionData sessionData = new SessionData();

        Optional<SessionData> sessionDataOptional = SessionDataHelper.getSessionData(session);
        if(sessionDataOptional.isPresent()){
            sessionData = sessionDataOptional.get();
        }

        return HttpResponse.ok(sessionData);
    }


    @Post(value = "/workflowmessages{?reset}" , produces = MediaType.APPLICATION_JSON)
    public HttpResponse<List<WorkflowMessage>> listWorkflowMessages(Optional<String> reset, Session session){

        List<WorkflowMessage> messageList = new ArrayList<>();
        Optional<SessionData> sessionDataOptional = SessionDataHelper.getSessionData(session);
        if (sessionDataOptional.isPresent()) {

            SessionData sessionData = sessionDataOptional.get();

            if ("1".equals(reset)) {
                this.callUserMessageReset(sessionData);
            }

            messageList = this.readUserMessages(sessionData);

        }
        return HttpResponse.ok(messageList);
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
