package com.fmatheus.app.controller.dto.request;

import com.fmatheus.app.controller.util.AppUtil;
import com.fmatheus.app.controller.util.LocalDatetUtil;
import com.fmatheus.app.model.entity.Movie;
import com.fmatheus.app.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.codehaus.jackson.map.ObjectMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class MovieRequest {

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
    public static Movie converterEntity(String json, String image, User user) {

        var objectMapper = new ObjectMapper();
        var request = objectMapper.readValue(json, MovieRequest.class);

        return Movie.builder()
                .title(AppUtil.convertFirstUppercaseCharacter(request.getTitle()))
                .year(request.getYear())
                .rating(request.getRating())
                .urlTrailer(request.urlTrailer)
                .createdBy(user)
                .createdAt(LocalDatetUtil.currentDateTime())
                .updatedBy(user)
                .updatedAt(LocalDatetUtil.currentDateTime())
                .image(image)
                .build();

    }

}
