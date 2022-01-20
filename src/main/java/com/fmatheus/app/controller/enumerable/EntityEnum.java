package com.fmatheus.app.controller.enumerable;

import lombok.Getter;

public enum EntityEnum {

    TITLE("title");

    @Getter
    private final String value;

    EntityEnum(String value) {
        this.value = value;
    }

}