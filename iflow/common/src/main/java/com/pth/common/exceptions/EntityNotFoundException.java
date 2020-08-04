package com.pth.common.exceptions;

import java.io.IOException;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message,
                                   IOException cause) {
        super(message, cause);
    }
}
