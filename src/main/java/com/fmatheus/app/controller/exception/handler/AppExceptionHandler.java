package com.fmatheus.app.controller.exception.handler;

import com.fmatheus.app.controller.enumerable.MessagesEnum;
import com.fmatheus.app.controller.exception.*;
import com.fmatheus.app.controller.exception.handler.response.MessageResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe que captura as excecoes da api. A anotacao @ControllerAdvice significa
 * que esta classe ira monitorar todas as execoes da api.
 *
 * @author Fernando Matheus
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    private String message;

    private String cause;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        this.message = messageSource.getMessage(MessagesEnum.ERROR_NOT_READABLE.getMessage(), null, LocaleContextHolder.getLocale());
        this.cause = ex.getCause().toString();
        List<MessageResponse> erros = Collections.singletonList(new MessageResponse(
                MessagesEnum.ERROR_NOT_READABLE.getHttpCode(),
                MessagesEnum.ERROR_NOT_READABLE.getHttpDesctription(),
                this.cause,
                this.message
        ));
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<MessageResponse> erros = this.createListErros(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getBindingResult());
        return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(RuntimeException ex, WebRequest request) {
        this.message = this.messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        List<MessageResponse> erros = Collections.singletonList(new MessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                this.cause,
                this.message
        ));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<Object> handleForbiddenException(RuntimeException ex, WebRequest request) {
        this.message = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        List<MessageResponse> erros = Collections.singletonList(new MessageResponse(
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.name(),
                this.cause,
                this.message
        ));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({FileStorageException.class})
    public ResponseEntity<Object> handleFileStorageException(RuntimeException ex, WebRequest webRequest) {
        this.message = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        List<MessageResponse> erros = Collections.singletonList(new MessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                this.cause,
                this.message
        ));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(ResponseStatusException.class)
    ResponseEntity<RestResponseHandler> handleStatusException(ResponseStatusException ex, WebRequest request) {
        return RestResponseHandler.builder().exception(ex).path(request.getDescription(false).substring(4)).entity();
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(DataIntegrityViolationException ex,
                                                                       WebRequest request) {
        this.message = messageSource.getMessage(MessagesEnum.ERROR_BAD_REQUEST.getMessage(), null,
                LocaleContextHolder.getLocale());
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        List<MessageResponse> erros = Collections.singletonList(new MessageResponse(
                MessagesEnum.ERROR_BAD_REQUEST.getHttpCode(),
                MessagesEnum.ERROR_BAD_REQUEST.getHttpDesctription(),
                this.cause,
                this.message
        ));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
                                                                       WebRequest request) {
        this.message = messageSource.getMessage(MessagesEnum.ERROR_NOT_FOUND.getMessage(), null,
                LocaleContextHolder.getLocale());
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        List<MessageResponse> erros = Collections.singletonList(new MessageResponse(
                MessagesEnum.ERROR_NOT_FOUND.getHttpCode(),
                MessagesEnum.ERROR_NOT_FOUND.getHttpDesctription(),
                this.cause,
                this.message
        ));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({NotModifiedException.class})
    public ResponseEntity<Object> handleNotModifiedException(RuntimeException ex, WebRequest request) {
        this.message = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        List<MessageResponse> erros = Collections.singletonList(new MessageResponse(
                HttpStatus.NOT_MODIFIED.value(),
                HttpStatus.NOT_MODIFIED.name(),
                this.cause,
                this.message
        ));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({UserInactiveException.class})
    public ResponseEntity<Object> handleUserInactiveException(RuntimeException ex, WebRequest request) {
        this.message = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        this.cause = ExceptionUtils.getRootCauseMessage(ex);
        List<MessageResponse> erros = Collections.singletonList(new MessageResponse(
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.name(),
                this.cause,
                this.message
        ));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<MessageResponse> createListErros(int status, String error, BindingResult result) {
        List<MessageResponse> erros = new ArrayList<>();
        result.getFieldErrors().forEach(fieldError -> {
            this.message = this.messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            this.cause = fieldError.toString();
            erros.add(new MessageResponse(status, error, cause, message));
        });
        return erros;
    }


}