package com.pth.gui.models.gui;

import com.pth.common.exceptions.IFlowCustomizedException;
import com.pth.gui.enums.ESocketCommands;
import com.pth.gui.models.gui.ocr.OcrResultWord;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GuiSocketMessage extends HashMap<String, Object> {

  private static final long serialVersionUID = 3425395554514027407L;

  public GuiSocketMessage() {

  }

  public GuiSocketMessage(final int initialCapacity) {

    super(initialCapacity);
  }

  public GuiSocketMessage(final Map<? extends String, ? extends String> m) {

    super(m);

  }

  public GuiSocketMessage(final int initialCapacity, final float loadFactor) {

    super(initialCapacity,loadFactor);
  }

  public boolean hasFileHash() {

    return this.containsKey(ESocketCommands.FILE_HASH.getValue());
  }

  public String getFileNotHash() {

    final String encodedString = this.get(ESocketCommands.FILE_HASH.getValue()).toString();

    final String decodedString = GuiSocketMessage.decodeHashPath(encodedString);

    return decodedString;

  }

  public void setFileNotHash(final String filepath) {

    final String encodedString = GuiSocketMessage.encodeHashPath(filepath);

    this.put(ESocketCommands.FILE_HASH.getValue(), encodedString);
  }

  public boolean hasHocrFileHash() {

    return this.containsKey(ESocketCommands.HOCRFILE_HASH.getValue());
  }

  public String getHocrFileNotHash() {

    final String encodedString = this.get(ESocketCommands.HOCRFILE_HASH.getValue()).toString();

    final String decodedString = GuiSocketMessage.decodeHashPath(encodedString);

    return decodedString;

  }

  public void setHocrFileNotHash(final String filepath) {

    final String encodedString = GuiSocketMessage.encodeHashPath(filepath);

    this.put(ESocketCommands.HOCRFILE_HASH.getValue(), encodedString);
  }

  public boolean hasMessageReload() {

    return this.containsKey(ESocketCommands.MESSAGE_RELOAD.getValue());
  }

  public String getStatus() {

    return this.get(ESocketCommands.STATUS.getValue()).toString();
  }

  public void setStatus(final String status) {

    this.put(ESocketCommands.STATUS.getValue(), status);
  }

  public String getFileName() {

    return this.get(ESocketCommands.FILE_NAME.getValue()).toString();
  }

  public void setFileName(final String status) {

    this.put(ESocketCommands.FILE_NAME.getValue(), status);
  }

  public void setUserLoggedIn(final String msg) {

    this.put(ESocketCommands.USER_LOGGED_IN.getValue(), msg);
  }

  public String getCommand() {

    return this.get(ESocketCommands.COMMAND.getValue()).toString();
  }

  public void setCommand(final String command) {

    this.put(ESocketCommands.COMMAND.getValue(), command);
  }

  public String getSelectedOcrPreset() {

    return this.get(ESocketCommands.SELECTED_OCR_PRESET.getValue()).toString();
  }

  public void setSelectedOcrPreset(final String value) {

    this.put(ESocketCommands.SELECTED_OCR_PRESET.getValue(), value);
  }

  public void setWords(final Map<String, Set<OcrResultWord>> words) {

    this.put(ESocketCommands.WORDS.getValue(), words);
  }

  public String getErrorMessage() {

    return this.get(ESocketCommands.ERROR_MESSAGE.getValue()).toString();
  }

  public void setErrorMessage(final String error) {

    this.put(ESocketCommands.ERROR_MESSAGE.getValue(), error);
  }

  public String getErrorDetail() {

    return this.get(ESocketCommands.ERROR_DETAIL.getValue()).toString();
  }

  public void setErrorDetail(final String error) {

    this.put(ESocketCommands.ERROR_DETAIL.getValue(), error);
  }

  public void setErrorDetail(final StackTraceElement[] stackTraces) {

    this.put(ESocketCommands.ERROR_DETAIL.getValue(), IFlowCustomizedException.stackListToString(stackTraces));
  }

  public boolean getIsFileImage() {

    return "true".equals(this.get(ESocketCommands.IS_FILE_IMAGE.getValue()).toString());
  }

  public void setIsFileImage(final boolean value) {

    this.put(ESocketCommands.IS_FILE_IMAGE.getValue(), value);
  }

  public boolean getIsFilePdf() {

    return "true".equals(this.get(ESocketCommands.IS_FILE_PDF.getValue()).toString());
  }

  public void setIsFilePdf(final boolean value) {

    this.put(ESocketCommands.IS_FILE_PDF.getValue(), value);
  }

  public int getPageCount() {

    return Integer.parseInt(this.get(ESocketCommands.PAGE_COUNT.getValue()).toString());
  }

  public void setPageCount(final int value) {

    this.put(ESocketCommands.PAGE_COUNT.getValue(), value);
  }

  public int getImageWidth() {

    return Integer.parseInt(this.get(ESocketCommands.IMAGE_WIDTH.getValue()).toString());
  }

  public void setImageWidth(final int value) {

    this.put(ESocketCommands.IMAGE_WIDTH.getValue(), value);
  }

  public int getImageHeight() {

    return Integer.parseInt(this.get(ESocketCommands.IMAGE_HEIGHT.getValue()).toString());
  }

  public void setImageHeight(final int value) {

    this.put(ESocketCommands.IMAGE_HEIGHT.getValue(), value);
  }

  public String getToken() {
    return this.get(ESocketCommands.TOKEN.getValue()).toString();
  }

  public boolean hasToken() {

    return this.containsKey(ESocketCommands.TOKEN.getValue());
  }

  public void setToken(String token) {
    this.put(ESocketCommands.TOKEN.getValue(), token);
  }

  public static GuiSocketMessage generate(final String status) {

    final GuiSocketMessage message = new GuiSocketMessage();
    message.put(ESocketCommands.STATUS.getValue(), status);
    return message;
  }

  public static String decodeHashPath(final String encodedString) {

    final String decodedString = new String(Base64.getDecoder().decode(encodedString));

    return decodedString;
  }

  public static String encodeHashPath(final String filepath) {

    final String encodedString = Base64.getEncoder().encodeToString(filepath.getBytes());

    return encodedString;
  }

  @Override
  public GuiSocketMessage clone() {

    final GuiSocketMessage message = new GuiSocketMessage();
    for (final String key : this.keySet()) {
      message.put(key, this.get(key));
    }

    return message;

  }


}
