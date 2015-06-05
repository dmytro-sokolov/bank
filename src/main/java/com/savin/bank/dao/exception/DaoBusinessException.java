package com.savin.bank.dao.exception;

public class DaoBusinessException extends DaoException{
    public DaoBusinessException() {
    }

    public DaoBusinessException(String message) {
        super(message);
    }

    public DaoBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoBusinessException(Throwable cause) {
        super(cause);
    }
}
