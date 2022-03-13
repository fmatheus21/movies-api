package com.fmatheus.app.controller.dto.request;

import com.fmatheus.app.controller.util.AppUtil;
import com.fmatheus.app.controller.util.LocalDatetUtil;
import com.fmatheus.app.model.entity.Movie;
import com.fmatheus.app.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class MovieDtoRequest {

    @NotNull
    private String codeImdb;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    private int year;

    @NotNull
    private BigDecimal rating;

    @NotNull
    @NotBlank
    private String urlTrailer;

    @SneakyThrows
    public static Movie converterEntity(MovieDtoRequest request, String image, User user) {
        return Movie.builder()
                .codeImdb(request.getCodeImdb())
                .title(AppUtil.convertFirstUppercaseCharacter(request.getTitle()))
                .year(request.getYear())
                .rating(request.getRating())
                .urlTrailer(request.getUrlTrailer())
                .createdBy(user)
                .createdAt(LocalDatetUtil.currentDateTime())
                .updatedBy(user)
                .updatedAt(LocalDatetUtil.currentDateTime())
                .image(image)
                .build();
    }

}
