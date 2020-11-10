package com.pth.gui.authentication;

import com.pth.gui.models.gui.uisession.SessionData;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationUserDetailsAdapter;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.filters.SecurityFilter;
import io.micronaut.security.session.SecuritySessionConfiguration;
import io.micronaut.security.session.SessionLoginHandler;
import io.micronaut.security.session.SessionLogoutHandler;
import io.micronaut.security.token.config.TokenConfiguration;
import io.micronaut.session.Session;
import io.micronaut.session.SessionStore;
import io.micronaut.session.http.CookieHttpSessionIdGenerator;
import io.micronaut.session.http.HttpSessionConfiguration;
import io.micronaut.session.http.SessionForRequest;

import javax.annotation.Nullable;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Replaces(SessionLogoutHandler.class)
public class GuiLogoutHandler extends SessionLogoutHandler {

    @Nullable
    private final String rolesKeyName;
    private final CookieHttpSessionIdGenerator cookieHttpSessionIdGenerator;
    private final HttpSessionConfiguration configuration;


    public GuiLogoutHandler(SecuritySessionConfiguration securitySessionConfiguration,
                            SessionStore<Session> sessionStore,
                            TokenConfiguration tokenConfiguration,
                            HttpSessionConfiguration configuration) {
        super(securitySessionConfiguration);
        this.rolesKeyName = tokenConfiguration.getRolesName();
        this.configuration = configuration;
        this.cookieHttpSessionIdGenerator = new CookieHttpSessionIdGenerator(configuration);
    }

    @Override
    public HttpResponse logout(HttpRequest<?> request) {
        super.logout(request);

        Map<String, Object> attrs =  new HashMap<>();
        attrs.put("res", "ok");
        attrs.put("session-cookie-name", configuration.getCookieName());


        return HttpResponse.ok(attrs);
    }

}
