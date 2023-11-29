package com.eclipse.order.exceptions;

public class NotFoundExcption extends RuntimeException {
    public NotFoundExcption(String message) {
        super(message);
    }

    public NotFoundExcption(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}
