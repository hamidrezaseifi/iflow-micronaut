package com.pth.gui.controllers;

import com.pth.gui.models.gui.GuiSocketMessage;
import com.pth.gui.services.ISocketUserMessageManager;
import io.micronaut.http.MediaType;
import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import org.reactivestreams.Publisher;

import java.util.UUID;

@ServerWebSocket("/websocket/workflowmessages/{userId}")
public class WorkflowMessageSocketController {

    private final WebSocketBroadcaster broadcaster;
    private final ISocketUserMessageManager socketUserManager;

    public WorkflowMessageSocketController(WebSocketBroadcaster broadcaster, ISocketUserMessageManager socketUserManager) {
        this.broadcaster = broadcaster;
        this.socketUserManager = socketUserManager;
    }

    @OnOpen
    public Publisher<GuiSocketMessage> onOpen(UUID userId, WebSocketSession session) {
        socketUserManager.openSocket(session, userId);

        GuiSocketMessage generatedMessage = GuiSocketMessage.generate("logged-in");
        generatedMessage.setUserLoggedIn(userId.toString());

        return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
    }

    @OnMessage
    public Publisher<GuiSocketMessage> onMessage(
            UUID userId,
            String message,
            WebSocketSession session) {

        socketUserManager.setSocket(session, userId);

        GuiSocketMessage generatedMessage = GuiSocketMessage.generate("logged-in");
        generatedMessage.setUserLoggedIn(userId.toString());

        return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
    }

    @OnClose
    public Publisher<GuiSocketMessage> onClose(
            UUID userId,
            WebSocketSession session) {

        socketUserManager.closeSocket(session, userId);

        GuiSocketMessage generatedMessage = GuiSocketMessage.generate("logged-out");
        generatedMessage.setUserLoggedIn(userId.toString());

        return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
    }
}
