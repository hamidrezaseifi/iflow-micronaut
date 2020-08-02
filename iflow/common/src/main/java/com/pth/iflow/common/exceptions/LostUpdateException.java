package com.pth.iflow.common.exceptions;

public class LostUpdateException extends RuntimeException {

    public LostUpdateException(String message) {
        super(message);
    }

    public LostUpdateException(String message,
                               Throwable cause) {
        super(message, cause);
    }
}
