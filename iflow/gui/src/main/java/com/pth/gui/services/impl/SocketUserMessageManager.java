package com.pth.gui.services.impl;

import com.pth.gui.models.gui.WebSocketSessionUserModel;
import com.pth.gui.services.ISocketUserMessageManager;
import io.micronaut.scheduling.annotation.Scheduled;
import io.micronaut.websocket.WebSocketSession;

import javax.inject.Singleton;
import java.util.*;

@Singleton
public class SocketUserMessageManager implements ISocketUserMessageManager {

    private Map<UUID, WebSocketSessionUserModel> sessionUserMap = new HashMap<>();

    @Override
    public void openSocket(WebSocketSession session, UUID userId) {

        sessionUserMap.put(userId, new WebSocketSessionUserModel(session, userId));
    }

    @Override
    public void setSocket(WebSocketSession session, UUID userId) {

        sessionUserMap.put(userId, new WebSocketSessionUserModel(session, userId));
    }

    @Override
    public void closeSocket(WebSocketSession session, UUID userId) {
        sessionUserMap.remove(userId);
    }

    @Override
    public void sendMessage(UUID userId, Object message) {
        if(sessionUserMap.containsKey(userId)){
            WebSocketSessionUserModel sessionUserModel = sessionUserMap.get(userId);
            if(sessionUserModel.isClosed()){
                sessionUserMap.remove(sessionUserModel.getUserId());
            }
            else{
                sessionUserModel.sendMessageAsync(message);
            }
        }
    }

    @Scheduled(fixedDelay = "45s", initialDelay = "5s")
    public void clearList(){

        List<UUID> closedSessions = new ArrayList<>();
        for(UUID userId: sessionUserMap.keySet()){
            if(sessionUserMap.get(userId).isClosed()){
                closedSessions.add(userId);
                sessionUserMap.remove(userId);
            }
        }

        for(UUID userId: closedSessions){
            sessionUserMap.remove(userId);
        }
    }
}
