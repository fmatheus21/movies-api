package com.fmatheus.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fmatheus.app.controller.constant.FormatTypeConstant;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"id", "title", "year", "rating", "urlTrailer", "createdBy", "createdAt", "updatedBy", "updatedAt", "image"})
public class MovieDto {

    private int id;
    private String title;
    private int year;
    private BigDecimal rating;
    private String urlTrailer;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FormatTypeConstant.DATE_TIME)
    private LocalDateTime createdAt;
    private String updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FormatTypeConstant.DATE_TIME)
    private LocalDateTime updatedAt;
    private String image;
    private String colorRating;


}
