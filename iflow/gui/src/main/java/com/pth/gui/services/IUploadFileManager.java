package com.pth.gui.services;

import com.pth.gui.models.UploadededFile;
import com.pth.gui.models.gui.FileSavingData;
import io.micronaut.http.multipart.CompletedFileUpload;

import java.io.IOException;
import java.util.List;


public interface IUploadFileManager {

  String saveSingleMultipartInTemp(final CompletedFileUpload stream) throws IOException;

  List<FileSavingData> moveFromTempToArchive(List<UploadededFile> files) throws IOException;

}
