package com.pth.gui.controllers;

import com.pth.common.edo.enums.EInvoiceWorkflowTypeItems;
import com.pth.common.edo.enums.EOcrType;
import com.pth.common.edo.enums.EWorkflowType;
import com.pth.gui.authentication.GuiTokenManualAuthentication;
import com.pth.gui.helpers.ocr.IOcrHelper;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPreset;
import com.pth.gui.models.CompanyWorkflowtypeItemOcrSettingPresetItem;
import com.pth.gui.models.gui.GuiSocketMessage;
import com.pth.gui.models.gui.ocr.OcrResultValueType;
import com.pth.gui.models.gui.ocr.OcrResultWord;
import com.pth.gui.models.gui.ocr.OcrResults;
import com.pth.gui.services.ICompanyHandler;
import com.pth.gui.services.ISocketUserMessageManager;
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
                final OcrResults ocrResults = ocrHelper.generateResult(hocrResult);

                final BufferedWriter writer = new BufferedWriter(new FileWriter(hocrPath));
                writer.write(hocrResult);
                writer.close();

                final Map<String, Set<OcrResultWord>> words =
                        this.retrieveInvoiceDetailWords(ocrResults, selectedPreset, companyId, workflowType, token);

                generatedMessage.setWords(words);
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

    private Map<String, Set<OcrResultWord>> retrieveInvoiceDetailWords(final OcrResults ocrResults,
                                                                       String selectedPreset,
                                                                       UUID companyId,
                                                                       EWorkflowType workflowType,
                                                                       String token){

        if(selectedPreset == null){
            List<CompanyWorkflowtypeItemOcrSettingPreset> presetList =
                    this.companyHandler.readCompanyWorkflowtypeItemOcrSettings(companyId, token);

            if(presetList.isEmpty()){
                return new HashMap<>();
            }
            selectedPreset = presetList.get(0).getPresetName();
        }

        final Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem> presetItems =
                this.companyHandler.readPresetAllItems(selectedPreset, companyId, workflowType, token);

        final Map<String, Set<OcrResultWord>> words = new HashMap<>();

        Set<OcrResultWord> results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.PAYMENT_AMOUNT.getIdentity(), OcrResultValueType.FLOAT);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.PAYMENT_AMOUNT.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.INVOCIE_SENDER.getIdentity(), OcrResultValueType.TEXT);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.INVOCIE_SENDER.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.INVOCIE_NUMBER.getIdentity(), OcrResultValueType.TEXT);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.INVOCIE_NUMBER.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.INVOCIE_DATE.getIdentity(), OcrResultValueType.DATE);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.INVOCIE_DATE.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.PARTNER_CODE.getIdentity(), OcrResultValueType.TEXT);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.PARTNER_CODE.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.VENDOR_NUMBER.getIdentity(), OcrResultValueType.TEXT);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.VENDOR_NUMBER.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.VENDOR_NAME.getIdentity(), OcrResultValueType.TEXT);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.VENDOR_NAME.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.IS_DIRECT_DEBIT_PERMISSION.getIdentity(),
                                    OcrResultValueType.TEXT);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.IS_DIRECT_DEBIT_PERMISSION.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.INVOICE_TYPE.getIdentity(), OcrResultValueType.TEXT);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.INVOICE_TYPE.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.DISCOUNT_ENTERDATE.getIdentity(), OcrResultValueType.DATE);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.DISCOUNT_ENTERDATE.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.DISCOUNT_DEADLINE.getIdentity(), OcrResultValueType.TEXT);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.DISCOUNT_DEADLINE.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.DISCOUNT_RATE.getIdentity(), OcrResultValueType.FLOAT);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.DISCOUNT_RATE.getIdentity(), results);
        }

        results = this
                .extractSearchItems(ocrResults, presetItems, EInvoiceWorkflowTypeItems.DISCOUNT_DATE.getIdentity(), OcrResultValueType.DATE);
        if (results != null) {
            words.put(EInvoiceWorkflowTypeItems.DISCOUNT_DATE.getIdentity(), results);
        }

        final Set<OcrResultWord> amountWorldList = ocrResults.findWord("betrag", false, false, OcrResultValueType.FLOAT);
        final Set<OcrResultWord> senderWorldList = ocrResults.findWord("sender", false, false, OcrResultValueType.TEXT);
        final Set<OcrResultWord> numberWorldList = ocrResults
                .findWords(new String[] { "R.-Nr.", "Rg-Nr", "R. Nummer", "Rg-Nummer", "Rechnungsnummer", "nr." }, false,
                           false,
                           OcrResultValueType.TEXT);
        final Set<OcrResultWord> dateWorldList = ocrResults.findWord("Datum", false, false, OcrResultValueType.DATE);
        final Set<OcrResultWord> testList = ocrResults.findWords(new String[] { "ident", "heinstadt" }, false, false, OcrResultValueType.TEXT);

        /*
         * words.put("invoice-paymentamount", amountWorldList); words.put("invoice-sender", senderWorldList); words.put("invoice-invoicenumber",
         * numberWorldList); words.put("invoice-invoicedate", dateWorldList); words.put("test", testList);
         */
        return words;
    }

    private Set<OcrResultWord> extractSearchItems(final OcrResults ocrResults,
                                                  final Map<String, CompanyWorkflowtypeItemOcrSettingPresetItem> presetItems,
                                                  final String propertyName, final OcrResultValueType valueType) {

        Set<OcrResultWord> results = null;
        if (presetItems.containsKey(propertyName)) {
            final CompanyWorkflowtypeItemOcrSettingPresetItem property = presetItems.get(propertyName);

            if (property.getOcrTypeEnum() == EOcrType.SEARCH_WORD) {

                final String[] searchWords = property.getValue().trim().split(";");
                results = ocrResults.findWords(searchWords, false, false, valueType);
                results = results.isEmpty() ? null : results;

            }

        }

        return results;
    }

}
