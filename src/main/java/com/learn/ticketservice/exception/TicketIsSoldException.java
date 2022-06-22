package com.learn.ticketservice.exception;

public class TicketIsSoldException extends RuntimeException{
    public TicketIsSoldException(String message) {
        super(message);
    }

    public TicketIsSoldException(String message, Throwable cause) {
        super(message, cause);
    }
}
