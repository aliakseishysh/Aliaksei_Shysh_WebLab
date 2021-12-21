package com.epam.esm.controller.exception;

public class WrongParametersControllerException extends ControllerException{

    public static final int ERROR_CODE = 40005;

    public WrongParametersControllerException() {
        super();
    }

    public WrongParametersControllerException(Throwable cause) {
        super(cause);
    }

    public WrongParametersControllerException(String message) {
        super(message);
    }
}
