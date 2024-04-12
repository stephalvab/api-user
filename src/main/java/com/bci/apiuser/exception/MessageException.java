package com.bci.apiuser.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Builder
@Data
public class MessageException {
    private String message;
    private int status;
    private String messageStatus;

    public static MessageException Message (HttpStatus status, String message) {
        return MessageException.builder()
                .status(status.value())
                .messageStatus(status.name())
                .message(message)
                .build();
    }

}
