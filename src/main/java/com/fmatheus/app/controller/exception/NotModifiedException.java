package com.fmatheus.app.controller.exception;

import java.io.Serial;

/**
 * Ao lancar esta excecao, a mesma sera capturada pela classe AppExceptionHandler onde sera tratada.
 *
 * @author Fernando Matheus
 */
public class NotModifiedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotModifiedException(String message) {
        super(message);
    }

}
