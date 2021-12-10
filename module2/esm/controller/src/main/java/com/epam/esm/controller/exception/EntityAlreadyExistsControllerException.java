package com.epam.esm.controller.exception;

public class EntityAlreadyExistsControllerException extends ControllerException{

    public static final int ERROR_CODE = 40903;

    public EntityAlreadyExistsControllerException(Throwable cause) {
        super(cause);
    }

    public EntityAlreadyExistsControllerException(String message) {
        super(message);
    }
}
