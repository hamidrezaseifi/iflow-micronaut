package com.pth.gui.helpers;

import com.pth.gui.authentication.GuiAuthenticationProvider;
import com.pth.gui.models.gui.uisession.SessionData;
import io.micronaut.security.authentication.AuthenticationUserDetailsAdapter;
import io.micronaut.session.Session;

import java.util.Optional;

public class SessionDataHelper {

    public static final String MICRONAUT_AUTHENTICATION_SESSION_KEY = "micronaut.AUTHENTICATION";

    public static boolean isLoggedIn(Session session){
        Optional<Object> oUserDetailsOptional = session.get(MICRONAUT_AUTHENTICATION_SESSION_KEY);
        return oUserDetailsOptional.isPresent() &&
               oUserDetailsOptional.get() instanceof AuthenticationUserDetailsAdapter;
    }

    public static Optional<SessionData> getSessionData(Session session){
        Optional<SessionData> sessionDataOptional = Optional.empty();
        if(isLoggedIn(session)){
            Optional<Object> oUserDetailsOptional = session.get(MICRONAUT_AUTHENTICATION_SESSION_KEY);

            AuthenticationUserDetailsAdapter userDetails = (AuthenticationUserDetailsAdapter)oUserDetailsOptional.get();

            if(userDetails.getAttributes().containsKey(GuiAuthenticationProvider.SESSION_DATA_KEY)){
                SessionData sessionData =
                        (SessionData)userDetails.getAttributes().get(GuiAuthenticationProvider.SESSION_DATA_KEY);
                sessionDataOptional = Optional.of(sessionData);
            }

        }
        return sessionDataOptional;
    }

    public static void setSessionData(Session session, SessionData sessionData){
        session.put(MICRONAUT_AUTHENTICATION_SESSION_KEY, sessionData);
    }

}
