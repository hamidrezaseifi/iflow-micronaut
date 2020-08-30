package com.pth.gui.models;

import com.pth.gui.models.gui.GuiSocketMessage;
import org.apache.commons.lang3.StringUtils;

public class UploadededFile {

  private String fileName;
  // private String filePath;
  private String filePathHashed;

  public UploadededFile() {

  }

  public String getFileName() {

    return this.fileName;
  }

  public void setFileName(final String fileName) {

    this.fileName = fileName;
  }

  public String getFilePath() {

    return GuiSocketMessage.decodeHashPath(this.filePathHashed);
  }

  public String getFilePathHashed() {

    return this.filePathHashed;
  }

  public void setFilePathHashed(final String filePathHashed) {

    this.filePathHashed = filePathHashed;
  }

  public boolean hasFilePathHashed() {

    return StringUtils.isNotEmpty(this.filePathHashed);
  }

}
