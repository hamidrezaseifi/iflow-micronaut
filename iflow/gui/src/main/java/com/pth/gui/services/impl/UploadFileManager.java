package com.pth.gui.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import com.pth.gui.exception.InvalidUploadedStreamException;
import com.pth.gui.models.UploadededFile;
import com.pth.gui.models.gui.FileSavingData;
import com.pth.gui.services.IUploadFileManager;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.http.multipart.CompletedFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Singleton;

@Singleton
@ConfigurationProperties("iflow.gui.uploadfile")
public class UploadFileManager implements IUploadFileManager {

  protected final Logger log = LoggerFactory.getLogger(UploadFileManager.class);

  private String tempBaseDir;

  private String archiveBaseDir;

  @Override
  public String saveSingleMultipartInTemp(final CompletedFileUpload stream) throws IOException {

    final String ext = FileSavingData.getExtention(stream.getFilename());
    final String tempPath = FileSavingData.generateSavingFileFullPath(this.tempBaseDir, ext);
    final File oFile = this.createFileAndFolders(tempPath);

    try {
      Files.copy(stream.getInputStream(), oFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    catch (IOException | NullPointerException ex){
      throw new InvalidUploadedStreamException(stream.getFilename(), tempPath);
    }

    return tempPath;
  }

  @Override
  public List<FileSavingData> moveFromTempToArchive(final List<UploadededFile> files) throws IOException {

    final List<FileSavingData> list = new ArrayList<>();

    for (final UploadededFile tempUploadedFile : files) {

      final FileSavingData fileSave = FileSavingData.generateFromFilePath(tempUploadedFile.getFilePath());

      final String archiveFilePath = FileSavingData.generateSavingFileFullPath(this.archiveBaseDir, fileSave.getFileExtention());

      this.createFileAndFolders(archiveFilePath);

      final FileSavingData archiveSaveFile = FileSavingData.generateFromFilePath(archiveFilePath);
      archiveSaveFile.setTitle(tempUploadedFile.getFileName());

      final File tempFile = fileSave.getFile();
      tempFile.renameTo(archiveSaveFile.getFile());

      list.add(archiveSaveFile);
    }
    return list;
  }

  private File createFileAndFolders(final String path) {

    final File oFile = new File(path);
    if (!oFile.getParentFile().exists()) {
      oFile.getParentFile().mkdirs();
    }
    return oFile;
  }

  public String getTempBaseDir() {
    return tempBaseDir;
  }

  public void setTempBaseDir(String tempBaseDir) {
    this.tempBaseDir = tempBaseDir;
  }

  public String getArchiveBaseDir() {
    return archiveBaseDir;
  }

  public void setArchiveBaseDir(String archiveBaseDir) {
    this.archiveBaseDir = archiveBaseDir;
  }
}
