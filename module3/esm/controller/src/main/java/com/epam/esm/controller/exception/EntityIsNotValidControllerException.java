package com.epam.esm.controller.exception;

import com.epam.esm.service.exception.ServiceException;

public class EntityIsNotValidControllerException extends ServiceException {

    public static final int ERROR_CODE = 40002;

    public EntityIsNotValidControllerException() {
        super();
    }

    public EntityIsNotValidControllerException(String message) {
        super(message);
    }

    public EntityIsNotValidControllerException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityIsNotValidControllerException(Throwable cause) {
        super(cause);
    }
}
