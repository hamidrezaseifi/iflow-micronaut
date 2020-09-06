package com.pth.gui.exception;

import java.util.UUID;

public class InvalidUploadedStreamException extends RuntimeException {

    private final String sourceName;
    private final String targetPath;

    public InvalidUploadedStreamException(String sourceName, String targetPath) {
        this.sourceName = sourceName;
        this.targetPath = targetPath;
    }

    @Override
    public String getMessage(){
        return String.format("Invalid uploaded file stream with name %s to %s"
                , sourceName
                , targetPath);
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getTargetPath() {
        return targetPath;
    }
}
