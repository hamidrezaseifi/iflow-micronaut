package com.pth.gui.controllers;

import com.pth.common.edo.enums.EUserAcces;
import com.pth.gui.models.User;
import com.pth.gui.models.gui.uisession.SessionData;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.authentication.AuthenticationUserDetailsAdapter;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.session.Session;

import java.util.Collection;
import java.util.Optional;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/users")
public class UserController {


    @Secured(SecurityRule.IS_ANONYMOUS)
    @Get("/sessiondata")
    public HttpResponse<SessionData> sessionData(Session session){
        SessionData sessionData = new SessionData();

        //micronaut.AUTHENTICATION
        Optional<Object> oUserDetailsOptional = session.get("micronaut.AUTHENTICATION");
        if(oUserDetailsOptional.isPresent() && oUserDetailsOptional.get() instanceof AuthenticationUserDetailsAdapter){

            AuthenticationUserDetailsAdapter userDetails = (AuthenticationUserDetailsAdapter)oUserDetailsOptional.get();

            if(userDetails.getAttributes().containsKey("session-data")){
                sessionData = (SessionData)userDetails.getAttributes().get("session-data");
            }

        }

        return HttpResponse.ok(sessionData);
    }

    /*private void generateGeneralData(final Map<String, Object> map) throws IFlowMessageConversionFailureException {

        if (this.isSessionValidAndLoggedIn()) {

            Map<String, Object> childsMap = new HashMap<>();


            childsMap = new HashMap<>();
            childsMap.put("menus", this.getMenus());

            final Map<String, Object> dashboardMap = new HashMap<>();
            dashboardMap.put("totalColumns", this.getSessionUserInfo().getDashboarTotalColumns());
            dashboardMap.put("totalRows", this.getSessionUserInfo().getDashboarTotalRows());
            dashboardMap.put("dashboardMenus", this.getSessionUserInfo().getPreparedUserDashboardMenus(this.getMenus()));
            childsMap.put("dashboard", dashboardMap);

            map.put("app", childsMap);

            map.put("isLogged", "true");

        }
        else {
            Map<String, Object> childsMap = new HashMap<>();
            childsMap.put("company", null);
            childsMap.put("departments", new ArrayList<>());
            childsMap.put("users", new ArrayList<>());
            map.put("company", childsMap);

            childsMap = new HashMap<>();
            childsMap.put("worlflowTypes", new ArrayList<>());
            map.put("workflow", childsMap);

            childsMap = new HashMap<>();
            childsMap.put("currentUser", null);
            map.put("user", childsMap);

            childsMap = new HashMap<>();
            childsMap.put("menus", new ArrayList<>());
            map.put("app", childsMap);

        }
    }*/

}
