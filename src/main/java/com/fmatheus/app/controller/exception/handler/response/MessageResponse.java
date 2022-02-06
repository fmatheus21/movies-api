package com.fmatheus.app.controller.exception.handler.response;

import com.fmatheus.app.controller.enumerable.MessagesEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class MessageResponse {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int statusCode;
    private final String statusDescription;
    private final String cause;
    private final String message;

    public MessageResponse(MessagesEnum messagesEnum, String cause, String message) {
        this.statusCode = messagesEnum.getHttpSttus().value();
        this.statusDescription = messagesEnum.getHttpSttus().name();
        this.cause = cause;
        this.message = message;
    }

}