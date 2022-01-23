package com.fmatheus.app.controller.enumerable;

import lombok.Getter;

import java.io.File;

public enum AppPropertiesEnum {

    FILE("file:".concat(File.separator)),
    IMAGE_EXTENSION("png"),
    SYSTEM_PATH("/system"),
    MOVIE_PATH("/movie"),
    MOVIE_SIZE_WIDTH("400"),
    MOVIE_SIZE_HEIGHT("600");

    @Getter
    private final String value;

    AppPropertiesEnum(String value) {
        this.value = value;
    }

}
