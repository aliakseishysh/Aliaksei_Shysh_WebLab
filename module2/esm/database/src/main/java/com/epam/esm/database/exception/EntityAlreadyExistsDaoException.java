package com.epam.esm.database.exception;

public class EntityAlreadyExistsDaoException extends DaoException {

    public static final int ERROR_CODE = 40901;

    public EntityAlreadyExistsDaoException(String message) {
        super(message);
    }

}
