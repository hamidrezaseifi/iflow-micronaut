package com.pth.common.exceptions;

public class ValueOutOfRangeException extends RuntimeException {

    public ValueOutOfRangeException(String message) {
        super(message);
    }

    public ValueOutOfRangeException(String message,
                                    Throwable cause) {
        super(message, cause);
    }
}
