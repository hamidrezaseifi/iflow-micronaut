package com.pth.gui.controllers.helper;

import com.pth.common.edo.enums.EModule;
import com.pth.common.exceptions.EIFlowErrorType;
import com.pth.gui.exception.GuiCustomizedException;
import com.pth.gui.helpers.SessionDataHelper;
import com.pth.gui.models.gui.uisession.SessionData;
import io.micronaut.session.Session;

import java.util.Optional;

public class AuthenticatedController {

    protected SessionData getSessionData(Session session){
        Optional<SessionData> sessionDataOptional = SessionDataHelper.getSessionData(session);
        if(sessionDataOptional.isPresent()){
            return sessionDataOptional.get();
        }

        throw new GuiCustomizedException("Invalid Session!",
                                         EModule.GUI.getModuleName(),
                                         EIFlowErrorType.GUI_INVALID_SESSION);
    }

    protected void setSessionData(Session session, SessionData sessionData){
        SessionDataHelper.setSessionData(session, sessionData);
    }
}
