package com.pth.gui.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.websocket.WebSocketSession;

import java.util.UUID;

public interface ISocketUserMessageManager {

    void openSocket(WebSocketSession session, UUID userId);

    void setSocket(WebSocketSession session, UUID userId);

    void closeSocket(WebSocketSession session, UUID userId);

    void sendMessage(UUID userId, Object message);

}
