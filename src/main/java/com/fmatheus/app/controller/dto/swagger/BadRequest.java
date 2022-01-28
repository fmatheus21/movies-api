package com.fmatheus.app.controller.dto.swagger;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BadRequest {

    private LocalDateTime timestamp;
    private int status;
    private String statusDescription;
    private String cause;
    private String message;

}
