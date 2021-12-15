package com.epam.esm.service.exception;

public class EntityIsNotValidServiceException extends ServiceException {

    public static final int ERROR_CODE = 40001;

    public EntityIsNotValidServiceException() {
        super();
    }

    public EntityIsNotValidServiceException(String message) {
        super(message);
    }

    public EntityIsNotValidServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityIsNotValidServiceException(Throwable cause) {
        super(cause);
    }
}
