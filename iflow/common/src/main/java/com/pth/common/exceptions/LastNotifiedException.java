package com.pth.common.exceptions;

public class LastNotifiedException extends RuntimeException {

    public LastNotifiedException(String message) {
        super(message);
    }

    public LastNotifiedException(String message,
                               Throwable cause) {
        super(message, cause);
    }
}
