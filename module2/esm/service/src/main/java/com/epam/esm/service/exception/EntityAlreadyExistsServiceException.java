package com.epam.esm.service.exception;

public class EntityAlreadyExistsServiceException extends ServiceException{

    public static final int ERROR_CODE = 40902;

    public EntityAlreadyExistsServiceException(Throwable cause) {
        super(cause);
    }


}
