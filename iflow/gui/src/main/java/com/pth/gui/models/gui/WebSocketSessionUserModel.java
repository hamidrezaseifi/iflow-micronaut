package com.pth.gui.models.gui;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.websocket.WebSocketSession;

import java.util.UUID;

public class WebSocketSessionUserModel {
    private WebSocketSession socketSession;
    private UUID userId;

    public WebSocketSessionUserModel(WebSocketSession socketSession,
                                     UUID userId) {
        this.socketSession = socketSession;
        this.userId = userId;
    }

    public WebSocketSession getSocketSession() {
        return socketSession;
    }

    public void setSocketSession(WebSocketSession socketSession) {
        this.socketSession = socketSession;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean isClosed(){
        return this.socketSession.isOpen() == false;
    }

    public void sendMessageAsync(Object message){
        socketSession.sendAsync(message);
    }
}
