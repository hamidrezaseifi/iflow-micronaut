package com.pth.gui.authentication;

import com.pth.clients.clients.profile.IProfileClient;
import com.pth.common.authentication.IAuthenticationDetailResolver;
import com.pth.common.edo.TokenValidationRequestEdo;
import com.pth.common.edo.enums.EApplication;
import com.pth.gui.models.gui.uisession.SessionData;
import io.micronaut.security.authentication.*;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class GuiTokenManualAuthentication {

    private final IProfileClient profileClient;

    public GuiTokenManualAuthentication(IProfileClient profileClient) {
        this.profileClient = profileClient;
    }

    public Optional<BearerAccessRefreshToken> authenticate(String token){
        Optional<BearerAccessRefreshToken> bearerAccessRefreshTokenOptional = this.profileClient.validateToken(token);
        return bearerAccessRefreshTokenOptional;
    }

}
