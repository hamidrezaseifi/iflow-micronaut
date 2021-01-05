package com.pth.gui.controllers;

import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.authentication.GuiTokenManualAuthentication;
import com.pth.gui.helpers.ocr.IOcrHelper;
import com.pth.gui.models.gui.GuiSocketMessage;
import com.pth.gui.models.gui.ocr.OcrResults;
import com.pth.gui.services.ICompanyHandler;
import io.micronaut.http.MediaType;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.websocket.WebSocketBroadcaster;
import io.micronaut.websocket.WebSocketSession;
import io.micronaut.websocket.annotation.OnClose;
import io.micronaut.websocket.annotation.OnMessage;
import io.micronaut.websocket.annotation.OnOpen;
import io.micronaut.websocket.annotation.ServerWebSocket;
import org.reactivestreams.Publisher;

import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

@ServerWebSocket("/websocket/workflowocr/{companyId}/{userId}")
public class WorkflowOcrSocketController {

    private final WebSocketBroadcaster broadcaster;
    private final GuiTokenManualAuthentication tokenAuthentication;
    private final IOcrHelper ocrHelper;
    private final ICompanyHandler companyHandler;

    public WorkflowOcrSocketController(final WebSocketBroadcaster broadcaster,
                                       final GuiTokenManualAuthentication tokenAuthentication,
                                       final IOcrHelper ocrHelper,
                                       final ICompanyHandler companyHandler) {
        this.broadcaster = broadcaster;
        this.tokenAuthentication = tokenAuthentication;
        this.ocrHelper = ocrHelper;
        this.companyHandler = companyHandler;
    }

    @OnOpen
    public Publisher<GuiSocketMessage> onOpen(UUID companyId, UUID userId, WebSocketSession session) {

        GuiSocketMessage generatedMessage = GuiSocketMessage.generate("socket-opened");
        generatedMessage.setUserLoggedIn(userId.toString());

        return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
    }

    @OnMessage
    public Publisher<GuiSocketMessage> onMessage(
            UUID companyId,
            UUID userId,
            final GuiSocketMessage message,
            WebSocketSession session) {

        final GuiSocketMessage generatedMessage = message.clone();
        generatedMessage.setStatus("processing");

        if (message.hasToken() == false){
            generatedMessage.setStatus("token-notfound");
            return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
        }

        String token = message.getToken();
        Optional<BearerAccessRefreshToken> authenticationOptional = this.tokenAuthentication.authenticate(token);

        if (authenticationOptional.isPresent() == false){
            generatedMessage.setStatus("not-authenticated");
            return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
        }


        if(message.hasFileHash() && message.hasHocrFileHash()) {
            final String filePath = message.getFileNotHash();
            final String hocrPath = message.getHocrFileNotHash();
            final String selectedPreset = message.hasSelectedOcrPreset()? message.getSelectedOcrPreset() : null;
            final EWorkflowType workflowType = message.getWorkflowType();

            try {
                final String hocrResult = ocrHelper.doOcr(filePath);
                final OcrResults ocrResults = ocrHelper.generateResultFromContent(hocrResult);

                final BufferedWriter writer = new BufferedWriter(new FileWriter(hocrPath));
                writer.write(hocrResult);
                writer.close();

                generatedMessage.setWords(new HashMap<>());
                generatedMessage.setPageCount(ocrResults.getPages().size());

                generatedMessage.setImageWidth(300);
                generatedMessage.setImageHeight(500);
                if (generatedMessage.getIsFileImage()) {
                    final BufferedImage bimg = ImageIO.read(new File(filePath));
                    generatedMessage.setImageWidth(bimg.getWidth());
                    generatedMessage.setImageHeight(bimg.getHeight());
                }

                if (generatedMessage.getIsFilePdf() && ocrResults.getPages().size() > 0) {
                    generatedMessage.setImageWidth(ocrResults.getPages().get(0).getBox().getWidth());
                    generatedMessage.setImageHeight(ocrResults.getPages().get(0).getBox().getHeight());
                }

                generatedMessage.setStatus("done");
            } catch (final IOException e) {

                generatedMessage.setStatus("error");
                generatedMessage.setErrorMessage(e.getLocalizedMessage());
                generatedMessage.setErrorDetail(e.getStackTrace());
            } catch (final TesseractException e) {

                generatedMessage.setStatus("error");
                generatedMessage.setErrorMessage(e.getLocalizedMessage());
                generatedMessage.setErrorDetail(e.getStackTrace());
            } catch (final NoClassDefFoundError e) {

                generatedMessage.setStatus("error");
                generatedMessage.setErrorMessage(e.getLocalizedMessage());
                generatedMessage.setErrorDetail(e.getStackTrace());
                System.out.println(e);
            } catch (final Exception e) {

                generatedMessage.setStatus("error");
                generatedMessage.setErrorMessage(e.getLocalizedMessage());
                generatedMessage.setErrorDetail(e.getStackTrace());
            }

        }
        else{
            generatedMessage.setStatus("not-authenticated");
        }

        return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
    }

    @OnClose
    public Publisher<GuiSocketMessage> onClose(
            UUID companyId,
            UUID userId,
            WebSocketSession session) {

        GuiSocketMessage generatedMessage = GuiSocketMessage.generate("socket-closed");
        generatedMessage.setUserLoggedIn(userId.toString());

        return broadcaster.broadcast(generatedMessage, MediaType.APPLICATION_JSON_TYPE);
    }

}
