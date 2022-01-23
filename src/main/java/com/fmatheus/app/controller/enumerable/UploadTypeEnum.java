package com.fmatheus.app.controller.enumerable;

import lombok.Getter;


public enum UploadTypeEnum {

    MOVIE("movie");

    @Getter
    private final String value;

    UploadTypeEnum(String value) {
        this.value = value;
    }

}

