package com.fmatheus.app.controller.enumerable;

import lombok.Getter;

import java.io.File;

public enum AppPropertiesEnum {

    FILE("file:".concat(File.separator)),
    IMAGE_EXTENSION("png"),
    SYSTEM_PATH("/system"),
    AVATAR_SYSTEM_PATH("/system/avatar"),
    AVATAR_PATH("/avatar"),
    AVATAR_SIZE_WIDTH("300"),
    AVATAR_SIZE_HEIGHT("300"),
    MOVIE_PATH("/movie"),
    MOVIE_SIZE_WIDTH("700"),
    MOVIE_SIZE_HEIGHT("700");

    @Getter
    private final String value;

    AppPropertiesEnum(String value) {
        this.value = value;
    }

}
