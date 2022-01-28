package com.fmatheus.app.controller.enumerable;

import lombok.Getter;

/**
 * Classe utilizada para referenciar os tipos de metodos em uma implementacao HATEOAS.
 *
 * @author Fernando Matheus
 */
public enum HateoasEnum {

    VIEW("View"),
    VIEW_TYPE("GET"),
    DELETE("Delete"),
    DELETE_TYPE("DELETE"),
    UPDATE("Update"),
    UPDATE_TYPE("PUT"),
    RECORD(" Record");

    @Getter
    private final String value;

    HateoasEnum(String value) {
        this.value = value;
    }

}
