package com.savin.bank.dao.exception;

public class DaoSystemException extends DaoException {
    public DaoSystemException() {
    }

    public DaoSystemException(String message) {
        super(message);
    }

    public DaoSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoSystemException(Throwable cause) {
        super(cause);
    }
}
