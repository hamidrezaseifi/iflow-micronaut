package com.pth.gui.authentication;

import com.pth.gui.models.gui.uisession.SessionData;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationUserDetailsAdapter;
import io.micronaut.security.authentication.UserDetails;
import io.micronaut.security.filters.SecurityFilter;
import io.micronaut.security.handlers.LoginHandler;
import io.micronaut.security.session.SecuritySessionConfiguration;
import io.micronaut.security.session.SessionLoginHandler;
import io.micronaut.security.token.config.TokenConfiguration;
import io.micronaut.session.Session;
import io.micronaut.session.SessionStore;
import io.micronaut.session.http.CookieHttpSessionIdGenerator;
import io.micronaut.session.http.HttpSessionConfiguration;
import io.micronaut.session.http.SessionForRequest;
import io.netty.handler.codec.http.HttpHeaderNames;

import javax.annotation.Nullable;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
@Replaces(SessionLoginHandler.class)
public class GuiLoginHandler extends SessionLoginHandler {

    @Nullable
    private final String rolesKeyName;
    private final CookieHttpSessionIdGenerator cookieHttpSessionIdGenerator;
    private final HttpSessionConfiguration configuration;


    public GuiLoginHandler(SecuritySessionConfiguration securitySessionConfiguration,
                           SessionStore<Session> sessionStore,
                           TokenConfiguration tokenConfiguration,
                           HttpSessionConfiguration configuration) {
        super(securitySessionConfiguration, sessionStore, tokenConfiguration);
        this.rolesKeyName = tokenConfiguration.getRolesName();
        this.configuration = configuration;
        this.cookieHttpSessionIdGenerator = new CookieHttpSessionIdGenerator(configuration);
    }

    @Override
    public HttpResponse loginSuccess(UserDetails userDetails,
                                     HttpRequest<?> request) {
        Session session = (Session) SessionForRequest.find(request).orElseGet(() -> {
            return SessionForRequest.create(this.sessionStore, request);
        });
        session.put(SecurityFilter.AUTHENTICATION, new AuthenticationUserDetailsAdapter(userDetails, this.rolesKeyName));

        Map<String, Object> attrs =  userDetails.getAttributes("roles", "uname");
        attrs.put("sessionId", session.getId());
        attrs.put("session", session);
        SessionData sessionData = (SessionData)attrs.get(GuiAuthenticationProvider.SESSION_DATA_KEY);
        sessionData.setSessionId(session.getId());
        attrs.put(GuiAuthenticationProvider.SESSION_DATA_KEY, sessionData);

        String cookieValue = cookieHttpSessionIdGenerator.cookieValueFromSession(session);
        attrs.put("session-cookie-data", cookieValue);
        attrs.put("session-cookie-name", configuration.getCookieName());

        //io.netty.handler.codec.http.cookie.ClientCookieEncoder
        //HttpResponse resp = HttpResponse.ok(attrs);

        return HttpResponse.ok(attrs);
    }

    @Override
    public HttpResponse loginFailed(AuthenticationFailed authenticationFailed) {
        return HttpResponse.unauthorized();
    }
}
