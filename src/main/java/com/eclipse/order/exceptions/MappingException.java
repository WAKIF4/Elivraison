package com.eclipse.order.exceptions;

public class MappingException extends RuntimeException {
    public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}
