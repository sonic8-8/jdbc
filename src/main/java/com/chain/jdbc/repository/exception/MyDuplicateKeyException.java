package com.chain.jdbc.repository.exception;

public class MyDuplicateKeyException extends MyDbException {

    public MyDuplicateKeyException() {
        super();
    }

    public MyDuplicateKeyException(String message) {
        super(message);
    }

    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicateKeyException(Throwable cause) {
        super(cause);
    }
}
