package com.fmatheus.app.controller.dto.swagger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Unauthorized {

    private String error;
    private String errorDescription;

}
