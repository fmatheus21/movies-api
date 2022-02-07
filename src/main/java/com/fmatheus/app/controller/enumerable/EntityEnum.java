package com.fmatheus.app.controller.enumerable;

import lombok.Getter;

public enum EntityEnum {

    CODE_IMDB("codeImdb"),
    TITLE("title"),
    YEAR("year"),
    RATING("rating");

    @Getter
    private final String value;

    EntityEnum(String value) {
        this.value = value;
    }

}