package com.fmatheus.app.controller.rule;

import com.fmatheus.app.controller.enumerable.MessagesEnum;
import com.fmatheus.app.controller.exception.BadRequestException;
import com.fmatheus.app.controller.exception.FileStorageException;
import com.fmatheus.app.controller.exception.ForbiddenException;
import com.fmatheus.app.controller.exception.UserInactiveException;
import com.fmatheus.app.controller.exception.handler.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class MessageResponseRule {

    @Autowired
    private MessageSource messageSource;

    private MessageResponse messageResponse(MessagesEnum messagesEnum) {
        String message = messageSource.getMessage(messagesEnum.getMessage(), null, LocaleContextHolder.getLocale());
        return new MessageResponse(messagesEnum.getHttpCode(), messagesEnum.getHttpDesctription(), messagesEnum.getHttpCause(), message);
    }

    public MessageResponse messageSuccessUpdate() {
        return messageResponse(MessagesEnum.SUCCESS_UPDATE);
    }

    public MessageResponse messageSuccessCreate() {
        return messageResponse(MessagesEnum.SUCCESS_CREATE);
    }

    public MessageResponse messageSuccessDelete() {
        return messageResponse(MessagesEnum.SUCCESS_DELETE);
    }

    public BadRequestException errorBadRequest() {
        return new BadRequestException(MessagesEnum.ERROR_BAD_REQUEST.getMessage());
    }

    public BadRequestException errorNotFound() {
        return new BadRequestException(MessagesEnum.ERROR_NOT_FOUND.getMessage());
    }

    public BadRequestException badRequestErrorUserNotfound() {
        return new BadRequestException(MessagesEnum.ERROR_USER_NOTFOUND.getMessage());
    }

    public UsernameNotFoundException usernameNotFoundException() {
        return new UsernameNotFoundException(MessagesEnum.ERROR_USER_NOTFOUND.getMessage());
    }

    public ForbiddenException errorNotPermission() {
        return new ForbiddenException(MessagesEnum.ERROR_NOT_PERMISSION.getMessage());
    }

    public InternalError internalError() {
        return new InternalError(MessagesEnum.ERROR_INTERNAL.getMessage());
    }

    public ForbiddenException forbiddenException() {
        return new ForbiddenException(MessagesEnum.ERROR_FORBIDDEN.getMessage());
    }

    public FileStorageException fileStorageException() {
        return new FileStorageException(MessagesEnum.ERROR_FILE_STORAGE.getMessage());
    }

    public UserInactiveException userInactiveException() {
        return new UserInactiveException(MessagesEnum.ERROR_USER_INACTIVE.getMessage());
    }

    public BadRequestException badRequestErrorFileMaxLength() {
        return new BadRequestException(MessagesEnum.ERROR_NOT_FOUND_FILE.getMessage());
    }


}
