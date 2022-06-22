package com.learn.ticketservice.web;

import com.learn.ticketservice.exception.DataNotFoundException;
import com.learn.ticketservice.exception.EntityExistsException;
import com.learn.ticketservice.exception.TicketIsSoldException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            DataNotFoundException.class,
            EntityExistsException.class,
            TicketIsSoldException.class})
    public ResponseEntity<Object> controllerAdvice(RuntimeException exception) {
        return ResponseEntity.status(404).body(exception.getMessage());
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
