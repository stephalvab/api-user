package com.bci.apiuser.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleValidationExceptions(WebExchangeBindException ex) {
        System.out.println(ex.getClass());
        System.out.println(ex.getLocalizedMessage());
        BindingResult result = ex.getBindingResult();
        String messageError = "";
        for (FieldError error : result.getFieldErrors()) {
            messageError = messageError.concat(error.getDefaultMessage()).concat(". ");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MessageException.Message(HttpStatus.BAD_REQUEST, messageError));
    }
}
