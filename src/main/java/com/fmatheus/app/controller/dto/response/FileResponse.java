package com.fmatheus.app.controller.dto.response;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponse {

    private int width;
    private int height;
    private String path;

}
