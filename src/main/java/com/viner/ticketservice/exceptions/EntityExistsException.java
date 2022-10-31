package com.viner.ticketservice.exceptions;

public class EntityExistsException extends RuntimeException{
    public EntityExistsException(String message) {
        super(message);
    }
}
