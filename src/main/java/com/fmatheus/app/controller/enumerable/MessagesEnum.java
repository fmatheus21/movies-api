package com.fmatheus.app.controller.enumerable;

import lombok.Getter;

/**
 * Enum que representa as mensagens padrao da api que se encontrar em
 * messages.properties
 *
 * @author Fernando Matheus
 */
public enum MessagesEnum {

    SUCCESS_CREATE(201, "Created", "CREATED", "message.success.create"),
    SUCCESS_UPDATE(201, "Created", "CREATED", "message.success.update"),
    SUCCESS_DELETE(200, "OK", "OK", "message.success.delete"),
    ERROR_INTERNAL(500, "Internal Server", "INTERNAL SERVER ERROR", "message.error.internal-server"),
    ERROR_FILE_STORAGE(500, "Internal Server", "INTERNAL SERVER ERROR", "message.error.file-storage"),
    ERROR_FORBIDDEN(403, "Forbidden", "FORBIDDEN", "message.error.forbidden"),
    ERROR_BAD_REQUEST(400, "Bad Request", "BAD REQUEST", "message.error.bad-request"),
    ERROR_NOT_PERMISSION(403, "Forbidden", "FORBIDDEN", "message.error.not-permission"),
    ERROR_NOT_FOUND(400, "Bad Request", "BAD REQUEST", "message.error.not-found"),
    ERROR_NOT_READABLE(400, "Bad Request", "BAD REQUEST", "message.error.not-readable"),
    ERROR_USER_NOTFOUND(400, "Bad Request", "BAD REQUEST", "message.error.user-notfound"),
    ERROR_USER_INACTIVE(400, "Bad Request", "BAD REQUEST", "message.error.user-inactive"),
    ERROR_NOT_FOUND_FILE(400, "Bad Request", "BAD REQUEST", "message.error.not-found-file");

    @Getter
    private final int httpCode;

    @Getter
    private final String httpDesctription;

    @Getter
    private final String httpCause;

    @Getter
    private final String message;

    MessagesEnum(int httpCode, String httpDesctription, String httpCause, String message) {
        this.httpCode = httpCode;
        this.httpDesctription = httpDesctription;
        this.httpCause = httpCause;
        this.message = message;
    }

}
