package com.fmatheus.app.controller.dto.swagger;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Notfound {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;

}
